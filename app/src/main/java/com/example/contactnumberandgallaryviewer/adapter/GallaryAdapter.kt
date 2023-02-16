package com.example.contactnumberandgallaryviewer.adapter

import android.content.ComponentCallbacks
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactnumberandgallaryviewer.R
import com.example.contactnumberandgallaryviewer.databinding.ItemGallaryBinding
import com.example.contactnumberandgallaryviewer.model.GallaryModel

class GallaryAdapter(val context: Context,
    val gallaryList: ArrayList<GallaryModel>,val callbacks: (GallaryModel,Int,View)-> Unit
) : RecyclerView.Adapter<GallaryAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemGallaryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemGallaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = gallaryList[position]
        Glide.with(context)
            .load(item.displayphoto)
            .placeholder(R.drawable.ic_launcher_background).into(holder.binding.imageforgrid)
        holder.itemView.setOnClickListener {

            callbacks.invoke(gallaryList[position],position,holder.binding.imageforgrid)

        }
    }
    override fun getItemCount(): Int = gallaryList.size
}