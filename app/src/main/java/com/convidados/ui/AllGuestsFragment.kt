package com.convidados.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.convidados.databinding.FragmentAllGuestsBinding
import com.convidados.ui.adapter.GuestsAdapter
import com.convidados.ui.listener.OnGuestListener
import com.convidados.viewModel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.listGuestAll.layoutManager = LinearLayoutManager(context)

        binding.listGuestAll.adapter = adapter

        val listener = object : OnGuestListener{
            override fun onClick(id:Int) {
                Toast.makeText(requireContext(), "Click - ID: ${id}",Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id:Int) {
                allGuestsViewModel.delete(id)
                allGuestsViewModel.getAll()
            }
        }

        adapter.attachListener(listener)

        allGuestsViewModel.getAll()

        allGuestsViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}