package com.programming.personalfinance.viewmodel

import androidx.lifecycle.ViewModel
import com.programming.personalfinance.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

}