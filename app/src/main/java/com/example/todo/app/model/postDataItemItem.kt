package com.example.todo.app.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class postDataItemItem(
    @Json(name = "body")
    val body: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "user_id")
    val userId: Int
)