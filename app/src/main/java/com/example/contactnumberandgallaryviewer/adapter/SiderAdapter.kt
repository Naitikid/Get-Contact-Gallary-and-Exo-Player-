package com.example.contactnumberandgallaryviewer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.contactnumberandgallaryviewer.R
import com.example.contactnumberandgallaryviewer.model.GallaryModel
import java.util.*
import kotlin.collections.ArrayList

class SiderAdapter(val context: Context, val imageList: ArrayList<GallaryModel>) : PagerAdapter() {

    override fun getCount(): Int {
        return imageList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View =
            mLayoutInflater.inflate(R.layout.item_show_sideimages, container, false)
        val item = imageList[position]
        Glide.with(context)
            .load(item.displayphoto)
            .placeholder(R.drawable.ic_launcher_background).into(itemView.findViewById(R.id.idIVImage))
        container.addView(itemView)
        return itemView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}