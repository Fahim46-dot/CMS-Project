package com.example.todo.app.ui.post.postEdit

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
class postEditViewModel @Inject constructor(private val repositoy: postRepositoy) : ViewModel() {
    private val _updatePost: MutableLiveData<postDataItemItem> by lazy {
        MutableLiveData<postDataItemItem>()
    }
    val updatePost: LiveData<postDataItemItem?>
        get() = _updatePost

    private val _updateSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val updateSuccess: LiveData<Boolean>
        get() = _updateSuccess

    private fun isValid(title: String, body: String): Boolean {
        if (body.isEmpty()) return false
        if (title.isEmpty()) return false
        return true
    }

    fun updatepost(
        userId: Int,
        postId: Int,
        body: String,
        title: String
    ) = viewModelScope.launch {
        if (!isValid(title, body)) return@launch

        try {
            repositoy.updatePost(postId, postDataItemItem(body, 0, title, userId))
            _updateSuccess.value = true
        } catch (e: ApiException) {
            Log.d("error", "${e.message}")
        }
    }

    fun getDetails(postId: Int) = viewModelScope.launch {
        try {
            val details = repositoy.getPost(postId)

            _updatePost.value = details
        } catch (e: ApiException) {
            Log.d("error", "${e.message}")
        }
    }
}
