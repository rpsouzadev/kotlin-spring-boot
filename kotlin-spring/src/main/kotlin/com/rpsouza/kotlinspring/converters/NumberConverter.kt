package com.rpsouza.kotlinspring.converters

object NumberConverter {

  fun convertToFloat(strNumber: String?): Float {
    if (strNumber.isNullOrBlank()) return 0.0f

    val number = strNumber.replace(",".toRegex(), ".")
    return if (isNumeric(number)) number.toFloat() else 0.0f
  }

  fun isNumeric(strNumber: String?): Boolean {
    if (strNumber.isNullOrBlank()) return false
    val number = strNumber.replace(",".toRegex(), ".")
    return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
  }
}