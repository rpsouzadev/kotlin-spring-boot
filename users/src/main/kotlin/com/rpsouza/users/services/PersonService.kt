package com.rpsouza.kotlinspring.services

import com.rpsouza.kotlinspring.data.vo.v1.PersonVO
import com.rpsouza.kotlinspring.exceptions.ResourceNotFoundException
import com.rpsouza.kotlinspring.model.Person
import com.rpsouza.kotlinspring.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {
  @Autowired
  private lateinit var repository: PersonRepository

  private val logger = Logger.getLogger(PersonService::class.java.name)

  fun findAll(): List<PersonVO> {
    logger.info("Finding all people!")
    return repository.findAll()
  }

  fun findById(id: Long): PersonVO {
    logger.info("Finding one person!")
    return repository.findById(id)
      .orElseThrow { ResourceNotFoundException("No records found for this ID") }
  }


  fun createPerson(person: PersonVO): PersonVO {
    logger.info("Creating one person with name ${person.firstName}!")
    return repository.save(person)
  }

  fun updatePerson(person: PersonVO): PersonVO {
    logger.info("Updating one person with id ${person.id}!")

    val entity = repository.findById(person.id)
      .orElseThrow { ResourceNotFoundException("No records found for this ID") }

      entity.firstName = person.firstName
      entity.lastName = person.lastName
      entity.address = person.address
      entity.gender = person.gender

    return repository.save(entity)
  }

  fun deletePerson(id: Long) {
    logger.info("deleting one person with id $id!")

    val entity = repository.findById(id)
      .orElseThrow { ResourceNotFoundException("No records found for this ID") }

    repository.delete(entity)
  }


}