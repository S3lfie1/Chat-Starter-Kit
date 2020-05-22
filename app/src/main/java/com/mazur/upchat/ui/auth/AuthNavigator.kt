package com.mazur.upchat.ui.auth

import com.mazur.upchat.ui.BaseFragment

interface AuthNavigator{
    fun navigateTo(fragment: BaseFragment, addToBackStack: Boolean = true)
    fun navigateTo(fragment: BaseFragment, target: BaseFragment)
    fun navigateToLogged()
    fun navigateToLogin()
    fun navigateToRegister()
}