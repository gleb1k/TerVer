package com.example.terver

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.terver.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {

    private var n: Int = 0
    private var m: Int = 0

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            Constants.permuts
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding?.run {



            btnCalc.setOnClickListener {
                val position = spinner.selectedItemPosition.toString().toInt()

                if (etN.text.isNullOrEmpty() || etM.text.isNullOrBlank() ) {
                    tvResult.text = "Введите значение"
                } else {
                    n = etN.text.toString().toInt()
                    m = etM.text.toString().toInt()
                }

                when(position){
                    0 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.permutations(n)}"
                        etM.isEnabled = false
                    }
                    1 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.placementsWORepetitions(n,m)}"
                    }
                    2 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.placementsWRepetitions(n,m)}"
                    }
                    3 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.combinationsWRepetitions(n,m)}"
                    }
                    4 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.combinationsWORepetitions(n,m)}"
                    }
                }
            }

            spinner.adapter = adapter

//            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    tvResult.text = "Выберите перестановку"
//                }
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    when(position){
//                        0 -> {
//                            tvResult.text = "${spinner.selectedItem} : ${Combinatorics.permutations(n)}"
//                        }
//                        1 -> {
//                            tvResult.text = "${spinner.selectedItem} : ${Combinatorics.placementsWORepetitions(n,m)}"
//                        }
//                        2 -> {
//                            tvResult.text = "${spinner.selectedItem} : ${Combinatorics.placementsWRepetitions(n,m)}"
//                        }
//                        3 -> {
//                            tvResult.text = "${spinner.selectedItem} : ${Combinatorics.combinationsWRepetitions(n,m)}"
//                        }
//                        4 -> {
//                            tvResult.text = "${spinner.selectedItem} : ${Combinatorics.combinationsWORepetitions(n,m)}"
//                        }
//                    }
//                }
//            }

//            tvResult.text = spinner.selectedItemPosition.toString()

//
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