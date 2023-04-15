package com.example.todo.app.network

import com.example.todo.app.network.ApiClient.Companion.buildClient
import com.example.todo.app.utils.Constants
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiClient {
    companion object {
        private fun buildClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader(
                            "Authorization",
                            "Bearer 42c659224de2fca7722be0801433089e26c9ec6b6ba23b175c52744473caff03"
                        )
                        .build()

                    chain.proceed(request)
                }
                .build()
        }
        /*       Making instance of Retrofit   */

        fun getRetrofit(moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .client(buildClient())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }
    }
}
