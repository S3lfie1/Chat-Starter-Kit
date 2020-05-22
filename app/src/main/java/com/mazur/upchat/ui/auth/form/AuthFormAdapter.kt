package com.mazur.upchat.ui.auth.form

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AuthFormAdapter(fragmentManager: FragmentManager, private val context: Context?) : FragmentPagerAdapter(fragmentManager){
    private var mFragmentItems : ArrayList<Fragment> = ArrayList()
    private var mFragmentTitle : ArrayList<String> = ArrayList()

    fun addFragments(fragmentItem : Fragment,fragmentTitle: String){
        mFragmentItems.add(fragmentItem)
        mFragmentTitle.add(fragmentTitle)
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentItems[position]
    }

    override fun getCount(): Int {
        return mFragmentItems.size
    }

    override fun getPageTitle(position: Int):CharSequence{
        return when(position){
            0 -> "Login"
            1 -> "Register"
            else -> "Login"
        }
    }
}