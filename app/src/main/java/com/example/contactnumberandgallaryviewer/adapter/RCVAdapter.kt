package com.example.contactnumberandgallaryviewer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactnumberandgallaryviewer.databinding.ItemContactBinding
import com.example.contactnumberandgallaryviewer.model.ContactModel

class RCVAdapter(
    val contactList: ArrayList<ContactModel>
) : RecyclerView.Adapter<RCVAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = contactList[position]
        holder.binding.contactname.text = item.displayName
        holder.binding.mobilenumber.text = item.number
    }

    override fun getItemCount(): Int = contactList.size
}