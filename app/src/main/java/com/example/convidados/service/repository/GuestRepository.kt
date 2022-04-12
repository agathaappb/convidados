package com.example.convidados.service.repository

import android.content.Context
import com.example.convidados.service.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val mGuestDataBaseHelper : GuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object{
        private lateinit var repository : GuestRepository

        fun getInstance(context: Context) : GuestRepository{
            if(!::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun seveRepository(guest : GuestModel){

    }

    fun getAll():List<GuestModel>{

        val list:MutableList<GuestModel> = ArrayList()

        return list
    }

    fun getPresent(){

    }

    fun getAbsent(){

    }

    fun update(guest : GuestModel){

    }

    fun delete(guest : GuestModel){

    }
}