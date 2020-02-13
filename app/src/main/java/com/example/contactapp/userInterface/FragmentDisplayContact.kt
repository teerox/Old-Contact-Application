package com.example.contactapp.userInterface

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentDisplayContactBinding
import com.example.contactapp.model.Contact
import com.example.contactapp.model.ContactDatabase
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class FragmentDisplayContact : Fragment() {

    private lateinit var database: ContactDatabase
    private var parc: Int? = null
    private var REQUEST_CODE = 1
    lateinit var binding:FragmentDisplayContactBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_display_contact,container,false)
        database = ContactDatabase.getInstance(this.context!!)!!
        val args = FragmentDisplayContactArgs.fromBundle(arguments!!)
        parc = args.contact.uid
        GlobalScope.launch(Dispatchers.Main){
            val singleContact = getSingleContact()
            binding.contact = singleContact[0]
            val imageUrl = singleContact[0].image
            Glide.with(this@FragmentDisplayContact).asBitmap().error(R.drawable.image_round).load(imageUrl).into(binding.profileImage)
        }

        return binding.root
    }

    suspend fun getSingleContact():ArrayList<Contact>{
        return GlobalScope.async(Dispatchers.IO) {
           val listContact =  database.contactDao().loadAllByIds(parc!!)
            listContact  as ArrayList<Contact>
        }.await()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val selectedImg = data?.data
                GlobalScope.async(Dispatchers.IO){
                    if (parc != null) {
                         database.contactDao().updateContact(parc!!, selectedImg.toString())
                        }
                    }
                    Glide.with(this).asBitmap().placeholder(R.drawable.image_round).load(selectedImg).into(binding.profileImage)
            }
        }

        }

    fun sendemail(v: View){
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", binding.emailContact.text.toString(), null))
        startActivity(emailIntent)
    }

    fun addressLocation(v:View){
        val addressIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=${binding.addressContact.text}"))
        startActivity(addressIntent)
    }

    fun sendMessage(v:View){
        val messageIntent = Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", binding.phoneNumberContact.text.toString(), null))
        startActivity(messageIntent)
    }

    fun call(v:View){
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:" + binding.phoneNumberContact.text.toString())
        startActivity(callIntent)
    }

    fun changeProfilePicture(V:View){
        var img = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (ContextCompat.checkSelfPermission(this.context!!, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //If no permission, request permission
            ActivityCompat.requestPermissions(activity!!.parent,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
        } else {
            img.type = "image/*"
            img.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(img,"select a picture"),REQUEST_CODE)
        }
    }


}
