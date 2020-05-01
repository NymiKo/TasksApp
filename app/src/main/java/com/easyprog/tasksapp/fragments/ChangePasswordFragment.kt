package com.easyprog.tasksapp.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.presenters.ChangePasswordPresenter
import com.easyprog.tasksapp.view.ChangePasswordView
import kotlinx.android.synthetic.main.fragment_change_password.*

/**
 * A simple [Fragment] subclass.
 */
class ChangePasswordFragment : MvpAppCompatFragment(), ChangePasswordView {

    @InjectPresenter
    lateinit var changePasswordPresenter: ChangePasswordPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSavePassword.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
            val token = sharedPreferences?.getString("token", null)

            changePasswordPresenter.changePassword(token = token!!, oldPassword = editTextOldPassword.text.toString(),
            newPassword = editTextNewPassword.text.toString())
        }
    }

    override fun changePasswordSuccess(messageSuccess: Int) {
        Toast.makeText(activity?.applicationContext, messageSuccess, Toast.LENGTH_SHORT).show()
    }

    override fun changePasswordError(messageError: Int) {
        Toast.makeText(activity?.applicationContext, messageError, Toast.LENGTH_SHORT).show()
    }

    override fun errorWithNewPassword(messageError: Int) {
        editTextLayoutNewPassword.error = getString(messageError)
    }

    override fun errorWithOldPassword(messageError: Int) {
        editTextLayoutOldPassword.error = getString(messageError)
    }

}
