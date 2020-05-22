package com.mazur.upchat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mazur.upchat.R
import com.mazur.upchat.ui.userslist.UsersListFragment

class BaseWrapperActivity : AppCompatActivity(), Navigator {

    private var menuTransactionId: Int? = null
    private val transactionTag = "transaction_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_wrapper)

        menuTransactionId = savedInstanceState?.getInt(transactionTag)

        if (savedInstanceState != null) {
            return
        } else {
            supportFragmentManager.beginTransaction().add(R.id.base_container, UsersListFragment()).commit()
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
//        transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
        transaction.replace(R.id.base_container, fragment)

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
            transaction.replace(R.id.base_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun navigateBack() {
        supportFragmentManager.popBackStack()
    }

    override fun navigateToMenu() {
//        menuTransactionId = replaceFragment(HomeFragment(), true)
    }

    override fun navigateBackToMenu() {
        menuTransactionId?.let {
            supportFragmentManager.popBackStack(it, 0)
        }
    }

}