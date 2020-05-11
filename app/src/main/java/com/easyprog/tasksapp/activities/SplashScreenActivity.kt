package com.easyprog.tasksapp.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.presenters.SplashScreenPresenter
import com.easyprog.tasksapp.view.SplashScreenView

class SplashScreenActivity : MvpAppCompatActivity(),  SplashScreenView{

    @InjectPresenter
    lateinit var splashScreenPresenter: SplashScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val sharedPreferences = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)!!
        val token = sharedPreferences.getString("token", "null")
        Handler().postDelayed({
            if (token != "null") {
                //splashScreenPresenter.sendTasksOnServer(token!!)
                startActivity(Intent(this, UserActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            } else {
                startActivity(Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        },3000)
    }
}
