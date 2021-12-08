package com.cdta.youtubelistingapp.model

import com.parse.ParseFile

data class Category (
    var objectId: String,
    var name: String,
    var picture: ParseFile?
)