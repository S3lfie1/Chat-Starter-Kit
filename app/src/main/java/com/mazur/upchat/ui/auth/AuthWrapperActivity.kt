package com.mazur.upchat.ui.auth

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mazur.upchat.R
import com.mazur.upchat.ui.BaseFragment
import com.mazur.upchat.ui.BaseWrapperActivity
import com.mazur.upchat.ui.auth.form.AuthFragment
import com.mazur.upchat.ui.auth.form.login.LoginFragment
import com.mazur.upchat.ui.auth.form.register.RegisterFragment

class AuthWrapperActivity : AppCompatActivity(), AuthNavigator {

    private var menuTransactionId: Int? = null
    private val transactionTag = "transaction_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_wrapper)

        menuTransactionId = savedInstanceState?.getInt(transactionTag)

        if (savedInstanceState != null) {
            return
        } else {
            supportFragmentManager.beginTransaction().add(R.id.auth_container, AuthFragment()).commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            menuTransactionId?.let {
                putInt(transactionTag, it)
            }
        }
        super.onSaveInstanceState(outState)
    }

    @SuppressLint("PrivateResource")
    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean): Int {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
        transaction.replace(R.id.auth_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        return transaction.commit()
    }

    override fun navigateTo(fragment: BaseFragment, addToBackStack: Boolean) {
        Handler().post {
            replaceFragment(fragment, addToBackStack)
        }
    }

    @SuppressLint("PrivateResource")
    override fun navigateTo(fragment: BaseFragment, target: BaseFragment) {
        Handler().post {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun navigateToLogged() {
        val intent = Intent(this, BaseWrapperActivity::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        } else {
            startActivity(intent)
        }

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        finish()
    }

    override fun navigateToRegister() {
        menuTransactionId = replaceFragment(RegisterFragment(), true)
    }

    override fun navigateToLogin() {
        menuTransactionId = replaceFragment(LoginFragment(), true)
    }
}