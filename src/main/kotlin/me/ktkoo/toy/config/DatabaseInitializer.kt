package me.ktkoo.toy.config

import java.math.BigDecimal
import me.ktkoo.toy.product.Product
import me.ktkoo.toy.product.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DatabaseInitializer(private val productRepository: ProductRepository) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String) {
        (1..1000).forEach {
            productRepository.save(
                Product(
                    name = "이름$it",
                    price = BigDecimal(10_00 * it),
                    stockQuantity = (10 * it).toLong(),
                ),
            )
        }
    }
}
