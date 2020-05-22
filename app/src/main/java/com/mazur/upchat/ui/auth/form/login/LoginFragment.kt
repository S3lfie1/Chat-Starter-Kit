package com.mazur.upchat.ui.auth.form.login

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.mazur.upchat.R
import com.mazur.upchat.ui.BaseWrapperActivity
import com.mazur.upchat.ui.auth.AuthBaseFragment
import com.mazur.upchat.ui.auth.form.AuthView
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment :  AuthBaseFragment(), AuthView.LoginView {
    private lateinit var presenter: LoginPresenter
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        presenter = ViewModelProviders.of(this).get(LoginPresenter::class.java)
        presenter.view = this

        auth = FirebaseAuth.getInstance()
        presenter.auth = auth

        view.btn_login.setOnClickListener {
            val email = view.txt_login_email.text.toString()
            val password = view.txt_login_password.text.toString()

            presenter.validateCredentials(email, password)
        }

        return view
    }

    override fun onStart() {
        super.onStart()

//        val currentUser = auth.currentUser
//
//        println(currentUser?.isEmailVerified)
//
//        if (currentUser != null) {
//            navigateToLogged()
//        }
    }

    override fun navigateToHome() {
        authNavigator?.navigateToLogged()
    }

    override fun markEmailInvalid() {
        txt_login_email.error = "Błędny email"
    }

    override fun markPasswordInvalid() {
        txt_login_password.setBackgroundColor(Color.TRANSPARENT)
        txt_login_password.setError("Błędne hasło", null)
    }

}