package com.mazur.upchat.ui

interface Navigator{
    fun navigateTo(fragment: BaseFragment, addToBackStack: Boolean = true)
    fun navigateTo(fragment: BaseFragment, target: BaseFragment)
    fun navigateBack()
    fun navigateToMenu()
    fun navigateBackToMenu()
}