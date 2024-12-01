package com.movies_selcom.view.fragment.dashboard.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.movies_selcom.R
import com.movies_selcom.databinding.FragmentMainBinding
import com.movies_selcom.model.entities.MovieEntity
import com.movies_selcom.utils.extensions.MoviesType
import com.movies_selcom.utils.extensions.provideViewModel
import com.movies_selcom.view.fragment.dashboard.adapter.OnMovieListener
import com.movies_selcom.view.fragment.dashboard.adapter.UltraPagerAdapter
import com.movies_selcom.view.fragment.dashboard.viewmodel.MainViewModel
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.OnMenuItemClickListener
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import com.tmall.ultraviewpager.UltraViewPager
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer


class DashboardFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val mainViewModel: MainViewModel by provideViewModel()
    private lateinit var ultraViewPager: UltraViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUltraPager()
        initializePowerMenu()
        setMoviesList()
    }

    @SuppressLint("ClickableViewAccessibility", "ResourceType")
    private fun initializeUltraPager() {
        ultraViewPager = binding?.ultraViewpager as UltraViewPager
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)

        //initialize UltraPagerAdapter，and add child view to UltraViewPager

        ultraViewPager.adapter = setAdapter(mainViewModel.moviesLiveData.value?: arrayListOf())

        ultraViewPager.setPageTransformer(false, UltraDepthScaleTransformer())

    }

    private fun initializePowerMenu() {
        val powerMenu = PowerMenu.Builder(requireContext())
            .addItem(PowerMenuItem(MoviesType.popular.name, true)) // add an item.
            .addItem(PowerMenuItem(MoviesType.broadcast.name, false)) // aad an item list.
            .addItem(PowerMenuItem(MoviesType.favorites.name, false))
            .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT) // Animation start point (TOP | LEFT).
            .setMenuRadius(10f) // sets the corner radius.
            .setMenuShadow(10f) // sets the shadow.
            .setTextColor(/*ContextCompat.getColor(requireContext(),  R.color.black)*/Color.GRAY)
            .setTextGravity(Gravity.CENTER)
            .setTextTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD))
            .setSelectedTextColor(Color.WHITE)
            .setMenuColor(Color.WHITE)
            .setSelectedMenuColor(/*ContextCompat.getColor(requireContext(), ContextCompat.getColor(requireContext(), R.color.purple_700))*/Color.GREEN)
            .build()

        powerMenu.onMenuItemClickListener = onMenuItemClickListener(powerMenu)

        binding?.menuFcb?.setOnClickListener {
            powerMenu.showAsAnchorLeftTop(binding?.menuFcb,0,-370)
        }

    }

    private fun onMenuItemClickListener(powerMenu: PowerMenu): OnMenuItemClickListener<PowerMenuItem> =
        object : OnMenuItemClickListener<PowerMenuItem> {
            override fun onItemClick(position: Int, item: PowerMenuItem) {
                Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()

                mainViewModel.requestMovies(position)

                powerMenu.selectedPosition = position // change selected item
                powerMenu.dismiss()
            }
        }
    private fun setAdapter(list: ArrayList<MovieEntity>): PagerAdapter {
        val adapter: PagerAdapter =
            UltraPagerAdapter(requireContext(), list,object :
                OnMovieListener {
                override fun onMovieClick(view : View) {
                    navigateToDetails(list[ultraViewPager.currentItem])
                }
            })
        return adapter
    }

    private fun setMoviesList() {
        mainViewModel.moviesLiveData.observe(viewLifecycleOwner) { list ->

            ultraViewPager.adapter = setAdapter(list?: arrayListOf())
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun navigateToDetails(movieEntity: MovieEntity) {

        val bundle = setBundle(movieEntity)

        findNavController().navigate(R.id.action_dashboardFragment_to_detailsFragment,bundle)
    }

    private fun setBundle(movieEntity: MovieEntity): Bundle {
        return bundleOf(
            "name" to movieEntity.name,
            "id" to movieEntity.id,
            "image_path" to movieEntity.image_path,
            "overview" to movieEntity.overview)
    }
    
}