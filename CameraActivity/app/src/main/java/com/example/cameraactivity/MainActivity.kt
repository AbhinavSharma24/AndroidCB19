package com.example.cameraactivity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Rational
import android.util.Size
import android.view.ViewGroup
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requests()
    }

    private fun requests() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                1234
            )
            camera.setOnClickListener {
                requests()
            }
        }
        else {
            textureView.post{
                startCamera()
            }
        }

    }

    private fun startCamera(){
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(1080, 1080))
            setTargetAspectRatio(Rational(1, 1))
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {
            val parent = textureView.parent as ViewGroup
            parent.removeView(textureView)
            parent.addView(textureView,0)

            textureView.surfaceTexture = it.surfaceTexture
        }
        CameraX.bindToLifecycle(this,preview)
    }
}

