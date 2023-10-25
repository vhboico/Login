package com.example.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.listener.Listener
import com.example.login.repository.RepositoryAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelAuth @Inject constructor(private val repositoryAuth: RepositoryAuth): ViewModel() {

    fun signUp(name: String, email: String, password: String, listener: Listener){
        viewModelScope.launch {
            repositoryAuth.signUp(name, email, password, listener)
        }
    }

    fun login(email: String, password: String, listener: Listener){
        viewModelScope.launch {
            repositoryAuth.login(email, password, listener)
        }
    }
}