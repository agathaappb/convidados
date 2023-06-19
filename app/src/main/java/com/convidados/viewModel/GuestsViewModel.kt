package com.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.convidados.model.GuestModel
import com.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listGuest = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listGuest

    fun getAll(){
        listGuest.value = repository.getAll()
    }

    fun delete(id:Int){
        repository.delete(id)
    }

    fun getPresence(presence:Int){
        listGuest.value = repository.getPresence(presence)
    }
}