package com.cdta.youtubelistingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cdta.youtubelistingapp.R
import com.cdta.youtubelistingapp.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(var list:ArrayList<Category>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CategoryViewHolder(view: View):RecyclerView.ViewHolder(view){
        var cat_image = view.cat_image
        var cat_name = view.cat_name
        fun bind(category: Category){
            //link category name to textView
            cat_name.text=category.name
            //link category image to imageView
        }
    }
}