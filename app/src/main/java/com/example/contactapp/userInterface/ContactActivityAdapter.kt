package com.example.contactapp.userInterface

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactapp.R
import com.example.contactapp.databinding.LayoutListitemBinding
import com.example.contactapp.model.Contact

class ContactActivityAdapter(var context: Context, private var arrayLists:ArrayList<Contact>, private val clickListener: (Contact) -> Unit ):RecyclerView.Adapter<ContactActivityAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = LayoutListitemBinding.inflate(view)
        return ViewHolder(binding)
          }

    override fun getItemCount(): Int {
        return arrayLists.size
         }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(arrayLists[position],clickListener)
    class ViewHolder(private val binding: LayoutListitemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Contact,clickListener: (Contact) -> Unit){
            binding.root.setOnClickListener {
                clickListener(item)
            }
            binding.contacts = item
            Glide.with(binding.root.context).asBitmap().error(R.drawable.image_round).load(item.image).into(binding.smallimage)
        }

    }

//    @BindingAdapter("imageUrl")
//    fun setImageUrl(imageView: CircleImageView, url: Uri?) {
//        Glide.with(imageView.context).asBitmap().error(R.drawable.image_round).load(url).into(imageView)
//        imageView.setImageURI(url)
//    }



}