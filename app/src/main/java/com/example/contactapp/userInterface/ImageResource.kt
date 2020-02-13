package com.example.contactapp.userInterface

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.contactapp.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_listitem.view.*


class DataBindingAdaptars {
    @BindingAdapter("imageResource")
    fun setImageUri(view: CircleImageView, imageUri: String?) {
        Glide.with(view.context).asBitmap().error(R.drawable.image_round).load(imageUri).into(view)
//        if (imageUri == null) {
//            view.setImageURI(null)
//        } else {
//            view.setImageURI(Uri.parse(imageUri))
//        }
    }

    @BindingAdapter("android:src")
    fun setImageUri(view: CircleImageView, imageUri: Uri?) {
        view.setImageURI(imageUri)
    }

    @BindingAdapter("android:src")
    fun setImageDrawable(view: CircleImageView, drawable: Drawable?) {
        view.setImageDrawable(drawable)
    }

    @BindingAdapter("android:src")
    fun setImageResource(imageView: CircleImageView, resource: Int) {
        imageView.setImageResource(resource)
    }


}