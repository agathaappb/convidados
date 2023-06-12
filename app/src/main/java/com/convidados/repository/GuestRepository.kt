package com.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.convidados.model.GuestModel
import com.convidados.utils.Constants

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    //Singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(Constants.DataBase.NAME, guest.name)
            values.put(Constants.DataBase.PRESENCE, presence)

            db.insert(Constants.DataBase.TABLE_NAME, null, values)

            true

        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0
            val values = ContentValues()
            values.put(Constants.DataBase.NAME, guest.name)
            values.put(Constants.DataBase.PRESENCE, presence)

            val selection = Constants.DataBase.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(Constants.DataBase.TABLE_NAME, values, selection, args)

            true

        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selection = Constants.DataBase.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(Constants.DataBase.TABLE_NAME, selection, args)

            true

        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {

        val listGuest = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                Constants.DataBase.ID,
                Constants.DataBase.NAME,
                Constants.DataBase.PRESENCE
            )

            val cursor = db.query(Constants.DataBase.TABLE_NAME, projection, null, null, null, null, null)

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(Constants.DataBase.ID))
                    val name = cursor.getString(cursor.getColumnIndex(Constants.DataBase.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(Constants.DataBase.PRESENCE))

                    listGuest.add(GuestModel(id, name, presence==1))
                }
            }

            cursor.close()

        }catch (e: java.lang.Exception){
            return listGuest
        }
        return listGuest
    }

    fun getPresence(status: Int): List<GuestModel> {

        val listGuest = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                Constants.DataBase.ID,
                Constants.DataBase.NAME,
                Constants.DataBase.PRESENCE
            )

            val selection = Constants.DataBase.PRESENCE + " = ?"
            val args = arrayOf("${status}")

            val cursor = db.query(Constants.DataBase.TABLE_NAME, projection, selection, args, null, null, null)

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(Constants.DataBase.ID))
                    val name = cursor.getString(cursor.getColumnIndex(Constants.DataBase.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(Constants.DataBase.PRESENCE))

                    listGuest.add(GuestModel(id, name, presence==1))
                }
            }

            cursor.close()

        }catch (e: java.lang.Exception){
            return listGuest
        }
        return listGuest
    }


}