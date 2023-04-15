package com.example.todo.app.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class commentDataItem(
    @Json(name = "body")
    val body: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "post_id")
    val postId: Int
)