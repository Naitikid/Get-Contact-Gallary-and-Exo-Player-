package com.example.contactnumberandgallaryviewer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.contactnumberandgallaryviewer.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {



    private lateinit var binding:ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_test)

    }
}