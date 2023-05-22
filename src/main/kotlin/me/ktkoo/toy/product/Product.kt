package me.ktkoo.toy.product

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.math.BigDecimal
import java.math.BigInteger

@Entity
class Product(
    @Id @GeneratedValue
    private val id: Long? = null,
    private val name: String,
    private val price: BigDecimal,
    private var count: BigInteger,
    private var status: ProductStatus,
) {
    fun order(count: BigInteger) {
        if (count > this.count) {
            throw IllegalArgumentException("Requested quantity $count exceeds available stock.")
        }

        this.count = this.count.subtract(count)

        if (this.count == BigInteger.ZERO) {
            this.status = ProductStatus.OUT_OF_STOCK
        }
    }

    fun getPrice(): BigDecimal {
        return this.price
    }
}
