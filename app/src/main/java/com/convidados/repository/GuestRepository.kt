package com.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.convidados.model.GuestModel
import com.convidados.utils.Constants

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    //Singleton
    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository{
            if(!::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel):Boolean{
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(Constants.DataBase.NAME, guest.name)
            values.put(Constants.DataBase.PRESENCE, presence)

            db.insert(Constants.DataBase.TABLE_NAME, null, values)

            true

        }catch (e: java.lang.Exception){
            false
        }
    }

    fun update(guest: GuestModel):Boolean{
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0
            val values = ContentValues()
            values.put(Constants.DataBase.NAME, guest.name)
            values.put(Constants.DataBase.PRESENCE, presence)

            val selection = Constants.DataBase.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(Constants.DataBase.TABLE_NAME, values,selection, args)

            true

        }catch (e: java.lang.Exception){
            false
        }
    }


}