package com.example.contactnumberandgallaryviewer.forcontacts

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactnumberandgallaryviewer.R
import com.example.contactnumberandgallaryviewer.adapter.RCVAdapter
import com.example.contactnumberandgallaryviewer.databinding.ActivityContactsBinding
import com.example.contactnumberandgallaryviewer.model.ContactModel

class ContactsActivity : AppCompatActivity() {
    var arrayList: ArrayList<ContactModel> = arrayListOf()
    var rcvAdapter: RCVAdapter = RCVAdapter(arrayList)

    private lateinit var binding: ActivityContactsBinding
    @SuppressLint("Range", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts)
        binding.apply {
            rcvContact.layoutManager = LinearLayoutManager(this@ContactsActivity)
            rcvContact.adapter = RCVAdapter(arrayList)
        }
        arrayList.clear()
        val cursor = this.contentResolver
            .query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ), null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
        while (cursor!!.moveToNext()) {
            val contactName =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val contactNumber =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val contactModel = ContactModel(contactName, contactNumber)
            arrayList.add(contactModel)
        }
        rcvAdapter.notifyDataSetChanged()
        cursor.close()
    }
}
