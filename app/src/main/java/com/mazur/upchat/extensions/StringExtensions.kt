package com.mazur.upchat.extensions

import android.util.Patterns
import com.mazur.upchat.helpers.Preferences

fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword(): Boolean =
    this.length >= Preferences.MIN_PASSWORD_LENGTH && this.length <= Preferences.MAX_PASSWORD_LENGTH

fun String.isValidName(): Boolean =
    this.length >= Preferences.MIN_NAME_LENGTH && this.length <= Preferences.MAX_NAME_LENGTH