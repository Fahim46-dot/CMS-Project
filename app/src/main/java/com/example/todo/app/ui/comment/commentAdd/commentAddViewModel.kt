package com.example.todo.app.ui.comment.commentAdd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.app.model.commentDataItem
import com.example.todo.app.repository.commentRepository
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class commentAddViewModel @Inject constructor(private val repository: commentRepository) :
    ViewModel() {
    private val _comment: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val comment: LiveData<Boolean>
        get() = _comment

    private fun isValid(name: String, email: String, body: String): Boolean {
        if (name.isEmpty()) return false

        if (email.isEmpty()) return false

        if (body.isEmpty()) return false

        return true
    }

    fun addComment(
        postId: Int,
        name: String,
        email: String,
        body: String
    ) = viewModelScope.launch {
        if (!isValid(name, email, body)) return@launch
        try {
            val newComment =
                repository.addComment(postId, commentDataItem(body, email, 0, name, postId))

            if (newComment != null) {
                _comment.value = true
            } else {
                Log.d("useradd", "Comment add failed")
            }
        } catch (e: ApiException) {
            Log.d("error", "${e.message}")
        }
    }
}
