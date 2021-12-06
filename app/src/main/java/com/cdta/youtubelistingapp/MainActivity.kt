package com.cdta.youtubelistingapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdta.youtubelistingapp.adapter.CategoryAdapter
import com.cdta.youtubelistingapp.model.Category
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "BrowserCategories:"
    var categoryList = ArrayList<Category>()
    var adp = CategoryAdapter(categoryList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        category_content.layoutManager=LinearLayoutManager(this)
        category_content.adapter = adp
        loadCategories()
    }

    private fun loadCategories() {
        progress_bar.visibility = View.VISIBLE
        val query = ParseQuery<ParseObject>("Category")
        query.orderByAscending("name")
//        query.whereEqualTo("name","Java")
        query.findInBackground { list, e ->
            progress_bar.visibility = View.GONE
            if (e == null) {
                //No error occured
                Log.d(TAG, "$TAG: No error ocurred when running the query")
                if (list.size > 0) {
                    //there is categories retrived
                    Log.d(TAG, "$TAG: There is " + list.size + "categories retrieved")
                    for (category in list) {
                        categoryList.add(
                            Category(
                                category.objectId, category.getString("name").toString(),
                                category.getParseFile("picture")
                            )
                        )
                        adp.notifyDataSetChanged()

                        Log.d(TAG, "$TAG: categoryList content:  " + categoryList.toString())
                        var nameOfCategories = ""
                        for (c in categoryList){
                            nameOfCategories+=c.name+"\n"
                        }
                    }
                } else {
                    //there is no categories in the app backend
                    Log.d(TAG, "$TAG: There is no categories in the app backend")
                    error_category.visibility = View.VISIBLE
                    error_message.text=getString(R.string.error_load_category)
                }
            } else {
                //there is error occured
                Log.d(TAG, "$TAG: There is an error occured " + e.message)
                error_category.visibility = View.VISIBLE
                refresh.visibility = View.VISIBLE
                error_message.text=getString(R.string.error_network_message)
            }
        }
    }

    fun refresh_category_clicked(v:View){
        error_category.visibility = View.GONE
        refresh.visibility = View.GONE
        error_message.text=""
        loadCategories()
    }
}