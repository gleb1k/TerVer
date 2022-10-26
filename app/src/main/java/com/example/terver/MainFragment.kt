package com.example.terver

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.terver.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {

    private var n: Int = 0
    private var m: Int = 0
    private var list: MutableList<Int> = mutableListOf(0)

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
                    if (position!=1)
                    m = etM.text.toString().toInt()
                    else{
                        m = 0
                        list.clear()
                        val arrStr = etM.text.split(" ")
                        val lenght = arrStr.size
                        for (index in 0 until lenght) {
                            val a = arrStr[index].toInt()
                            if (a<=0)
                            {
                                tvResult.text = "Некорректные значения"
                                list.clear()
                                break
                            }
                            else {
                                list.add(a)
                            }
                        }
                    }
                }

                when(position){
                    0 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.permutationsWORepetitions(n)}"
                    }
                    1 -> {
                        val result = Combinatorics.permutationsWRepetitions(n, list)
                        if (result == (-1).toDouble())
                            tvResult.text = "Некорректные значения"
                        else
                            tvResult.text = "${spinner.selectedItem} : ${result}"
                    }
                    2 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.placementsWORepetitions(n,m)}"
                    }
                    3 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.placementsWRepetitions(n,m)}"
                    }
                    4 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.combinationsWRepetitions(n,m)}"
                    }
                    5 -> {
                        tvResult.text = "${spinner.selectedItem} : ${Combinatorics.combinationsWORepetitions(n,m)}"
                    }
                }
            }

            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    tvResult.text = "Выберите перестановку"
                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when(position){
                        0 -> {
                            etM.isEnabled = false
                            etM.visibility = View.GONE
                        }
                        1 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите n1 n2 ... nk (через пробел)"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_CLASS_PHONE
                        }
                        2 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите m"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_CLASS_NUMBER
                        }
                        3 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите m"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_CLASS_NUMBER
                        }
                        4 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите m"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_CLASS_NUMBER
                        }
                        5 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите m"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_CLASS_NUMBER
                        }
                    }
                }
            }



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