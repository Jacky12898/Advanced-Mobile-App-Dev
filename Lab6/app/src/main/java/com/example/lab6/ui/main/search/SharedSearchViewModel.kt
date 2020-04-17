package com.example.lab6.ui.main.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab6.data.Park
import com.example.lab6.data.ParkRepository

class SharedSearchViewModel(app: Application) : AndroidViewModel(app) {
    private val parkRepo = ParkRepository(app)
    val parkData = parkRepo.parkData
    val parkDetails = parkRepo.parkDetails
    val selectedPark = MutableLiveData<Park>()
    val searchUserInput = MutableLiveData<String>()

    init {
        selectedPark.observeForever(parkRepo.parkSelectedObserver)
        searchUserInput.observeForever(parkRepo.searchTermEntered)
    }

    override fun onCleared() {
        selectedPark.removeObserver(parkRepo.parkSelectedObserver)
        searchUserInput.removeObserver(parkRepo.searchTermEntered)
        super.onCleared()
    }
}
