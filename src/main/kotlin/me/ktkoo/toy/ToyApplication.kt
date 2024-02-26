package me.ktkoo.toy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ToyApplication

fun main(args: Array<String>) {
    runApplication<ToyApplication>(*args)
}
