package me.ktkoo.toy.product

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
class Product(
    @Id @GeneratedValue
    val id: Long? = null,
    val name: String,
    val price: BigDecimal,
    var stockQuantity: Long,
    @Enumerated(EnumType.STRING)
    var status: ProductStatus = ProductStatus.AVAILABLE,
) {
    fun deductProductStock(stockQuantity: Long) {
        if (stockQuantity > this.stockQuantity) {
            throw IllegalArgumentException("Requested quantity $stockQuantity exceeds available stock.")
        }

        this.stockQuantity -= stockQuantity

        if (this.stockQuantity == 0L) {
            this.status = ProductStatus.OUT_OF_STOCK
        }
    }

    fun getId(): Long {
        return id ?: throw Exception("Product id is null")
    }
}
