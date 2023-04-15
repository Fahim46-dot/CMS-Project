package com.example.todo.app.ui.comment.commentDetails

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
class commentDetailsViewModel @Inject constructor(private val repository: commentRepository) :
    ViewModel() {
    private val _commentDetails: MutableLiveData<commentDataItem> by lazy {
        MutableLiveData<commentDataItem>()
    }
    val commentDetails: LiveData<commentDataItem?>
        get() = _commentDetails

    private val _delete: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val delete: LiveData<Boolean>
        get() = _delete

    fun getComments(commentId: Int) = viewModelScope.launch {
        try {
            val commentData = repository.getComment(commentId)

            _commentDetails.value = commentData
        } catch (e: ApiException) {
            Log.d("error", "Something missing")
        }
    }

    fun delete(commentId: Int) = viewModelScope.launch {
        try {
            repository.deleteComment(commentId)
            _delete.value = true
        } catch (e: ApiException) {
            Log.d("error", "Something happened")
        }
    }
}
