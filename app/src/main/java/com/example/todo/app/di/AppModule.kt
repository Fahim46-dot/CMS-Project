package com.example.todo.app.di

import com.example.todo.app.network.ApiClient
import com.example.todo.app.network.api.CommentApiInterface
import com.example.todo.app.network.api.PosiApiInterface
import com.example.todo.app.network.api.UserApiInterface
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            //.add(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return ApiClient.getRetrofit(moshi)
    }

    @Provides
    @Singleton
    fun provideUserApiInterface(retrofit: Retrofit): UserApiInterface {
        return retrofit.create(UserApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun providePostApiInterface(retrofit: Retrofit):PosiApiInterface{
        return retrofit.create(PosiApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentApiInterface(retrofit: Retrofit):CommentApiInterface{
        return retrofit.create(CommentApiInterface::class.java)
    }
}
