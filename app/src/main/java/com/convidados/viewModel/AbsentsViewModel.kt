package com.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.convidados.model.GuestModel
import com.convidados.repository.GuestRepository

class AbsentsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listGuestAbsent = MutableLiveData<List<GuestModel>>()
    val guestsAbsents: LiveData<List<GuestModel>> = listGuestAbsent

    fun getAbsent(presence:Int){
        listGuestAbsent.value = repository.getPresence(presence)
    }


}