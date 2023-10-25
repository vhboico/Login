package com.example.login.viewmodel

import androidx.lifecycle.ViewModel
import com.example.login.repository.RepositoryAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelAPI @Inject constructor(private val repositoryAPI: RepositoryAPI): ViewModel(){
}