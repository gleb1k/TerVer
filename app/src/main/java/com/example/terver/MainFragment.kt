package com.example.terver

import android.annotation.SuppressLint
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

    @SuppressLint("SetTextI18n")
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

                if (checkN() && checkM()) {
                    n = etN.text.toString().toInt()
                    if (position != 1) {
                        if (etM.visibility != View.GONE) {
                            m = etM.text.toString().toInt()
                        }
                    } else
                        readArray()

                    when (position) {
                        0 -> {
                            tvResult.text = "${spinner.selectedItem} : ${
                                Combinatorics.permutationsWORepetitions(n)
                            }"
                        }
                        1 -> {
                            if (list.isNullOrEmpty())
                                tvResult.text = "Что-то введено не так"
                            else {
                                val result = Combinatorics.permutationsWRepetitions(n, list)
                                if (result == (-1).toDouble())
                                    tvResult.text = "Некорректные значения"
                                else
                                    tvResult.text = "${spinner.selectedItem} : $result"
                            }
                        }
                        2 -> {
                            val result = Combinatorics.placementsWORepetitions(
                                n,
                                m
                            )
                            if (result == (-1).toDouble())
                                tvResult.text = "Некорректные значения"
                            else
                                tvResult.text = "${spinner.selectedItem} : $result"
                        }
                        3 -> {
                            tvResult.text = "${spinner.selectedItem} : ${
                                Combinatorics.placementsWRepetitions(
                                    n,
                                    m
                                )
                            }"
                        }
                        4 -> {
                            val result = Combinatorics.combinationsWORepetitions(
                                n,
                                m
                            )
                            if (result == (-1).toDouble())
                                tvResult.text = "Некорректные значения"
                            else
                                tvResult.text = "${spinner.selectedItem} : $result"
                        }
                        5 -> {
                            tvResult.text = "${spinner.selectedItem} : ${
                                Combinatorics.combinationsWRepetitions(
                                    n,
                                    m
                                )
                            }"
                        }

                    }
                }
            }

            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    tvResult.text = "Выберите перестановку"
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            etM.isEnabled = false
                            etM.visibility = View.GONE
                            etM.text.clear()
                        }
                        1 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите n1 n2 ... nk (через пробел)"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                            etM.text.clear()
                        }
                        2 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите m"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_CLASS_NUMBER
                            etM.text.clear()
                        }
                        3 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите m"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_CLASS_NUMBER
                            etM.text.clear()
                        }
                        4 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите m"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_CLASS_NUMBER
                            etM.text.clear()
                        }
                        5 -> {
                            etM.isEnabled = true
                            etM.hint = "Введите m"
                            etM.visibility = View.VISIBLE
                            etM.inputType = InputType.TYPE_CLASS_NUMBER
                            etM.text.clear()
                        }
                    }
                }
            }


        }
    }

    private fun checkN(): Boolean {
        if (binding!!.etN.text.isNullOrEmpty()) {
            binding!!.tvResult.text = "Введите значение"
            return false
        }
        return true

    }

    private fun checkM(): Boolean {
        if (binding!!.etM.text.isNullOrEmpty())
            if (binding!!.etM.visibility != View.GONE) {
                binding!!.tvResult.text = "Введите значение"
                return false
            }
        return true
    }


    private fun readArray(): MutableList<Int> {
        binding?.run {
            list.clear()
            val arrStr = etM.text.split(" ")
            val lenghtArr = arrStr.size
            for (index in 0 until lenghtArr) {
                try {
                    val a = arrStr[index].toInt()
                        list.add(a)
                } catch (e: NumberFormatException) {
                    tvResult.text = "Это не число!"
                    list.clear()
                }
            }
        }
        return list
    }

    companion object {

        const val MAIN_FRAGMENT_TAG = "Main_FRAGMENT_TAG"
    }
}