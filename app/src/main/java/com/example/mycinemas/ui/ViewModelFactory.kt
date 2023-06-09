package com.example.mycinemas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycinemas.data.MovieRepository
import com.example.mycinemas.ui.screen.detail.DetailViewModel
import com.example.mycinemas.ui.screen.home.HomeViewModel
import com.example.mycinemas.ui.screen.profile.ProfileViewModel

class ViewModelFactory(private val repository: MovieRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel() as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class" + modelClass.name)
    }
}