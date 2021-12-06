package com.cdta.youtubelistingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cdta.youtubelistingapp.model.Category
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val TAG = "BrowserCategories:"
    var categoryList = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCategories()
    }

    private fun loadCategories() {
        val query = ParseQuery<ParseObject>("Category")
        query.orderByAscending("name")
        query.findInBackground { list, e ->
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
                        Log.d(TAG, "$TAG: categoryList content:  " + categoryList.toString())
                        var nameOfCategories = ""
                        for (c in categoryList){
                            nameOfCategories+=c.name+"\n"
                        }
                    }
                } else {
                    //there is no categories in the app backend
                    Log.d(TAG, "$TAG: There is no categories in the app backed")
                }
            } else {
                //there is error occured
                Log.d(TAG, "$TAG: There is an error occured " + e.message)
            }
        }
    }
}