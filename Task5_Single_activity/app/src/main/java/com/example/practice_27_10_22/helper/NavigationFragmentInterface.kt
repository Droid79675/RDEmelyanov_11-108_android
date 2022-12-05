package com.example.practice_27_10_22.helper

import android.os.Bundle

interface NavigationFragmentInterface {
    val ARG: String
    fun createBundle(name: String) : Bundle
}