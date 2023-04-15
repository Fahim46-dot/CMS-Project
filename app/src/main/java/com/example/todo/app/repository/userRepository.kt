package com.example.todo.app.repository

import android.content.Context
import com.example.todo.app.model.userDataItem
import com.example.todo.app.network.SafeApiRequest
import com.example.todo.app.network.api.UserApiInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class userRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: UserApiInterface
) {

    /*       Making network call to get user   */
    suspend fun getUsers() = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getUser()
        }
    }

    suspend fun getUserDetails(userId: Int) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getUserDetails(userId)
        }
    }
    suspend fun updateUser(id: Int, user: userDataItem) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.updateUser(id, user)
        }
    }
    suspend fun deleteUser(id: Int) = withContext(Dispatchers.IO){
        SafeApiRequest.apiRequest(context){
            api.deleteUser(id)
        }
    }
    suspend fun createuser(user:userDataItem) = withContext(Dispatchers.IO){
        SafeApiRequest.apiRequest(context){
            api.createUser(user)
        }
    }
}
