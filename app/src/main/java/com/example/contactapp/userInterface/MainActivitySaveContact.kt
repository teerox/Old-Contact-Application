package com.example.contactapp.userInterface

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.contactapp.R
import com.example.contactapp.model.Contact
import com.example.contactapp.model.ContactDatabase
import com.example.contactapp.model.NetworkChangeReceiver
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_addcontact.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivitySaveContact : AppCompatActivity(){

    private lateinit var database: ContactDatabase
   lateinit var reciever:NetworkChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reciever = NetworkChangeReceiver()
        setContentView(R.layout.fragment_addcontact)
       // setSupportActionBar(toolbar_contact)
        database = ContactDatabase.getInstance(applicationContext)!!
//        toolbar_contact.setNavigationOnClickListener {
//            finish()
//        }

    }

    override fun onStart() {
        super.onStart()
        val intent = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(reciever, intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(reciever)
    }

    //METHOD FOR SAVE BUTTON
    fun saveContact(v:View){
        val nameValue = name.text.toString()
        val phoneValue = phone_number.text.toString()
        val emailValue = email.text.toString()
        val addressValue = address.text.toString()

        //VALIDATION
        if(nameValue.isEmpty() || phoneValue.isEmpty()){
            Toast.makeText(this, "Name or phone number cannot be empty", Toast.LENGTH_SHORT).show()
        }
        else if(phoneValue.length < 11 || phoneValue[0] != '0'){
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show()
        }
        else if (!emailValue.contains('@') || !emailValue.contains('.') ){
            Toast.makeText(this, "Email isn't valid", Toast.LENGTH_SHORT).show()
        }
        else{
            //SAVING CONTACT
            val dialogBuilder = AlertDialog.Builder(this,
                R.style.AlertDialogCustom
            )

            dialogBuilder.setTitle("Save this Contact")
            dialogBuilder.setMessage("Do you want to save this contact?")
            dialogBuilder.setPositiveButton("Yes") {
                    _, _ ->

                val connectivityManager =
                    baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val activeNetwork = connectivityManager.activeNetworkInfo
                if (activeNetwork != null && activeNetwork.isConnected) {
                    val singleContact = Contact(nameValue, "", emailValue, phoneValue, addressValue)
                    val fireStoreDB = FirebaseFirestore.getInstance()
                    fireStoreDB.collection(TABLE)
                        .add(singleContact)
                        .addOnSuccessListener {
                            GlobalScope.launch(Dispatchers.Main) {
                                saveContact()
                            }
                           Toast.makeText(this, "Contact Saved", Toast.LENGTH_LONG).show()
                            finish()
                        }.addOnFailureListener {
                            Log.e("Failure", it.toString())
                        }
                }else{
                    Toast.makeText(this,"No Network",Toast.LENGTH_LONG).show()
                }

           }

            dialogBuilder.setNegativeButton("No"){
                    _,_ -> Toast.makeText(this,"Contact not Saved", Toast.LENGTH_LONG).show()
            }

            //show the dialogue
            val alert = dialogBuilder.create()
            alert.setCanceledOnTouchOutside(false)
            alert.show()
        }

    }

    //METHOD FOR CANCEL BUTTON
    fun cancel(v:View){
        finish()
    }

    suspend fun saveContact():Contact{
        return GlobalScope.async(Dispatchers.IO) {
            val singleContact = Contact(name.text.toString(), "", email.text.toString(), phone_number.text.toString(), address.text.toString())
            database.contactDao().insertAll(singleContact)
            singleContact
        }.await()
    }


}
