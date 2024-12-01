package com.movies_selcom.utils.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.movies_selcom.R
import com.movies_selcom.dependency_injection.ViewModelModule
import java.util.Calendar
import androidx.swiperefreshlayout.widget.CircularProgressDrawable


inline fun <reified VM : ViewModel> Fragment.provideViewModel() =
    ViewModelModule.provideViewModel<VM>(this)

fun imageFromGlide(context: Context, poster_path:String, imageView :ImageView){
    // Using Glide to ImageView
    val url = "https://image.tmdb.org/t/p/w500/${poster_path}" //basic path to get images + specific end path from the movie
    val calendar = Calendar.getInstance()
    val versionNumber =calendar.get(Calendar.DAY_OF_WEEK) + calendar.get(Calendar.WEEK_OF_YEAR) +
            calendar.get(Calendar.YEAR) *100
    Glide.with(context)
        .load(url)
        .signature(IntegerVersionSignature(versionNumber))
        .placeholder(circularProgressDrawable(context))
        .error(ContextCompat.getDrawable(context.applicationContext, R.drawable.ic_baseline_image_not_supported_40 ))
        .into(imageView)
}

private fun circularProgressDrawable(context: Context): Drawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable

}