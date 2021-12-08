package com.cdta.youtubelistingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cdta.youtubelistingapp.R
import com.cdta.youtubelistingapp.model.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_video_list.view.*

class VideoAdapter(var list:ArrayList<Video>, val clickListener:(Video)->Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_video_list,parent,false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VideoViewHolder).bind(list[position],clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class VideoViewHolder(view: View):RecyclerView.ViewHolder(view){
        var vid_image = view.vid_image
        var vid_title = view.vid_title
        fun bind(video:Video, clickListener: (Video) -> Unit){
            //link category name to textView
            vid_title.text=video.title
            //link category image to imageView
            if (video.youtubeId!=null){
                var videoPictureURL= "https://img.youtube.com/vi/"+video.youtubeId+"/0.jpg"
                Picasso.get().load(videoPictureURL).into(vid_image)
            }
            itemView.setOnClickListener {
                clickListener(video)
            }
        }
    }
}