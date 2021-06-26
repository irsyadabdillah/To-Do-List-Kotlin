package com.irzstudio.todolist

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestLogic {
    @Test
    fun palindrome() {
        println("Radar palindrome = ${isPalindrome("radar")}")
        println("Malam palindrome = ${isPalindrome("malam")}")
        println("Kasur ini rusak palindrome = ${isPalindrome("kasur ini rusak")}")
        println("Ibu Ratna antar ubi palindrome = ${isPalindrome("ibu ratna antar ubi")}")
        println("Malas palindrome = ${isPalindrome("malas")}")
        println("Makan nasi goreng palindrome = ${isPalindrome("makan nasi goreng")}")
        println("Balonku ada lima palindrome = ${isPalindrome("balonku ada lima")}")
    }

    private fun isPalindrome(value: String): Boolean {
        for (i in 0..value.length - 1) {
            val hurufDepan = value[i]
            val hurufBelakang = value[value.length - 1 - i]
            if (hurufDepan != hurufBelakang) {
                return false
                break
            }
        }
        return true
    }

    @Test
    fun leabYear() {
        val tahun1 = 1900
        val tahun2 = 2020
        var result = 0
        for (i in tahun1..tahun2 step 4) {
            result = i
            println(result)
        }
    }


    @Test
    fun reverseWord() {
        val sentence = "I am Great human"
        println(specialReverse(sentence))
    }

    private fun specialReverse(sentence: String): String {
        var result = ""
        val words = sentence.split(" ".toRegex()).toTypedArray()
        for (i in words.indices) {
            result += if (i % 2 == 0) {
                " " + reverse(words[i])
            } else {
                " " + words[i]
            }
        }
        return result
    }

    private fun reverse(word: String): String {
        return StringBuilder(word).reverse().toString()
    }

    @Test
    fun nearestFibonacci() {
        print(searchNearestFibonacci(listOf(15, 1, 3)))
    }

    fun searchNearestFibonacci(arr: List<Int>): Int {
        var result = 0
        val arrSum = arr.sumBy { it }
        var prevFib = 0
        var nextFib = 1

        val counter = 0
        var breaker = 1

        while (counter < breaker) {
            val fib = prevFib + nextFib
            prevFib = nextFib
            nextFib = fib
            if (arrSum in prevFib..nextFib) {
                result = if (arrSum - prevFib > nextFib - arrSum) {
                    nextFib - arrSum
                } else {
                    prevFib - arrSum
                }
                breaker = 0
            }
        }

        return result
    }

    @Test
    fun fizzBuzz1() {
        for (i in 1..15) {
            val result: String =
                when {
                    i % 15 == 0 -> "FizzBuzz"
                    i % 3 == 0 -> "Fizz"
                    i % 5 == 0 -> "Buzz"
                    else -> "$i"
                }
            println(result)
        }
    }
}