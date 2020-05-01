package com.easyprog.tasksapp.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.presenters.EditPersonalDataPresenter
import com.easyprog.tasksapp.view.EditPersonalDataView
import kotlinx.android.synthetic.main.fragment_edit_personal_data.*
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class EditPersonalDataFragment : MvpAppCompatFragment(), EditPersonalDataView {

    var TAG = EditPersonalDataFragment::class.java.simpleName

    @InjectPresenter
    lateinit var editUserPersonalDataPresenter: EditPersonalDataPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editUserPersonalDataPresenter.getPersonalData()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_personal_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSavePersonalData.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
            val token = sharedPreferences?.getString("token", null)

            editUserPersonalDataPresenter.editPersonalData(token = token!!, name = editTextPersonalDataName.text.toString(),
            surname = editTextPersonalDataSurname.text.toString(), email = editTextPersonalDataEmail.text.toString(),
            login = editTextPersonalDataLogin.text.toString())
        }
    }

    override fun presentProfile(name: String, surname: String, email: String, login: String) {
        editTextPersonalDataName.setText(name)
        editTextPersonalDataSurname.setText(surname)
        editTextPersonalDataEmail.setText(email)
        editTextPersonalDataLogin.setText(login)
    }

    override fun presentLoading() {
        blackoutBackgroundEditPersonalData.visibility = View.VISIBLE
    }

    override fun editPersonalDataSuccess(messageSuccess: Int) {
        showMessage(message = messageSuccess)
    }

    override fun editPersonalDataError(messageError: Int) {
        showMessage(message = messageError)
    }

    override fun emptyPersonalData(messageEmptyPersonalData: Int) {
        showMessage(message = messageEmptyPersonalData)
    }

    override fun invalidatePersonalDataEmail(messageInvalidatePersonalDataEmail: Int) {
        blackoutBackgroundEditPersonalData.visibility = View.GONE
        editTextLayoutEmail.error = getString(messageInvalidatePersonalDataEmail)
    }

    override fun repeatPersonalDataEmail(messageRepeatPersonalDataEmail: Int) {
        blackoutBackgroundEditPersonalData.visibility = View.GONE
        editTextLayoutEmail.error = getString(messageRepeatPersonalDataEmail)
    }

    override fun repeatPersonalDataLogin(messageRepeatPersonalDataLogin: Int) {
        blackoutBackgroundEditPersonalData.visibility = View.GONE
        editTextLayoutLogin.error = getString(messageRepeatPersonalDataLogin)
    }

    override fun noInternet(messageNoInternet: Int) {
        showMessage(message = messageNoInternet)
    }

    private fun showMessage(message: Int) {
        blackoutBackgroundEditPersonalData.visibility = View.GONE
        Toast.makeText(activity?.applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
