package com.example.todo.app.ui.comment.commentList

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
class commentListViewModel @Inject constructor(
    private val repository: commentRepository
) : ViewModel() {
    private val _comment: MutableLiveData<List<commentDataItem>> by lazy {
        MutableLiveData<List<commentDataItem>>()
    }

    val comment: LiveData<List<commentDataItem>?>
        get() = _comment

    fun getComment(commentId: Int, page: Int) = viewModelScope.launch {
        try {
            val result = repository.getCommentList(commentId, page)

            _comment.value = result
        } catch (e: ApiException) {
            _comment.value = listOf()
            Log.d("error", "${e.message}")
        }
    }
}
