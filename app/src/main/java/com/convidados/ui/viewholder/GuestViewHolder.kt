package com.convidados.ui.viewholder

import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.convidados.databinding.RowGuestBinding
import com.convidados.model.GuestModel
import com.convidados.ui.listener.OnGuestListener

class GuestViewHolder(val item: RowGuestBinding, val listener:OnGuestListener): RecyclerView.ViewHolder(item.root) {

    fun bind(guest: GuestModel){
       item.guestName.text = guest.name

        item.guestName.setOnClickListener {
            listener.onClick(guest.id)
        }

        item.guestName.setOnLongClickListener {
            listener.onDelete(guest.id)
            true
        }

    }
}