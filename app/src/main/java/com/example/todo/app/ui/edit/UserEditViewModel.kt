package com.example.todo.app.ui.edit

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
class UserEditViewModel @Inject constructor(
    private val repository: userRepository
) : ViewModel() {

    private val _updateuser: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val updateuser: LiveData<Boolean>
        get() = _updateuser

    private fun isValid(name: String, email: String, gender: String, status: String): Boolean {
        if (name.isEmpty()) return false
        if (email.isEmpty()) return false
        if (gender.isEmpty()) return false
        if (status.isEmpty()) return false
        return true
    }

    fun update(
        id: Int,
        name: String,
        email: String,
        gender: String,
        status: String
    ) = viewModelScope.launch {
        try {
            val updateUserData =
                repository.updateUser(id, userDataItem(email, gender, 0, name, status))

            if (updateUserData != null) {
                _updateuser.value = true
            }; else {
                Log.d("userUpdateError", "User is not updated")
            }
        } catch (e: ApiException) {
            Log.d("userUpdateError", "${e.message}")
        }
    }
}
