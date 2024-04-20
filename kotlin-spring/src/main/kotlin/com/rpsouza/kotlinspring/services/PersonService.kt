package com.rpsouza.kotlinspring.services

import com.rpsouza.kotlinspring.controller.PersonController
import com.rpsouza.kotlinspring.data.vo.v1.PersonVO
import com.rpsouza.kotlinspring.exceptions.RequiredObjectIsNullException
import com.rpsouza.kotlinspring.data.vo.v2.PersonVO as PersonVOV2
import com.rpsouza.kotlinspring.exceptions.ResourceNotFoundException
import com.rpsouza.kotlinspring.mapper.DozerMapper
import com.rpsouza.kotlinspring.mapper.custom.PersonMapper
import com.rpsouza.kotlinspring.model.Person
import com.rpsouza.kotlinspring.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
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

    val vos = DozerMapper.parseListObjects(listPerson, PersonVO::class.java)

    for (person in vos) {
      val withSelfRel = linkTo(PersonController::class.java).slash(person.key).withSelfRel()

      person.add(withSelfRel)
    }

    return vos
  }

  fun findById(id: Long): PersonVO {
    logger.info("Finding one person by id $id!")
    val person = repository.findById(id)
      .orElseThrow { ResourceNotFoundException("No records found for this ID") }

    val personVO: PersonVO = DozerMapper.parseObject(person, PersonVO::class.java)
    val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
    personVO.add(withSelfRel)

    return personVO
  }

  fun createPerson(person: PersonVO?): PersonVO {
    if (person == null) throw RequiredObjectIsNullException()
    logger.info("Creating one person with name ${person.firstName}!")
    val entity: Person = DozerMapper.parseObject(person, Person::class.java)

    val personVO: PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
    personVO.add(withSelfRel)

    return personVO
  }

  fun createPersonV2(person: PersonVOV2): PersonVOV2 {
    logger.info("Creating one person with name ${person.firstName}!")
    val entity: Person = mapper.mapVoToEntity(person)

    return mapper.mapEntityToVo(repository.save(entity))
  }

  fun updatePerson(person: PersonVO?): PersonVO {
    if (person == null) throw RequiredObjectIsNullException()
    logger.info("Updating one person with id ${person.key}!")

    val entity = repository.findById(person.key)
      .orElseThrow { ResourceNotFoundException("No records found for this ID") }

    entity.firstName = person.firstName
    entity.lastName = person.lastName
    entity.address = person.address
    entity.gender = person.gender

    val personVO: PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)

    val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
    personVO.add(withSelfRel)

    return personVO
  }

  fun deletePerson(id: Long) {
    logger.info("deleting one person with id $id!")

    val entity = repository.findById(id)
      .orElseThrow { ResourceNotFoundException("No records found for this ID") }

    repository.delete(entity)
  }
}