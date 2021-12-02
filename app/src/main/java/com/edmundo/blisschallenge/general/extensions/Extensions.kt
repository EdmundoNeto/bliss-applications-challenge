package com.edmundo.blisschallenge.general.extensions

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, expression: (T?) -> Unit) {
    liveData.observe(this, Observer(expression))
}
fun AppCompatActivity.bindingContentView(layout: Int): ViewDataBinding =
    DataBindingUtil.setContentView(this, layout)


const val thumbnailMultiplier = 0.1f

@BindingAdapter("imageUrl")
fun ImageView.setImage(url: String?) =
    if (!url.isNullOrEmpty())
        Glide.with(this)
            .load(url)
            .fitCenter()
            .into(this)
            .let { Unit }
    else
        Unit

@BindingAdapter("thumbnailUrl")
fun ImageView.setThumbnail(url: String?) =
    if (!url.isNullOrEmpty())
        Glide.with(this)
            .load(url)
            .centerCrop()
            .thumbnail(thumbnailMultiplier)
            .into(this)
            .let { Unit }
    else Unit