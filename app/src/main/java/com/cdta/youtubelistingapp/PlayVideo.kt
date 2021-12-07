package com.cdta.youtubelistingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_play_video.*

class PlayVideo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)


        val videoYoutubeId = intent.getStringExtra("youtubeId")
        var videoURL = "https://www.youtube.com/embed/"+videoYoutubeId

//        Toast.makeText(
//            this,
//            "Selected video id " + videoYoutubeId,
//            Toast.LENGTH_LONG
//        ).show()

        my_web_view.settings.javaScriptEnabled=true
        my_web_view.loadUrl(videoURL)
    }
}