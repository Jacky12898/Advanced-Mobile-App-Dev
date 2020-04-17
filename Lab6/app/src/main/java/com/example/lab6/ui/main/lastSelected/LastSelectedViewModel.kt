package com.example.lab6.ui.main.lastSelected

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LastSelectedViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Last Selected Park: "
    }
    val text: LiveData<String> = _text
}