package com.convidados.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.convidados.databinding.RowGuestBinding
import com.convidados.model.GuestModel

class GuestViewHolder(val item: RowGuestBinding): RecyclerView.ViewHolder(item.root) {

    fun bind(guest: GuestModel){
       item.guestName.text = guest.name

    }
}