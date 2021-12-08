package com.cdta.youtubelistingapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_play_video.*

class PlayVideo : AppCompatActivity() {

    val TAG = "PlayVideo:"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_play_video)

        val videoYoutubeId = intent.getStringExtra("youtubeId")
        Log.d(TAG,"$TAG: Selected video id " + videoYoutubeId)
        var videoURL = "https://www.youtube.com/embed/" + videoYoutubeId

        my_web_view.settings.javaScriptEnabled = true
        my_web_view.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                loading_play_video_message.text =
                    getString(R.string.loading_youtube_video_message) + " " + newProgress + " %"
                if (newProgress == 100) {
                    progress_bar.visibility = View.GONE
                    my_web_view.visibility = View.VISIBLE
                }
            }
        }
        my_web_view.loadUrl(videoURL)
    }
}