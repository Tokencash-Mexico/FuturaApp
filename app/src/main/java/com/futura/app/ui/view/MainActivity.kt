package com.futura.app.ui.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.futura.app.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity(), MultiplePermissionsListener {

    private val permissions = listOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.CAMERA
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onResume() {
        super.onResume()

        Dexter.withContext(this)
            .withPermissions(permissions)
            .withListener(this)
            .check()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onPermissionRationaleShouldBeShown(
        permissionList: MutableList<PermissionRequest>?,
        permissionToken: PermissionToken?
    ) {
        permissionToken?.continuePermissionRequest()
    }

    override fun onPermissionsChecked(permissionsReport: MultiplePermissionsReport?) {
        if (permissionsReport?.deniedPermissionResponses?.isNotEmpty() == true){
            Toast.makeText(this, getString(R.string.necessary_permits), Toast.LENGTH_LONG).show()
            finishAffinity()
        }
    }
}