package com.example.contactnumberandgallaryviewer.datashow

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.contactnumberandgallaryviewer.R
import com.example.contactnumberandgallaryviewer.adapter.SiderAdapter
import com.example.contactnumberandgallaryviewer.databinding.ActivityShowBinding
import com.example.contactnumberandgallaryviewer.model.GallaryModel
import com.example.contactnumberandgallaryviewer.utils.Const
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates


class ShowActivity : AppCompatActivity() {

    lateinit var viewPagerAdapter: SiderAdapter
    var timer = Timer()
    var clickbtn=true

    private lateinit var binding: ActivityShowBinding
    private lateinit var adddata: GallaryModel

    companion object {
        var positionS by Delegates.notNull<Int>()
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show)
        binding.imagebutton.setOnClickListener {

            if (clickbtn){
                clickbtn=false
                binding.imagebutton.text="Stop"
                Toast.makeText(this, "++++++", Toast.LENGTH_SHORT).show()
                val timerTask: TimerTask = object : TimerTask() {
                    override fun run() { binding.idViewPager.post { binding.idViewPager.currentItem = (binding.idViewPager.currentItem + 1) % Const.imageListfull.size } }
                }
                timer = Timer()
                timer.schedule(timerTask, 1000, 1000)

            }else {
                Toast.makeText(this, "------", Toast.LENGTH_SHORT).show()
                clickbtn=true
                timer.cancel()
                binding.imagebutton.text="Start"

            }
        }


        if (intent != null) {
            //     adddata= intent.extras?.getSerializable("MyData") as GallaryModel
            positionS = intent.extras?.getSerializable("MyData") as Int
            Log.d(TAG, "for postion check:$positionS ")


            viewPagerAdapter = SiderAdapter(this@ShowActivity, Const.imageListfull)
            binding.idViewPager.adapter = viewPagerAdapter
            binding.idViewPager.currentItem = positionS

            /*  Glide.with(this)
                  .load(adddata.displayphoto)
                  .placeholder(R.drawable.ic_launcher_background).into(binding.imageviewforshow)*/


        }

    }
}