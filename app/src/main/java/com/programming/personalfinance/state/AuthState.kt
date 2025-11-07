package com.programming.personalfinance.state

sealed class AuthState<out T> {
    object Loading : AuthState<Nothing>()
    data class Success<out T>(val data: T) : AuthState<T>()
    data class Error(val message: String) : AuthState<Nothing>()
}
