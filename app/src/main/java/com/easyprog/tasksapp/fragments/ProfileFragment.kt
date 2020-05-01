package com.easyprog.tasksapp.fragments


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.activities.UserActivity
import com.easyprog.tasksapp.presenters.ProfilePresenter
import com.easyprog.tasksapp.view.ProfileView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.nav_header.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    private val TAG = ProfileFragment::class.java.simpleName

    private var avatar: Bitmap? = null

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profilePresenter.getUserProfile()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.profile_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = NavHostFragment.findNavController(this)
        val dialogItems = arrayOf(getString(R.string.change_personal_data),
            getString(R.string.change_avatar), getString(R.string.change_password))

        when(item?.itemId) {
            R.id.editProfileFragmentItem -> {
                AlertDialog.Builder(activity)
                    .setTitle(R.string.item_menu_edit)
                    .setItems(dialogItems){ dialog, which ->
                        when(dialogItems[which]) {
                            getString(R.string.change_personal_data) -> navController.navigate(R.id.editPersonalDataFragment)
                            getString(R.string.change_avatar) -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                    if (ContextCompat.checkSelfPermission(
                                            activity!!.applicationContext,
                                            Manifest.permission.READ_EXTERNAL_STORAGE
                                        ) == PackageManager.PERMISSION_DENIED){
                                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                                        requestPermissions(permissions, 1001)
                                    } else{
                                        //permission already granted
                                        pickImageFromGallery()
                                    }
                                } else{
                                    //system OS is < Marshmallow
                                    pickImageFromGallery()
                                }
                            }
                            getString(R.string.change_password) -> navController.navigate(R.id.editAuthorizationDataFragment)
                        }
                    }
                    .create()
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            1001 -> {
                if (requestCode == 1001) {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        pickImageFromGallery()
                    } else{
                        Toast.makeText(activity?.applicationContext, "Доступ отменен", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1000){
            val uri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, uri)

            val sharedPreferences = activity?.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
            val token = sharedPreferences?.getString("token", null)
            profilePresenter.updateAvatar(token = token!!, avatar = bitmap)
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1000)
    }

    @SuppressLint("SetTextI18n")
    override fun presentProfile(avatar: String, name: String, surname: String, email: String) {
        if(avatar == "no_avatar") Picasso.get().load(R.mipmap.no_avatar).into(imageAvatarProfile)
        else Picasso.get().load(avatar).fit().networkPolicy(NetworkPolicy.NO_CACHE).centerCrop().into(imageAvatarProfile)
        textNameProfile.text = "$name $surname"
        textEmailProfile.text = email
        val num = 30
        textAllTask.text = getString(R.string.all_task) + ": $num"
        textPersonalTask.text = getString(R.string.personal_task) + ": $num"
        textGroupTask.text = getString(R.string.group_task) + ": $num"
    }

    override fun updateAvatarError(messageError: Int) {
        Toast.makeText(activity?.applicationContext, messageError, Toast.LENGTH_SHORT).show()
    }

    override fun updateAvatarInView(avatar: String) {
        Picasso.get().invalidate(avatar)
        Picasso.get().load(avatar).placeholder(R.mipmap.no_avatar).fit()
            .networkPolicy(NetworkPolicy.NO_CACHE).centerCrop().into(imageAvatarProfile)
        val navHeader = activity?.navigationView?.getHeaderView(0)
        Picasso.get().load(avatar).placeholder(R.mipmap.no_avatar).fit()
            .networkPolicy(NetworkPolicy.NO_CACHE).centerCrop().into(navHeader?.imageNavHeaderAvatar)
    }
}
