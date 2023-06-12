package com.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.convidados.utils.Constants

class GuestDataBase(context: Context): SQLiteOpenHelper(context, NAME_DB, null, VERSION_DB) {

    companion object{
        private const val NAME_DB = "guestdb"
        private const val VERSION_DB = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE " + Constants.DataBase.TABLE_NAME +" (" +
                Constants.DataBase.ID + " integer primary key autoincrement," +
                Constants.DataBase.NAME+ " text, " +
                Constants.DataBase.PRESENCE+ " integer);")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}