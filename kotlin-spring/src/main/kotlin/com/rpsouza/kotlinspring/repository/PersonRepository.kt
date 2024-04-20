package com.rpsouza.kotlinspring.repository

import com.rpsouza.kotlinspring.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long?>