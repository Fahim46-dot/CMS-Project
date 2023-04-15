package com.example.todo.app.ui.post.postAdd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.app.model.postDataItemItem
import com.example.todo.app.repository.postRepositoy
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class postAddViewModel @Inject constructor(
    private val repositoy: postRepositoy
) : ViewModel() {
    private val _addPost: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val addPost: LiveData<Boolean?>
        get() = _addPost

    private fun isValid(title: String, body: String): Boolean {
        if (body.isEmpty()) return false
        if (title.isEmpty()) return false
        return true
    }

    fun addpost(
        userId: Int,
        title: String,
        body: String
    ) = viewModelScope.launch {
        if (!isValid(title, body)) return@launch

        try {
            val postadd = repositoy.addPost(userId, postDataItemItem(body, 0, title, userId))

            if (postadd != null) _addPost.value = true;
            else Log.d("error", "Post is not added")
        } catch (e: ApiException) {
            Log.d("error", "Post is not added")
        }
    }
}
