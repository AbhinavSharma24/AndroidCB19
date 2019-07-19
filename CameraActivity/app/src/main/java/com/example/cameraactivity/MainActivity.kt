package com.example.cameraactivity

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        captureBtn.visibility = View.GONE
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
                camera.setOnClickListener {
                    startCamera()
                    captureBtn.visibility = View.VISIBLE
                    camera.visibility = View.GONE
                }
            }
        }

    }

    private fun startCamera(){
        //to capture image on button click
        val imageCaptureConfig = ImageCaptureConfig.Builder().apply{
            setTargetAspectRatio(Rational(1,1))
            setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
        }.build()

        val imageCapture = ImageCapture(imageCaptureConfig)
        captureBtn.setOnClickListener{
            val file = File(externalMediaDirs.first(),"${System.currentTimeMillis()}.jpg")
            imageCapture.takePicture(file,object : ImageCapture.OnImageSavedListener{
                override fun onImageSaved(file: File) {
                    Toast.makeText(this@MainActivity,"Photo Captured ${file.absolutePath}",Toast.LENGTH_LONG).show()
                }
                override fun onError(useCaseError: ImageCapture.UseCaseError, message: String, cause: Throwable?) {
                    Toast.makeText(this@MainActivity,"Error Capturing $message",Toast.LENGTH_LONG).show()
                }

            })
        }


        //to get the preview
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(1080, 1080))
            setTargetAspectRatio(Rational(1, 1))
            /*setLensFacing(CameraX.LensFacing.FRONT)     //to open front camera by force*/
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {
            val parent = textureView.parent as ViewGroup
            parent.removeView(textureView)
            parent.addView(textureView,0)
            updateView()
            textureView.surfaceTexture = it.surfaceTexture
        }
        CameraX.bindToLifecycle(this,preview,imageCapture)

    }

    private fun updateView(){
        val matrix = Matrix()

        val centerX = textureView.width/2f
        val centerY = textureView.height/2f

        val rotation = when(textureView.display.rotation){
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotation.toFloat(),centerX,centerY)
        textureView.setTransform(matrix)
    }
}

