package com.example.todo.app.ui.add

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
class UserAddFragmentViewModel @Inject constructor(
    private val repository: userRepository
) : ViewModel() {
    private val _addUserSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val addUserSuccess: LiveData<Boolean>
        get() = _addUserSuccess

    private fun isValid(name: String, email: String, gender: String, status: String): Boolean {
        if (name.isBlank()) {
            return false
        }
        if (email.isBlank()) {
            return false
        }
        if (gender.isBlank()) {
            return false
        }
        if (status.isBlank()) return false
        return true
    }

    fun addUser(
        name: String,
        email: String,
        gender: String,
        status: String
    ) = viewModelScope.launch {
        if (!isValid(name, email, gender, status)) return@launch
        try {
            val newUser = repository.createuser(userDataItem(email,gender,0,name,status))

            if(newUser!=null)_addUserSuccess.postValue(true)
            else{
                Log.d("useradd", "User add failed")
            }
        }catch (e: ApiException){
            Log.d("error", "${e.message}")
        }
    }
}
