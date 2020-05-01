package com.easyprog.tasksapp.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.presenters.UserActivityPresenter
import com.easyprog.tasksapp.view.UserActivityView
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.nav_header.view.imageNavHeaderAvatar

class UserActivity : MvpAppCompatActivity(), UserActivityView {

    @InjectPresenter
    lateinit var userActivityPresenter: UserActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userActivityPresenter.getUserAvatar()

        val navController = this.findNavController(R.id.navHostUser)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostUser)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    @SuppressLint("SetTextI18n")
    override fun fillNavHeader(avatar: String) {
        val navHeader = navigationView.getHeaderView(0)
        if(avatar == "no_avatar") Picasso.get().load(R.mipmap.no_avatar).into(navHeader.imageNavHeaderAvatar)
        else Picasso.get().load(avatar).networkPolicy(NetworkPolicy.NO_CACHE).fit().centerCrop().into(navHeader.imageNavHeaderAvatar)
//        navHeader.textNavHeaderName.text = "$name $surname"
//        navHeader.textNavHeaderEmail.text = email
    }

//    @SuppressLint("SetTextI18n")
//    private fun navHeaderSet(name: String, surname: String, email: String) {
//        val navHeader = navigationView.getHeaderView(0)
//        navHeader.textNavHeaderName.text = "$name $surname"
//        navHeader.textNavHeaderEmail.text = email
//    }

}
