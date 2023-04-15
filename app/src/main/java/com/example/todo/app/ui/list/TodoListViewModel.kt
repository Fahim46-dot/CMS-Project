package com.example.todo.app.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.app.model.userDataItem
import com.example.todo.app.network.ApiException
import com.example.todo.app.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TodoListViewModel @Inject constructor(private val userRepository: userRepository) :
    ViewModel() {
    private val _user: MutableLiveData<List<userDataItem>> by lazy {
        MutableLiveData<List<userDataItem>>()
    }

    val user: LiveData<List<userDataItem>?>
        get() = _user

    fun getusers() = viewModelScope.launch {
        try {
            val userResponse = userRepository.getUsers()

            _user.value = userResponse
        } catch (e: ApiException) {
            _user.value = listOf()
            Log.d("userTag", "${e.message}")
        }
    }
}
