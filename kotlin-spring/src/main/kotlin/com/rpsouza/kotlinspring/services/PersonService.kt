package com.rpsouza.kotlinspring.services

import com.rpsouza.kotlinspring.model.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {
  private val counter: AtomicLong = AtomicLong()
  private val logger = Logger.getLogger(PersonService::class.java.name)

  fun findById(id: Long): Person {
    logger.info("Finding one person!")

    val person = Person(
      id = counter.incrementAndGet(),
      firstName = "Rafael",
      lastName = "Souza",
      address = "Salvador-Ba",
      gender = "Male"
    )

    return person
  }

  fun findAll(): List<Person> {
    logger.info("Finding all people!")

    val persons: MutableList<Person> = ArrayList()

    for (i in 0..7) {
      val person = mockPerson(i)
      persons.add(person)
    }

    return persons
  }

  fun createPerson(person: Person) = person

  fun updatePerson(person: Person) = person

  fun deletePerson(id: Long) {}

  private fun mockPerson(i: Int): Person {
    val person = Person(
      id = counter.incrementAndGet(),
      firstName = "Person name $i",
      lastName = "Last name $i",
      address = "Some Address in Brazil",
      gender = "Male"
    )

    return person
  }
}