package io.realworld.android.ui.auth

import androidx.lifecycle.*
import io.realworld.android.data.UserRepo
import io.realworld.api.models.entities.User
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user

    fun login(email: String, password: String) = viewModelScope.launch {
        UserRepo.login(email, password)?.let {
            _user.postValue(it.user)
        }
    }
}