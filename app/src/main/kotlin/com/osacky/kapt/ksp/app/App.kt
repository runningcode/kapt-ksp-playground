package com.osacky.kapt.ksp.app

import com.osacky.kapt.ksp.utilities.StringUtils

fun main() {
    val tokens = StringUtils.split(MessageUtils.getMessage())
    val result = StringUtils.join(tokens)
    println(result.capitalize())
}
