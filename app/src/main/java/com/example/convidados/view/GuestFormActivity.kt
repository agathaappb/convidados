package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewmodel.GuestFormViewModel
import com.example.convidados.R

class GuestFormActivity : AppCompatActivity() {

    private lateinit var btnBackToolbar : ImageView
    private lateinit var textTitleToolbar : TextView
    private lateinit var textNameGuestForm : TextView
    private lateinit var btnSave : Button
    private lateinit var btnPresent : RadioButton
    private lateinit var mViewModel: GuestFormViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setView()
        backToolbar()
        setTitleToolbar()

    }

    private fun setView(){
        btnBackToolbar = findViewById(R.id.btn_back_toolbar)
        textTitleToolbar = findViewById(R.id.text_title_toolbar)
        textNameGuestForm = findViewById(R.id.txt_nome_guest_form)
        btnSave = findViewById(R.id.btn_save)
        btnPresent = findViewById(R.id.btn_present)

    }

    private fun backToolbar(){
        btnBackToolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setTitleToolbar(){
        textTitleToolbar.text = "Cadastro de convidado"
    }

    private fun saveInfo(){
        btnSave.setOnClickListener {
            val name = textNameGuestForm.text.toString()
            val presence = btnPresent.isChecked

            mViewModel.save(name,presence)

        }
    }

    private fun observeSaveInfo(){
        mViewModel.saveGuest.observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext,"Sucesso! o/",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Falha! x.x",Toast.LENGTH_SHORT).show()
            }
        })
    }
}