package com.cdta.youtubelistingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class VideoList : AppCompatActivity() {

    var selectedCategoryObjectId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)
        setTitle(intent.getStringExtra("selectedCategoryName"))
        selectedCategoryObjectId = intent.getStringExtra("selectedCategoryObjectId").toString()
        Toast.makeText(
            this,
            "Selected video category object Id" + selectedCategoryObjectId,
            Toast.LENGTH_LONG
        ).show()
    }
}