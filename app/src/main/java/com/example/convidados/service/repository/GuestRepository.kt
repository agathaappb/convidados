package com.example.convidados.service.repository

import com.example.convidados.service.model.GuestModel

class GuestRepository {
    //CRUD - Create / Read / Update / Delete

    //Create!
    fun seveRepository(guest : GuestModel){

    }

    //Read!
    fun getAll():List<GuestModel>{

        val list:MutableList<GuestModel> = ArrayList()

        return list
    }

    fun getPresent(){

    }

    fun getAbsent(){

    }

    //Update
    fun update(guest : GuestModel){

    }

    //Delete
    fun delete(guest : GuestModel){

    }

}