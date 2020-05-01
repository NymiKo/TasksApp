package com.easyprog.tasksapp.fragments


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.presenters.RegistrationUserPresenter
import com.easyprog.tasksapp.view.RegistrationUserView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_registration.*
import java.io.File


class RegistrationFragment : MvpAppCompatFragment(), RegistrationUserView {

    @InjectPresenter
    lateinit var registrationUserPresenter: RegistrationUserPresenter

    val GALLERY_REQUEST = 1000
    var image: Bitmap? = null
    private var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e("Ошибка!", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                token = task.result?.token!!
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDownloadAvatar.setOnClickListener {
            if (VERSION.SDK_INT >= VERSION_CODES.M){
                if (checkSelfPermission(activity!!.applicationContext,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
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

        btnRegister.setOnClickListener {
            registrationUserPresenter.registrationUser(
                token = token, login = editLoginRegistration.text.toString(),
                password = editPasswordRegistration.text.toString(), image = image, name = editNameRegistration.text.toString(),
                surname = editSurnameRegistration.text.toString(), email = editEmailRegistration.text.toString())
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST)
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
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST){
            val uri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, uri)
            imageAvatarRegistration.setImageBitmap(bitmap)
            image = bitmap
        }
    }

    override fun presentLoading() {
        blackoutBackgroundRegistration.visibility = VISIBLE
    }

    override fun registerUserSuccessful(messageSuccess: Int) {
        showMessage(message = messageSuccess)
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.loginFragment)
    }

    override fun registerUserFailed(messageError: Int) {
        showMessage(message = messageError)
    }

    override fun repeatLogin(messageRepeatLogin: Int) {
        showMessage(message = messageRepeatLogin)
    }

    override fun repeatEmail(messageRepeatEmail: Int) {
        showMessage(message = messageRepeatEmail)
    }

    override fun invalidateEmail(messageInvalidateEmail: Int) {
        showMessage(message = messageInvalidateEmail)
    }

    override fun invalidatePassword(messageInvalidatePassword: Int) {
        showMessage(message = messageInvalidatePassword)
    }

    override fun invalidateOtherData(messageInvalidateOtherData: Int) {
        showMessage(message = messageInvalidateOtherData)
    }

    override fun noInternet(messageNoInternet: Int) {
        showMessage(message = messageNoInternet)
    }

    private fun showMessage(message: Int) {
        blackoutBackgroundRegistration.visibility = GONE
        Toast.makeText(activity?.applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
