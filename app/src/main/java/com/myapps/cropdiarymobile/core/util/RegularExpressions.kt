package com.myapps.cropdiarymobile.core.util

object RegularExpressions {
    val passwordRegex = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d@!*._]*$""".toRegex()
}