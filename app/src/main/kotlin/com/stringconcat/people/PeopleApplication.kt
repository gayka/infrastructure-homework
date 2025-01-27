package com.stringconcat.people

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(value = ["com.stringconcat.people"])
class PeopleApplication

fun main(vararg appArg: String) {
    runApplication<PeopleApplication>(*appArg)
}
