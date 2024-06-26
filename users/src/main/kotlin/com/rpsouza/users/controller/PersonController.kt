package com.rpsouza.kotlinspring.controller

import com.rpsouza.kotlinspring.data.vo.v1.PersonVO
import com.rpsouza.kotlinspring.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController {
  @Autowired
  private lateinit var service: PersonService

  @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
  fun personFindAll(): List<PersonVO> {
    return service.findAll()
  }

  @GetMapping(
    value = ["/{id}"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
  )
  fun personById(@PathVariable(value = "id") id: Long): PersonVO {
    return service.findById(id)
  }

  @PostMapping(
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE]
  )
  fun createPerson(@RequestBody person: PersonVO): PersonVO {
    return service.createPerson(person)
  }


  @PutMapping(
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE]
  )
  fun updatePerson(@RequestBody person: PersonVO): PersonVO {
    return service.updatePerson(person)
  }

  @DeleteMapping(
    value = ["/{id}"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
  )
  fun deletePerson(@PathVariable(value = "id") id: Long) {
    return service.deletePerson(id)
  }
}