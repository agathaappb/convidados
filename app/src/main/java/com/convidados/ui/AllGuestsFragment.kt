package com.convidados.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.convidados.databinding.FragmentAllGuestsBinding
import com.convidados.ui.adapter.GuestsAdapter
import com.convidados.ui.listener.OnGuestListener
import com.convidados.utils.Constants
import com.convidados.viewModel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var guestsViewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        guestsViewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.listGuestAll.layoutManager = LinearLayoutManager(context)

        binding.listGuestAll.adapter = adapter

        val listener = object : OnGuestListener{
            override fun onClick(id:Int) {
                val intent = Intent(requireActivity(),GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(Constants.Bundle.GUEST_ID,id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id:Int) {
                guestsViewModel.delete(id)
                guestsViewModel.getAll()
            }
        }

        adapter.attachListener(listener)

        guestsViewModel.getAll()

        guestsViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        guestsViewModel.getAll()
    }

}