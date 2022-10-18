package com.example.terver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.terver.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {



    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        binding?.run {

        }
    }

    companion object {

        const val MAIN_FRAGMENT_TAG = "Main_FRAGMENT_TAG"

        fun newInstance(message: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(MAIN_FRAGMENT_TAG, message)
                }
            }
    }
}