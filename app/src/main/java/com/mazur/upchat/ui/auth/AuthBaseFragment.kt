package com.mazur.upchat.ui.auth

import android.content.Context
import androidx.fragment.app.Fragment

open class  AuthBaseFragment: Fragment(){
    var authNavigator : AuthNavigator? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        authNavigator = context as? AuthNavigator
    }

}