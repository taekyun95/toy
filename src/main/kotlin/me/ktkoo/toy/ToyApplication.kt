package me.ktkoo.toy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class ToyApplication

fun main(args: Array<String>) {
    runApplication<ToyApplication>(*args)
}
