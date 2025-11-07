package com.programming.personalfinance.repository

import com.programming.personalfinance.model.User

interface UserRepository {
    fun saveUserAuth(user: User, onResult: (success: Boolean, message: String) -> Unit)
    fun signInAccount(user: User, onResult: (success: Boolean, message: String) -> Unit)
    fun saveUser(user: User)
}