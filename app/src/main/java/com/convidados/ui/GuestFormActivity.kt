package com.convidados.ui

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.convidados.databinding.ActivityGuestFormBinding
import com.convidados.model.GuestModel
import com.convidados.utils.Constants
import com.convidados.viewModel.GuestFormViewModel


class GuestFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModel(this)
        setView()
        editText()
        observeData()
        save()
        getData()

    }

    private fun setViewModel(owner: ViewModelStoreOwner){
        viewModel = ViewModelProvider(owner).get(GuestFormViewModel::class.java)
        binding.guestFormBtnSave.isEnabled = false
    }

    private fun setView(){
        binding.guestFormBtnPresent.isChecked = true
    }

    private fun save(){
        binding.guestFormBtnSave.setOnClickListener {
            getData()
            binding.guestFormTextName.text?.clear()
            editText()
            finish()
        }
    }

    private fun getData(){
        val name = binding.guestFormTextName.text.toString()
        val presence = binding.guestFormBtnPresent.isChecked

        val bundle = intent.extras
        if (bundle != null){
            this.id = bundle.getInt(Constants.Bundle.GUEST_ID)
            viewModel.getGuest(id)
            viewModel.update(GuestModel(id,name,presence))
        }else{
            viewModel.insert(GuestModel(id,name,presence))
        }
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

    private fun observeData(){
        viewModel.guests.observe(this, Observer {
            binding.guestFormTextName.setText(it.name)
            if (it.presence){
                binding.guestFormBtnPresent.isChecked = true
            }else{
                binding.guestFormBtnAbsent.isChecked = true
            }
        })
    }
}