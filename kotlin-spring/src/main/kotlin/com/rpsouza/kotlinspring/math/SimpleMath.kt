package com.rpsouza.kotlinspring.math

import org.springframework.web.bind.annotation.PathVariable

class SimpleMath {

  fun sum(numberOne: Float, numberTwo: Float) = numberOne + numberTwo

  fun subtraction(numberOne: Float, numberTwo: Float) = numberOne - numberTwo

  fun division(numberOne: Float, numberTwo: Float) = numberOne / numberTwo

  fun multiplication(numberOne: Float, numberTwo: Float) = numberOne * numberTwo
}