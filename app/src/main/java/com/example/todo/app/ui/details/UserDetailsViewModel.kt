package com.example.todo.app.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.app.model.userDataItem
import com.example.todo.app.repository.userRepository
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: userRepository
) : ViewModel() {
    private val _userdetails: MutableLiveData<userDataItem?> by lazy {
        MutableLiveData<userDataItem?>()
    }

    private val _eventDeleteSuccess: MutableLiveData<Boolean?> by lazy {
        MutableLiveData<Boolean?>()
    }

    val eventDeleteSuccess: LiveData<Boolean?>
        get() = _eventDeleteSuccess

    val userdetails: LiveData<userDataItem?>
        get() = _userdetails

    fun getDetails(
        userId: Int
    ) = viewModelScope.launch {
        try {
            val details = repository.getUserDetails(userId)

            _userdetails.value = details
        } catch (e: ApiException) {
            Log.d("error", "${e.message}")
        }
    }

    fun delete(userId: Int) = viewModelScope.launch {
        try {
            repository.deleteUser(userId)

            _eventDeleteSuccess.postValue(true)
        } catch (e: ApiException) {
            Log.d("error", "${e.message}")
        }
    }
}
