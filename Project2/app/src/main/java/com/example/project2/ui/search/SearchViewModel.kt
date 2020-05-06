package com.example.project2.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    val url: MutableLiveData<String> = MutableLiveData()
}