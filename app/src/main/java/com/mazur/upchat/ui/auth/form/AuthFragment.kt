package com.mazur.upchat.ui.auth.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mazur.upchat.R
import com.mazur.upchat.ui.auth.AuthBaseFragment
import com.mazur.upchat.ui.auth.form.login.LoginFragment
import com.mazur.upchat.ui.auth.form.register.RegisterFragment
import kotlinx.android.synthetic.main.fragment_auth.view.*

class AuthFragment : AuthBaseFragment(), AuthView {
    private lateinit var presenter: AuthPresenter
    private lateinit var formAdapter : AuthFormAdapter
    private lateinit var mTabLayout : TabLayout
    private lateinit var mViewPager : ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_auth, container, false)

        presenter = ViewModelProviders.of(this).get(AuthPresenter::class.java)
        presenter.view = this

        mTabLayout = view.tabLayout
        mViewPager = view.viewPager

        processAdapter()

        return view
    }

    override fun processAdapter(){
        formAdapter = AuthFormAdapter(childFragmentManager, context)
        formAdapter.addFragments(LoginFragment(), "Login")
        formAdapter.addFragments(RegisterFragment(), "Register")

        mViewPager.adapter = formAdapter

        mTabLayout.setupWithViewPager(mViewPager)
    }


}