package com.movies_selcom.view.fragment.splash.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.movies_selcom.R
import com.movies_selcom.databinding.FragmentSplashBinding
import com.movies_selcom.view.fragment.splash.viewmodel.SplashViewModel
import com.movies_selcom.utils.extensions.provideViewModel

class SplashFragment : Fragment() {
    var binding: FragmentSplashBinding? = null
    private val splashViewModel: SplashViewModel by provideViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerInternetConnection()
    }



    private fun navigateToDashboard() {
        findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)
    }

    private fun observerInternetConnection() {
        splashViewModel.internetConnectionAvailable.observe(viewLifecycleOwner) { isInternetConnectionAvailable ->
            if (isInternetConnectionAvailable) {
                navigateToDashboard()
                return@observe
            }
            startErrorDialog()
        }
    }

    private fun startErrorDialog() {
        val alertDialog: AlertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.apply {
            setTitle(getString(R.string.error))
            setMessage(requireContext().getString(R.string.internet_connection_error))
            setButton(
                AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok)
            ) { dialog, _ ->
                dialog.dismiss()
                activity?.finish()
            }
            show()
        }
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}