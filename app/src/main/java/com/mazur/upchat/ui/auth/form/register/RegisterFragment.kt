package com.mazur.upchat.ui.auth.form.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.mazur.upchat.R
import com.mazur.upchat.ui.auth.AuthBaseFragment
import com.mazur.upchat.ui.auth.form.AuthView
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : AuthBaseFragment(), AuthView.RegisterView {
    private lateinit var presenter: RegisterPresenter
    var auth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        presenter = ViewModelProviders.of(this).get(RegisterPresenter::class.java)
        presenter.view = this

        view.btn_register.setOnClickListener {
            val email: String = view.txt_register_email.text.toString()
            val firstName: String = view.txt_register_firstname.text.toString()
            val lastName: String = view.txt_register_lastname.text.toString()
            val password: String = view.txt_register_password.text.toString()
            val passwordConfirm: String = view.txt_register_confirm_password.text.toString()

            presenter.validateRegisterFields(email, firstName, lastName, password, passwordConfirm)
        }

        return view
    }

    override fun navigateToHome() {
        authNavigator?.navigateToLogged()
    }
}