package com.mazur.upchat.ui.auth.form.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.mazur.upchat.extensions.isValidEmail
import com.mazur.upchat.extensions.isValidPassword
import com.mazur.upchat.ui.auth.form.AuthView

class LoginPresenter: ViewModel() {
    var auth: FirebaseAuth? = null
    var view: AuthView.LoginView? = null

    @SuppressLint("CheckResult")
    fun validateCredentials(email: String, password: String) {
        var emailValid = false
        var passwordValid = false

        if (email.isValidEmail()) {
            emailValid = true
        } else {
            view?.markEmailInvalid()
        }

        if (password.isValidPassword()) {
            passwordValid = true
        } else {
            view?.markPasswordInvalid()
        }

        if (emailValid && passwordValid) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        view?.navigateToHome()
                    } else {
                        Log.d("UPCHAT_DEBUG", "Falied to login user: " + task.exception?.localizedMessage)
                    }
                }
        }
    }

}