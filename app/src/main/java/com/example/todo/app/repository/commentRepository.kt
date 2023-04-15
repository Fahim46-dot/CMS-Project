package com.example.todo.app.repository

import android.content.Context
import com.example.todo.app.model.commentDataItem
import com.example.todo.app.network.SafeApiRequest
import com.example.todo.app.network.api.CommentApiInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class commentRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: CommentApiInterface
) {
    suspend fun getCommentList(postId: Int, page: Int) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getComment(postId, page)
        }
    }

    suspend fun getComment(commentId: Int) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getComment(commentId)
        }
    }

    suspend fun addComment(postId: Int, comment: commentDataItem) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.addComment(postId, comment)
        }
    }

    suspend fun deleteComment(commentId: Int) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.deleteComment(commentId)
        }
    }

    suspend fun updateComment(commentId: Int, comment: commentDataItem) =
        withContext(Dispatchers.IO) {
            SafeApiRequest.apiRequest(context) {
                api.updateComment(commentId, comment)
            }
        }
}
