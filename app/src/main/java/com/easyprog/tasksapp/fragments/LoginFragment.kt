package com.easyprog.tasksapp.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.easyprog.domain.repositories.LoginUserRepository
import com.easyprog.tasksapp.App

import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.activities.UserActivity
import com.easyprog.tasksapp.presenters.LoginUserPresenter
import com.easyprog.tasksapp.view.LoginUserView
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : MvpAppCompatFragment(), LoginUserView {

//    @Inject
//    lateinit var loginUserRepository: LoginUserRepository

    @InjectPresenter
    lateinit var loginUserPresenter: LoginUserPresenter

    lateinit var editor: SharedPreferences.Editor

//    @ProvidePresenter
//    fun provideLoginUserPresenter(): LoginUserPresenter {
//        return LoginUserPresenter(loginUserRepository = loginUserRepository)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentLayout = inflater.inflate(R.layout.fragment_login, container, false)
        val navController = NavHostFragment.findNavController(this)
        fragmentLayout.btnRegistration.setOnClickListener {
            navController.navigate(R.id.registrationFragment)
        }

        fragmentLayout.btnLogin.setOnClickListener {
            loginUserPresenter.loginUser(login = editEmailOrLogin.text.toString(), password = editPassword.text.toString())
        }
        // Inflate the layout for this fragment
        return fragmentLayout
    }

    override fun presentLoading() {
        blackoutBackgroundLogin.visibility = VISIBLE
    }

    override fun loginUserSuccess(token: String, name: String, surname: String, email: String) {
        val sharedPreferences = activity?.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)!!
        sharedPreferences.edit().putString("token", token).apply()

        startActivity(Intent(this.activity, UserActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    override fun loginUserFailed(messageError: Int) {
        showMessage(message = messageError)
    }

    override fun invalidateLoginData(messageInvalidateLoginData: Int) {
        showMessage(message = messageInvalidateLoginData)
    }

    override fun noInternet(messageNoInternet: Int) {
        showMessage(message = messageNoInternet)
    }

    private fun showMessage(message: Int) {
        blackoutBackgroundLogin.visibility = GONE
        Toast.makeText(activity?.applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
