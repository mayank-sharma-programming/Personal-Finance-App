package com.programming.personalfinance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programming.personalfinance.model.User
import com.programming.personalfinance.repository.UserRepository
import com.programming.personalfinance.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _authResult = MutableLiveData<AuthState<String>>()
    val authResult: LiveData<AuthState<String>> = _authResult
    fun saveUserAuth(user: User) {
        viewModelScope.launch {
            _authResult.value = AuthState.Loading

            userRepository.saveUserAuth(user) { status, msg ->
                if (status) {
                    _authResult.value = AuthState.Success(msg)
                }else{
                    _authResult.value = AuthState.Error(msg)
                }
            }
        }
    }
    fun signInAccount(user: User) {
        viewModelScope.launch {
            _authResult.value = AuthState.Loading

            userRepository.signInAccount(user) { status, msg ->
                if (status) {
                    _authResult.value = AuthState.Success(msg)
                }else{
                    _authResult.value = AuthState.Error(msg)
                }
            }
        }
    }
}