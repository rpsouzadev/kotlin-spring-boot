package com.rpsouza.kotlinspring.services

import com.rpsouza.kotlinspring.data.vo.v1.PersonVO
import com.rpsouza.kotlinspring.data.vo.v2.PersonVO as PersonVOV2
import com.rpsouza.kotlinspring.exceptions.ResourceNotFoundException
import com.rpsouza.kotlinspring.mapper.DozerMapper
import com.rpsouza.kotlinspring.mapper.custom.PersonMapper
import com.rpsouza.kotlinspring.model.Person
import com.rpsouza.kotlinspring.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {
  @Autowired
  private lateinit var repository: PersonRepository

  @Autowired
  private lateinit var mapper: PersonMapper

  private val logger = Logger.getLogger(PersonService::class.java.name)

  fun findAll(): List<PersonVO> {
    logger.info("Finding all people!")
    val listPerson = repository.findAll()

    return DozerMapper.parseListObjects(listPerson, PersonVO::class.java)
  }

  fun findById(id: Long): PersonVO {
    logger.info("Finding one person!")
    val person = repository.findById(id)
      .orElseThrow { ResourceNotFoundException("No records found for this ID") }

    return DozerMapper.parseObject(person, PersonVO::class.java)
  }

  fun createPerson(person: PersonVO): PersonVO {
    logger.info("Creating one person with name ${person.firstName}!")
    val entity: Person = DozerMapper.parseObject(person, Person::class.java)

    return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
  }

  fun createPersonV2(person: PersonVOV2): PersonVOV2 {
    logger.info("Creating one person with name ${person.firstName}!")
    val entity: Person = mapper.mapVoToEntity(person)

    return mapper.mapEntityToVo(repository.save(entity))
  }

  fun updatePerson(person: PersonVO): PersonVO {
    logger.info("Updating one person with id ${person.id}!")

    val entity = repository.findById(person.id)
      .orElseThrow { ResourceNotFoundException("No records found for this ID") }

      entity.firstName = person.firstName
      entity.lastName = person.lastName
      entity.address = person.address
      entity.gender = person.gender

    return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
  }

  fun deletePerson(id: Long) {
    logger.info("deleting one person with id $id!")

    val entity = repository.findById(id)
      .orElseThrow { ResourceNotFoundException("No records found for this ID") }

    repository.delete(entity)
  }
}