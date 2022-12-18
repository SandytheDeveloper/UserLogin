package com.example.userlogin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    lateinit var splashvideo : VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splashvideo=findViewById(R.id.splashvideo)

        var mc = MediaController(this)

        mc.setAnchorView(splashvideo)
        splashvideo.setVideoURI(Uri.parse("android.resource://"+this.packageName+"/"+R.raw.splashvideo))
        splashvideo.requestFocus()
        splashvideo.start()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            var i =Intent(applicationContext,Home::class.java)
            startActivity(i)
            finish()
        },3000)
    }
}