package com.example.terver

class Combinatorics{
    fun countPermutation(n: Int) : Double{
        return  factorial(n)
    }
    private fun factorial(n: Int): Double {
        var result = 1.0
        for (i in 1..n) {
            result *= i
        }
        return result
    }

}