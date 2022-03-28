package com.example.convidados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class GuestFormActivity : AppCompatActivity() {

    private lateinit var btnBackToolbar:ImageView
    private lateinit var textTitleToolbar:TextView
    private lateinit var textNameGuestForm: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        setView()
        backToolbar()
        setTitleToolbar()

    }

    private fun setView(){
        btnBackToolbar = findViewById(R.id.btn_back_toolbar)
        textTitleToolbar = findViewById(R.id.text_title_toolbar)
        textNameGuestForm = findViewById(R.id.txt_nome_guest_form)

    }

    private fun backToolbar(){
        btnBackToolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setTitleToolbar(){
        textTitleToolbar.text = "Cadastro de convidado"
    }
}