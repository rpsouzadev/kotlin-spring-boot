package com.rpsouza.kotlinspring

import com.rpsouza.kotlinspring.exceptions.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {
  val counter: AtomicLong = AtomicLong()

  @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
  fun sum(
    @PathVariable(value = "numberOne") numberOne: String?,
    @PathVariable(value = "numberTwo") numberTwo: String?
  ): Float {
    if (!isNumeric(numberOne) || !isNumeric(numberTwo))
      throw UnsupportedMathOperationException("Please set a numeric value!")

    return convertToFloat(numberOne) + convertToFloat(numberTwo)
  }

  @RequestMapping(value = ["/sub/{numberOne}/{numberTwo}"])
  fun subtraction(
    @PathVariable(value = "numberOne") numberOne: String?,
    @PathVariable(value = "numberTwo") numberTwo: String?
  ): Float {
    if (!isNumeric(numberOne) || !isNumeric(numberTwo))
      throw UnsupportedMathOperationException("Please set a numeric value!")

    return convertToFloat(numberOne) - convertToFloat(numberTwo)
  }

  @RequestMapping(value = ["/division/{numberOne}/{numberTwo}"])
  fun division(
    @PathVariable(value = "numberOne") numberOne: String?,
    @PathVariable(value = "numberTwo") numberTwo: String?
  ): Float {
    if (!isNumeric(numberOne) || !isNumeric(numberTwo))
      throw UnsupportedMathOperationException("Please set a numeric value!")

    return convertToFloat(numberOne) / convertToFloat(numberTwo)
  }

  @RequestMapping(value = ["/multiplication/{numberOne}/{numberTwo}"])
  fun multiplication(
    @PathVariable(value = "numberOne") numberOne: String?,
    @PathVariable(value = "numberTwo") numberTwo: String?
  ): Float {
    if (!isNumeric(numberOne) || !isNumeric(numberTwo))
      throw UnsupportedMathOperationException("Please set a numeric value!")

    return convertToFloat(numberOne) * convertToFloat(numberTwo)
  }

  private fun convertToFloat(strNumber: String?): Float {
    if (strNumber.isNullOrBlank()) return 0.0f

    val number = strNumber.replace(",".toRegex(), ".")
    return if (isNumeric(number)) number.toFloat() else 0.0f
  }

  private fun isNumeric(strNumber: String?): Boolean {
    if (strNumber.isNullOrBlank()) return false
    val number = strNumber.replace(",".toRegex(), ".")
    return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
  }
}