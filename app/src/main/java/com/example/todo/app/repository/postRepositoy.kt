package com.example.todo.app.repository

import android.content.Context
import com.example.todo.app.model.postDataItemItem
import com.example.todo.app.network.SafeApiRequest
import com.example.todo.app.network.api.PosiApiInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class postRepositoy @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: PosiApiInterface
) {
    suspend fun getUsersPosts(userId: Int, page: Int) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getUserPost(userId, page)
        }
    }

    suspend fun getPost(postId: Int) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getPost(postId)
        }
    }

    suspend fun addPost(userId: Int, post: postDataItemItem) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.addPost(userId, post)
        }
    }

    suspend fun deletePost(postId: Int) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.deletePost(postId)
        }
    }

    suspend fun updatePost(postId: Int, post: postDataItemItem) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.updatePost(postId, post)
        }
    }
}
