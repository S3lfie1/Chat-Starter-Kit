package com.mazur.upchat.ui.userslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mazur.upchat.R
import com.mazur.upchat.ui.BaseFragment

class UsersListFragment : BaseFragment(), UsersListView {
    private lateinit var presenter: UsersListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_messages_list, container, false)

        presenter = ViewModelProviders.of(this).get(UsersListPresenter::class.java)
        presenter.view = this



        return view
    }
}