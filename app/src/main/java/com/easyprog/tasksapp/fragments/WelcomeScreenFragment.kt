package com.easyprog.tasksapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.activities.MainActivity
import com.easyprog.tasksapp.activities.UserActivity

class WelcomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_welcome_screen, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = NavHostFragment.findNavController(this)

        val sharedPreferences = activity?.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)!!
        val token = sharedPreferences.getString("token", "null")
        Handler().postDelayed({
            if (token != "null") {
                startActivity(Intent(this.activity, UserActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            } else {
                navController.navigate(R.id.loginFragment)
            }
        },2000)
    }
}
