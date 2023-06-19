package com.convidados.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.convidados.databinding.FragmentAbsentsBinding
import com.convidados.ui.adapter.GuestsAdapter
import com.convidados.ui.listener.OnGuestListener
import com.convidados.utils.Constants
import com.convidados.viewModel.AbsentsViewModel
import com.convidados.viewModel.GuestsViewModel


class AbsentsFragment : Fragment() {

    private var _binding: FragmentAbsentsBinding? = null
    private val binding get() = _binding!!
    private val adapter = GuestsAdapter()
    private lateinit var absentViewModel: GuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        absentViewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentAbsentsBinding.inflate(inflater, container, false)


        binding.listGuestAbsent.layoutManager = LinearLayoutManager(context)
        binding.listGuestAbsent.adapter = adapter

        absentViewModel.getPresence(0)

        val listener = object : OnGuestListener {
            override fun onClick(id:Int) {
                val intent = Intent(requireActivity(),GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(Constants.Bundle.GUEST_ID,id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id:Int) {
                absentViewModel.delete(id)
                absentViewModel.getAll()
            }
        }

        adapter.attachListener(listener)

        absentViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        absentViewModel.getPresence(0)
    }
}