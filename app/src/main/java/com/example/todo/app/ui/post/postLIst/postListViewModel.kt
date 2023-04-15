package com.example.todo.app.ui.post.postLIst

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.example.todo.app.model.postDataItem
import com.example.todo.app.model.postDataItemItem
import com.example.todo.app.model.userDataItem
import com.example.todo.app.repository.postRepositoy
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class postListViewModel @Inject constructor(
    private val repositoy: postRepositoy
) : ViewModel() {
    private val _post: MutableLiveData<List<postDataItemItem>> by lazy {
        MutableLiveData<List<postDataItemItem>>()
    }

    val post: LiveData<List<postDataItemItem>?>
        get() = _post

    fun getPost(userId: Int) = viewModelScope.launch {
        try {
            val result = repositoy.getUsersPosts(userId, 1)
            _post.value = result
        } catch (e: ApiException) {
            _post.value = listOf()

            Log.d("error", "${e.message}")
        }
    }

}
