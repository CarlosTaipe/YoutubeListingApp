package com.cdta.youtubelistingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdta.youtubelistingapp.adapter.VideoAdapter
import com.cdta.youtubelistingapp.model.Category
import com.cdta.youtubelistingapp.model.Video
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_video_list.*

class VideoList : AppCompatActivity() {

    val TAG = "VideoList:"
    var videoList = ArrayList<Video>()
    var adp = VideoAdapter(
        videoList, { videoItem: Video -> clickListener(videoItem) }
    )

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

        video_content.layoutManager = LinearLayoutManager(this)
        video_content.adapter=adp
        Log.d(TAG, "$TAG: before loading videos")
        loadVideos()
    }

    private fun loadVideos() {
//        progress_bar.visibility = View.VISIBLE
        val query = ParseQuery<ParseObject>("Video")
        if (selectedCategoryObjectId != null) {
            val categoryPointer =
                ParseObject.createWithoutData("Category", selectedCategoryObjectId)
            query.whereEqualTo("category", categoryPointer)
        }
        query.findInBackground { list, e ->
//            progress_bar.visibility = View.GONE
            if (e == null) {
                //No error occured
                Log.d(TAG, "$TAG: No error occured when running the query: list size " + list.size)
                if (list.size > 0) {
                    //there is categories retrived
                    Log.d(TAG, "$TAG: There is " + list.size + "videos retrieved")
                    for (video in list) {
                        videoList.add(
                            Video(
                                video.objectId,
                                video.get("youtubeId").toString(),
                                video.get("title").toString()
                            )
                        )
                    }
                        adp.notifyDataSetChanged()

                    Log.d(TAG, "$TAG: videosList content:  " + videoList.toString())
                    var nameOfVideos = ""
                    for (v in videoList) {
                        nameOfVideos += v.title + "\n"
                    }
                    Log.d(TAG, "$TAG: titles of retrived videos:  " + nameOfVideos)

                } else {
                    //there is no categories in the app backend
                    Log.d(TAG, "$TAG: There is no videos in the app backend")
//                    error_category.visibility = View.VISIBLE
//                    error_message.text = getString(R.string.error_load_category)
                }
            } else {
                //there is error occured
//                Log.d(TAG, "$TAG: There is an error occured " + e.message)
//                error_category.visibility = View.VISIBLE
//                refresh.visibility = View.VISIBLE
//                error_message.text = getString(R.string.error_network_message)
            }
        }
    }

    fun clickListener(video: Video) {
        val intent = Intent(this, PlayVideo::class.java)
        intent.putExtra("youtubeId", video.youtubeId)
        startActivity(intent)
    }

}