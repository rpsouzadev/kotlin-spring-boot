package com.rpsouza.kotlinspring.mapper.custom

import com.rpsouza.kotlinspring.data.vo.v2.PersonVO
import com.rpsouza.kotlinspring.model.Person
import org.springframework.stereotype.Service
import java.util.Date

@Service
class PersonMapper {

  fun mapEntityToVo(person: Person): PersonVO {
    val vo = PersonVO()

    vo.id = person.id
    vo.firstName = person.firstName
    vo.lastName = person.lastName
    vo.address = person.address
    vo.gender = person.gender
    vo.birthDay = Date()

    return vo
  }

  fun mapVoToEntity(person: PersonVO): Person {
    val entity = Person()

    entity.id = person.id
    entity.firstName = person.firstName
    entity.lastName = person.lastName
    entity.address = person.address
    entity.gender = person.gender

    return entity
  }
}