package com.convidados.ui

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.convidados.databinding.ActivityGuestFormBinding
import com.convidados.model.GuestModel
import com.convidados.viewModel.GuestFormViewModel


class GuestFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModel(this)
        setView()
        save()
        editText()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setViewModel(owner: ViewModelStoreOwner){
        viewModel = ViewModelProvider(owner).get(GuestFormViewModel::class.java)
        binding.guestFormBtnSave.isEnabled = false
    }

    private fun setView(){
        binding.guestFormBtnPresent.isChecked = true
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun save(){
        binding.guestFormBtnSave.setOnClickListener {
            getData()
            binding.guestFormTextName.text?.clear()
            editText()
        }
    }

    private fun getData(){
        val id = 0
        val name = binding.guestFormTextName.text.toString()
        val presence = binding.guestFormBtnPresent.isChecked

        viewModel.insert(GuestModel(id,name,presence))
        Toast.makeText(this,"nome: ${name} | Presença: ${presence}",Toast.LENGTH_SHORT).show()
    }

    private fun editText(){
        binding.guestFormTextName.addTextChangedListener( object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                binding.guestFormBtnSave.isEnabled = !binding.guestFormTextName.text.isNullOrBlank()
            }

        })
    }
}