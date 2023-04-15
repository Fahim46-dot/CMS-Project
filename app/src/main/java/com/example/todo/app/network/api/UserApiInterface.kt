package com.example.todo.app.network.api

import com.example.todo.app.model.userDataItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiInterface {
    @GET("v2/users")
    suspend fun getUser(): Response<List<userDataItem>>

    @GET("v2/users/{id}")
    suspend fun getUserDetails(@Path("id") userId: Int): Response<userDataItem>

    // For updating any user's data
    @PUT("v2/users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body userItem: userDataItem
    ): Response<userDataItem>

    @DELETE("v2/users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<userDataItem>

    //For user adding
    @POST("v2/users")
    suspend fun createUser(@Body user: userDataItem): Response<userDataItem>
}
