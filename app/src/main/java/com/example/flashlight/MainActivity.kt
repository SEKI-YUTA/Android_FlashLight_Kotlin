package com.example.flashlight

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var imageButton: ImageButton
    var state: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)

        imageButton= findViewById(R.id.torchButton)

        Dexter.withContext(this).withPermission(android.Manifest.permission.CAMERA).withListener(object: PermissionListener {
            @SuppressLint("NewApi")
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
//                TODO("Not yet implemented")
                runFlashLight()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
//                Toast.makeText(MainActivity.this, "camera permission is required", Toast.LENGTH_SHORT)
//                Toast.makeText(this, "camera permission is required", Toast.LENGTH_SHORT)
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
//                TODO("Not yet implemented")
            }

        }).check()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun runFlashLight() {
//        TODO("Not yet implemented")
        imageButton.setOnClickListener {
            if(!state) run {
                val cameraManager: CameraManager =
                     getSystemService (Context.CAMERA_SERVICE) as CameraManager
                try {
                    val cameraId: String = cameraManager.getCameraIdList()[0]
                    cameraManager.setTorchMode(cameraId, true)
                    state = true
                    imageButton.setImageResource(R.drawable.torch_on)
                } catch (e: CameraAccessException) {

                }
            } else {
                val cameraManager: CameraManager =
                    getSystemService (Context.CAMERA_SERVICE) as CameraManager
                try {
                    val cameraId: String = cameraManager.getCameraIdList()[0]
                    cameraManager.setTorchMode(cameraId, false)
                    state = false
                    imageButton.setImageResource(R.drawable.torch_off)
                } catch (e: CameraAccessException) {

                }
            }
        }
    }
}