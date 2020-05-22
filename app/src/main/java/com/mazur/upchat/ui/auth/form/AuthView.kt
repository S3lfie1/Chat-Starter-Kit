package com.mazur.upchat.ui.auth.form

interface AuthView {
    fun processAdapter()

    interface LoginView {
        fun navigateToHome()
        fun markEmailInvalid()
        fun markPasswordInvalid()
    }

    interface RegisterView {
        fun navigateToHome()

    }
}