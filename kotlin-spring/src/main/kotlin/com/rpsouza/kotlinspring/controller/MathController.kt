package com.rpsouza.kotlinspring.controller

import com.rpsouza.kotlinspring.converters.NumberConverter
import com.rpsouza.kotlinspring.exceptions.ResourceNotFoundException
import com.rpsouza.kotlinspring.math.SimpleMath
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {
  val counter: AtomicLong = AtomicLong()

  private val math = SimpleMath()

  @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
  fun sum(
    @PathVariable(value = "numberOne") numberOne: String?,
    @PathVariable(value = "numberTwo") numberTwo: String?
  ): Float {
    if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
      throw ResourceNotFoundException("Please set a numeric value!")

    return math.sum(
      NumberConverter.convertToFloat(numberOne),
      NumberConverter.convertToFloat(numberTwo)
    )
  }

  @RequestMapping(value = ["/sub/{numberOne}/{numberTwo}"])
  fun subtraction(
    @PathVariable(value = "numberOne") numberOne: String?,
    @PathVariable(value = "numberTwo") numberTwo: String?
  ): Float {
    if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
      throw ResourceNotFoundException("Please set a numeric value!")

    return math.subtraction(
      NumberConverter.convertToFloat(numberOne),
      NumberConverter.convertToFloat(numberTwo)
    )
  }

  @RequestMapping(value = ["/division/{numberOne}/{numberTwo}"])
  fun division(
    @PathVariable(value = "numberOne") numberOne: String?,
    @PathVariable(value = "numberTwo") numberTwo: String?
  ): Float {
    if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
      throw ResourceNotFoundException("Please set a numeric value!")

    return math.division(
      NumberConverter.convertToFloat(numberOne),
      NumberConverter.convertToFloat(numberTwo)
    )
  }

  @RequestMapping(value = ["/multiplication/{numberOne}/{numberTwo}"])
  fun multiplication(
    @PathVariable(value = "numberOne") numberOne: String?,
    @PathVariable(value = "numberTwo") numberTwo: String?
  ): Float {
    if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
      throw ResourceNotFoundException("Please set a numeric value!")

    return math.multiplication(
      NumberConverter.convertToFloat(numberOne),
      NumberConverter.convertToFloat(numberTwo)
    )
  }
}