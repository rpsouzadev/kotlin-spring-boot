package com.rpsouza.kotlinspring.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper
import java.util.ArrayList

object DozerMapper {

  private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()

  fun <O, D> parseObject(origin: O, destination: Class<D>?): D {
    return mapper.map(origin, destination)
  }

  fun <O, D> parseListObjects(origin: List<O>, destination: Class<D>?): List<D>{
    val destinationObjects: ArrayList<D> = ArrayList()

    for (o in origin) {
      destinationObjects.add(mapper.map(o, destination))
    }

    return destinationObjects
  }
}