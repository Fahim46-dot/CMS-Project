package com.example.todo.app.network.api

import com.example.todo.app.model.postDataItemItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PosiApiInterface {
    @GET("v2/users/{userId}/posts")
    suspend fun getUserPost(
        @Path("userId") userId: Int,
        @Query("page") page: Int
    ): Response<List<postDataItemItem>>

    @POST("v2/users/{userId}/posts")
    suspend fun addPost(
        @Path("userId") userId: Int,
        @Body post: postDataItemItem
    ): Response<postDataItemItem>

    @GET("v2/posts/{postId}")
    suspend fun getPost(
        @Path("postId") postId: Int
    ): Response<postDataItemItem>

    @DELETE("v2/posts/{postId}")
    suspend fun deletePost(@Path("postId")postId:Int):Response<Unit>

    @PUT("v2/posts/{postId}")
    suspend fun updatePost(@Path("postId")postId: Int, @Body post:postDataItemItem): Response<postDataItemItem>
}
