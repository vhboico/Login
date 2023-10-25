package com.example.login.listener

interface Listener {

    fun onSuccess(message: String, screen: String)
    fun onFailure(error: String)
}