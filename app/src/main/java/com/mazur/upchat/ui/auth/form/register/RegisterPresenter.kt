package com.mazur.upchat.ui.auth.form.register

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.mazur.upchat.extensions.isValidEmail
import com.mazur.upchat.extensions.isValidName
import com.mazur.upchat.extensions.isValidPassword
import com.mazur.upchat.model.User
import com.mazur.upchat.ui.auth.form.AuthView
import android.util.Log
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase
import com.mazur.upchat.helpers.Preferences
import com.orhanobut.hawk.Hawk

class RegisterPresenter : ViewModel() {
    var view: AuthView.RegisterView? = null

    fun validateRegisterFields(
        email: String,
        firstName: String,
        lastName: String,
        password: String,
        passwordConfirm: String
    ) {
        if (email.isValidEmail()) {
            if (password.isValidPassword() && password == passwordConfirm) {
                if (firstName.isValidName() && lastName.isValidName()) {
                    val user = User(email, firstName, lastName, password)
                    registerUser(user)
                }
            }
        }

    }

    private fun registerUser(user: User) {
        val fireBaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        try {
            fireBaseAuth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = fireBaseAuth.currentUser!!.uid

                        val fireBaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
                        fireBaseDatabase.getReference("users").child(userId).child("firstName")
                            .setValue(user.firstName)
                        fireBaseDatabase.getReference("users").child(userId).child("lastName")
                            .setValue(user.lastName)

                        Hawk.put(Preferences.USER_DATA, user)

                        view?.navigateToHome()
                    } else {
                        Log.d("UPCHAT_DEBUG", "Cannot register user: " + task.isSuccessful)
                    }
                }
                .addOnFailureListener {
                    Log.d("UPCHAT_ERROR", "Registration error: " + it.localizedMessage)
                }
        } catch (ex: FirebaseAuthUserCollisionException) {
            Log.e("UPCHAT_DEBUG", "Registration failed: " + ex.localizedMessage)
        }

    }

}