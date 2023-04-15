package com.example.todo.app.ui.post.postDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.app.model.postDataItemItem
import com.example.todo.app.repository.postRepositoy
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class postDetailsViewModel @Inject constructor(
    private val repository: postRepositoy
) : ViewModel() {
    private val _postDetails: MutableLiveData<postDataItemItem?> by lazy {
        MutableLiveData<postDataItemItem?>()
    }
    val postDetails: LiveData<postDataItemItem?>
        get() = _postDetails

    private val _eventDeleteSuccess: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    val eventDeleteSuccess: LiveData<Boolean?>
        get() = _eventDeleteSuccess

    fun getDetails(postId: Int) = viewModelScope.launch {
        try {
            val post = repository.getPost(postId)

            _postDetails.value = post
        } catch (e: ApiException) {
            Log.d("error", "%${e.message}")
        }
    }
    fun deletePost(postId: Int) = viewModelScope.launch {
        try {
            repository.deletePost(postId)

            _eventDeleteSuccess.postValue(true)
        } catch (e: ApiException) {
            Log.d("error", "${e.message}")
        }
    }
}
