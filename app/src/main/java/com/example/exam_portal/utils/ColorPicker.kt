package com.example.exam_portal.utils

object  ColorPicker {
    val colors = arrayOf(
        "#40E0D0",
    "#FF7F50",
     "#E6E6FA",
     "#708090",
     "#DAA520",
     "#87CEEB",
    "#FF00FF",
    "#DC143C",
        "#808000",
     "#CCCCFF"
    )

    var currentColorIndex=0

    fun getColor():String{
        currentColorIndex= (currentColorIndex+1)% colors.size
        return colors[currentColorIndex]
    }
}