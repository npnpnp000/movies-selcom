package com.movies_selcom.view.fragment.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.movies_selcom.R
import com.movies_selcom.databinding.FragmentDetailsBinding
import com.movies_selcom.model.entities.MovieEntity
import com.movies_selcom.utils.extensions.imageFromGlide
import com.movies_selcom.utils.extensions.provideViewModel
import com.movies_selcom.view.fragment.details.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    var binding: FragmentDetailsBinding? = null
    private val detailsViewModel: DetailsViewModel by provideViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkFavorite(arguments?.getInt("id") ?: -1)

        binding?.titleTxt?.text = arguments?.getString("name")
        binding?.overviewTxt?.text = arguments?.getString("overview")
        binding?.imageView?.let {
            imageFromGlide(requireContext(), arguments?.getString("image_path") ?: "", it)
        }

        binding?.starImv?.setOnClickListener {
            if (detailsViewModel.movieInFavoriteLiveData.value == null) {
                detailsViewModel.saveMovieToFavorite(setMovieToSave(arguments))
            } else {
                detailsViewModel.removeMovieFromFavorite()
            }
        }
    }

    private fun setMovieToSave(arguments: Bundle?): MovieEntity? {
        arguments?.let { arguments ->
            return MovieEntity(
                arguments.getString("name") ?: "",
                arguments.getInt("id"),
                arguments.getString("image_path") ?: "",
                arguments.getString("overview") ?: ""
            )
        }
        return null
    }

    private fun checkFavorite(id: Int) {
        setMovieFavoriteIndicator()

        detailsViewModel.getMovie(arguments?.getInt("id") ?: -1)
    }

    private fun setMovieFavoriteIndicator() {
        detailsViewModel.movieInFavoriteLiveData.observe(viewLifecycleOwner) { movie ->
            setStarIndicator(movie)
        }
    }

    private fun setStarIndicator(movie: MovieEntity?) {
        if (movie != null) {
            binding?.starImv?.setImageResource(R.drawable.ic_baseline_star_50)
        } else {
            binding?.starImv?.setImageResource(R.drawable.ic_baseline_star_border_50)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}