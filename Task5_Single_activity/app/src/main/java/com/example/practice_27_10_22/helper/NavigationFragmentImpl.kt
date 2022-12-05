package com.example.practice_27_10_22.helper

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController


fun NavigationFragmentInterface.getClassName(): String = this.javaClass.toString()
    .substring(
        startIndex = this.javaClass.toString().lastIndexOf(".") + 1,
        endIndex = this.javaClass.toString().lastIndexOf("$")
    )

fun Fragment.navigate(id: Int, classCalled: NavigationFragmentInterface) =
    findNavController().navigate(
        id,
        classCalled.createBundle(
            classCalled.getClassName()
        )
    )
