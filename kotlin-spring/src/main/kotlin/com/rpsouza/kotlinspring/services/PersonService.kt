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
}