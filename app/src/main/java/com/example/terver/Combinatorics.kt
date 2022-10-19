package com.example.terver

import kotlin.math.pow

public class Combinatorics {
    //Перестановки
    companion object {
        fun permutations(n: Int): Double {
            return factorial(n)
        }

        private fun factorial(n: Int): Double {
            var result = 1.0
            for (i in 1..n) {
                result *= i
            }
            return result
        }

        //Сочетания без повторений
        fun combinationsWORepetitions(n: Int, m: Int): Double {
            return factorial(n) / (factorial(n - m) * factorial(m))
        }

        //Сочетания с повтороениями
        fun combinationsWRepetitions(n: Int, m: Int): Double {
            return combinationsWORepetitions(n + m - 1, m)
        }

        //Размещения без повторений
        fun placementsWORepetitions(n: Int, m: Int): Double {
            return factorial(n) / factorial(n - m)
        }

        //Размещения с повторениями
        fun placementsWRepetitions(n: Int, m: Int): Double {
            return n.toDouble().pow(m)
        }

    }
}