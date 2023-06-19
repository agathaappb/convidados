package com.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.convidados.model.GuestModel
import com.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application): AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val guestId = MutableLiveData<GuestModel>()
    val guests: LiveData<GuestModel> = guestId

    fun insert(guest: GuestModel){
        repository.insert(guest)
    }

    fun getGuest(id:Int){
       guestId.value = repository.getGuestId(id)
    }

    fun update(guest: GuestModel){
        repository.update(guest)
    }




}