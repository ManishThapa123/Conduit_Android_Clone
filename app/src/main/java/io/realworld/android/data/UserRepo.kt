package io.realworld.android.data

import androidx.lifecycle.viewModelScope
import io.realworld.api.ConduitClient
import io.realworld.api.models.entities.LoginData
import io.realworld.api.models.entities.User
import io.realworld.api.models.entities.UserCreds
import io.realworld.api.models.entities.UserUpdateData
import io.realworld.api.models.requests.LoginRequest
import io.realworld.api.models.requests.SignupRequest
import io.realworld.api.models.requests.UserUpdateRequest
import io.realworld.api.models.responses.UserResponse
import kotlinx.coroutines.launch

object UserRepo {

    val api = ConduitClient.publicApi
    val authAPI = ConduitClient.authApi

    suspend fun login(email: String, password: String): User? {
        val response = api.loginUser(LoginRequest(LoginData(email,password)))

        //TODO : Save it in Shared Preference
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun signup(username: String, email: String, password: String): User?{
        val response = api.signupUser(SignupRequest(UserCreds(email, password, username)))

        //TODO : Save it in Shared Preference
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun getCurrentUser(token: String): User? {
        ConduitClient.authToken = token
        return authAPI.getCurrentUser().body()?.user
    }


   suspend fun update(
        bio: String?,
        username: String?,
        image: String?,
        password: String?,
        email: String?): User?{
       val response = authAPI.updateCurrentUser(UserUpdateRequest(UserUpdateData(bio, email, image, username, password)))

       return response.body()?.user
    }
    suspend fun getUserProfile() = authAPI.getCurrentUser().body()

//    suspend fun login2(email: String, password: String) = api.loginUser(LoginRequest(LoginData(email, password))).body()
}