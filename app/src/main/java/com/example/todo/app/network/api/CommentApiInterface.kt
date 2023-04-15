package com.example.todo.app.network.api

import com.example.todo.app.model.commentDataItem
import retrofit2.Response
import retrofit2.http.*

interface CommentApiInterface {

    @GET("v2/posts/{postId}/comments")
    suspend fun getComment(
        @Path("postId") postId: Int,
        @Query("page") page: Int
    ): Response<List<commentDataItem>>

    @GET("v2/comments/{commentId}")
    suspend fun getComment(@Path("commentId") commentId: Int): Response<commentDataItem>

    @POST("v2/posts/{postId}/comments")
    suspend fun addComment(
        @Path("postId") postId: Int,
        @Body comment: commentDataItem
    ): Response<commentDataItem>

    @DELETE("v2/comments/{commentId}")
    suspend fun deleteComment(@Path("commentId") commentId: Int): Response<Unit>

    @PUT("v2/comments/{commentId}")
    suspend fun updateComment(
        @Path("commentId") commentId: Int,
        @Body comment: commentDataItem
    ): Response<commentDataItem>
}
