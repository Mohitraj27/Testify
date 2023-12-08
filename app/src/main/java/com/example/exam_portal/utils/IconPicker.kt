package com.example.exam_portal.utils

import com.example.exam_portal.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.d1,
        R.drawable.d2,
        R.drawable.d3,
        R.drawable.d5,
        R.drawable.d7

    )


    var currentIcon=0

    fun getIcon():Int{
        currentIcon= (currentIcon+1)% icons.size
        return icons[currentIcon]
    }
}