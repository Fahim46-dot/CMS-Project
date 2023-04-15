package com.example.todo.app.ui.comment.commentEdit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.app.model.commentDataItem
import com.example.todo.app.model.postDataItemItem
import com.example.todo.app.repository.commentRepository
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class commentEditViewModel @Inject constructor(private val repository: commentRepository) :
    ViewModel() {
    private val _updateComment: MutableLiveData<commentDataItem> by lazy {
        MutableLiveData<commentDataItem>()
    }
    val updateComment: LiveData<commentDataItem?>
        get() = _updateComment

    private val _updateSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val updateSuccess: LiveData<Boolean>
        get() = _updateSuccess

    private fun isValid(name: String, email: String, body: String): Boolean {
        if (body.isEmpty()) return false
        if (name.isEmpty()) return false
        if (email.isEmpty()) return false
        return true
    }

    fun updateComment(
        commentId: Int,
        name: String,
        email: String,
        body: String,
        postId: Int

    ) = viewModelScope.launch {
        if (!isValid(name, email, body)) return@launch

        try {
            repository.updateComment(commentId, commentDataItem(body, email, 0, name, postId))
            _updateSuccess.value = true
        } catch (e: ApiException) {
            Log.d("error", "${e.message}")
        }
    }

    fun getDetails(commentId: Int) = viewModelScope.launch {
        try {
            val details = repository.getComment(commentId)

            _updateComment.value = details
        } catch (e: ApiException) {
            Log.d("error", "${e.message}")
        }
    }
}