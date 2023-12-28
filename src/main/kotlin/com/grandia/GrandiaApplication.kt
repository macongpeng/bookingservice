package com.grandia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrandiaApplication

fun main(args: Array<String>) {
    runApplication<GrandiaApplication>(*args)
}
