package com.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.convidados.model.GuestModel
import com.convidados.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listGuest = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listGuest

    fun getAll(){
        listGuest.value = repository.getAll()
    }
}