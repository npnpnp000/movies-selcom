package com.movies_selcom.view.fragment.dashboard.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.movies_selcom.databinding.CardMovieBinding
import com.movies_selcom.model.entities.MovieEntity
import com.movies_selcom.utils.extensions.imageFromGlide


class UltraPagerAdapter(private val context: Context, private val list: ArrayList<MovieEntity>, val onMovieListener: OnMovieListener) : PagerAdapter() , View.OnClickListener{

    override fun getCount(): Int {
       return list.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = CardMovieBinding.inflate(LayoutInflater.from(container.context), container, false)
        val root = binding.root

        binding.titleTxt.text = list[position].name

        imageFromGlide(context, "https://image.tmdb.org/t/p/w500"+list[position].image_path, binding.imageView)
        root.setOnClickListener(this)

        container.addView(root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as ConstraintLayout
        container.removeView(view)
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
      return view == `object`
    }

    override fun onClick(v: View?) {
        v?.let {view ->
            onMovieListener.onMovieClick(view)
        }
    }


}
