package com.example.login.repository

import com.example.login.datasource.DataSource
import com.example.login.listener.Listener
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryAuth @Inject constructor(private val dataSource: DataSource) {

    fun signUp(name: String, email: String, password: String, listener: Listener){
        dataSource.signUp(name, email, password, listener)
    }

    fun login(email: String, password: String, listener: Listener){
        dataSource.login(email, password, listener)
    }

    fun recoverPassword(email: String, listener: Listener){
        dataSource.recoverPassword(email, listener)
    }
}