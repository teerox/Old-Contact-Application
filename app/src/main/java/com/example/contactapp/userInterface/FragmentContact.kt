package com.example.contactapp.userInterface

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.navigation.Navigation.findNavController
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.amitshekhar.DebugDB
import com.example.contactapp.R
import com.example.contactapp.databinding.RecylerFragmentBinding
import com.example.contactapp.model.Contact
import com.example.contactapp.model.ContactDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.ArrayList

class FragmentContact : Fragment() {
   private var contacts = ArrayList<Contact>()
    lateinit var binding:RecylerFragmentBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.recyler_fragment,container,false)
        val recyclerView = binding.recyView
        val adapter = ContactActivityAdapter(activity!!.applicationContext,contacts) {
            val action = FragmentContactDirections.actionActivityRecyToFragmentDisplayContact(it)
            binding.root.findNavController().navigate(action)
        }
        recyclerView.adapter =  adapter
        // DebugDB.getAddressLog()
        val db = ContactDatabase.getInstance(activity!!.applicationContext)!!
        db.contactDao().getAll().observe(viewLifecycleOwner, Observer{it ->
           contacts.clear()
            contacts.addAll(it)
            adapter.notifyDataSetChanged()
        })
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        GlobalScope.launch(Dispatchers.Main) {

          }
    }

//   private suspend fun getContacts():ArrayList<Contact>{
//       return GlobalScope.async(Dispatchers.IO) {
//           val db = ContactDatabase.getInstance(activity!!.applicationContext)!!
//           val dbContacts = db.contactDao().getAll() as ArrayList<Contact>
//            dbContacts
//       }.await()
//    }

}

