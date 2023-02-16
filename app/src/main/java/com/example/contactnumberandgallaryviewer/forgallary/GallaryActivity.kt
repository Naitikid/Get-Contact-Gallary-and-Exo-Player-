package com.example.contactnumberandgallaryviewer.forgallary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactnumberandgallaryviewer.utils.Const
import com.example.contactnumberandgallaryviewer.R
import com.example.contactnumberandgallaryviewer.adapter.GallaryAdapter
import com.example.contactnumberandgallaryviewer.databinding.ActivityGallaryBinding
import com.example.contactnumberandgallaryviewer.datashow.ShowActivity
import com.example.contactnumberandgallaryviewer.model.GallaryModel


class GallaryActivity : AppCompatActivity() {

   // var imageList: ArrayList<String> = ArrayList()
    var imageList: ArrayList<GallaryModel> = ArrayList()


  //  var arrayListG = ArrayList<GallaryModel>()

   // var rcvAdapterG: GallaryAdapter = GallaryAdapter(this, arrayListG)

    private lateinit var binding: ActivityGallaryBinding

    @SuppressLint("NotifyDataSetChanged", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallary)
    /*    arrayListG.add(GallaryModel("http://13.233.39.120/trainee/uploads/user.png"))
        arrayListG.add(GallaryModel("http://13.233.39.120/trainee/uploads/user.png"))
        arrayListG.add(GallaryModel("http://13.233.39.120/trainee/uploads/user.png"))
        arrayListG.add(GallaryModel("http://13.233.39.120/trainee/uploads/user.png"))
        arrayListG.add(GallaryModel("http://13.233.39.120/trainee/uploads/user.png"))
        arrayListG.add(GallaryModel("http://13.233.39.120/trainee/uploads/user.png"))*/

        binding.apply {
            rcvgallary.layoutManager = GridLayoutManager(this@GallaryActivity, 2)
            rcvgallary.adapter = GallaryAdapter(this@GallaryActivity, imageList){ Gallarydata , Postion, View ->
                Toast.makeText(this@GallaryActivity, "123456789555:+$Postion", Toast.LENGTH_SHORT).show()
                val i = Intent(this@GallaryActivity, ShowActivity::class.java)
              //  i.putExtra("MyData",Gallarydata)
                i.putExtra("MyData",Postion)
                startActivity(i)
            }
        }

        /* arrayListG.clear()*/
        val columns = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media._ID
        )
        val imagecursor = this.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, ""
        )
        while (imagecursor!!.moveToNext()) {
            val dataColumnIndex =
                imagecursor.getColumnIndex(MediaStore.Images.Media.DATA)
         //   imageList.add(imagecursor.getString(dataColumnIndex))
            imageList.add(GallaryModel(imagecursor.getString(dataColumnIndex)))

            Const.imageListfull.add(GallaryModel(imagecursor.getString(dataColumnIndex)))
        //    Log.d(TAG, "Star: $imageList")
        }
       imagecursor.close()
    }
}
