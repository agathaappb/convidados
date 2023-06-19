package com.convidados.ui.viewholder

import android.content.DialogInterface
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que quer remover este convidado?")
                .setPositiveButton("Sim"
                ) { p0, p1 -> listener.onDelete(guest.id) }
                .setNegativeButton("Não"
                ) { p0, p1 -> p0?.cancel() }
                .setNeutralButton("Editar"
                ) { p0, p1 -> listener.onClick(guest.id) }
                .create()
                .show()

            true
        }

    }
}