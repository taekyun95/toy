package me.ktkoo.toy.product

import common.BaseEntity
import jakarta.persistence.Entity
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
    var status: ProductStatus = ProductStatus.AVAILABLE,
) : BaseEntity() {
    fun order(stockQuantity: Long) {
        if (stockQuantity > this.stockQuantity) {
            throw IllegalArgumentException("Requested quantity $stockQuantity exceeds available stock.")
        }

        this.stockQuantity -= stockQuantity

        if (this.stockQuantity == 0L) {
            this.status = ProductStatus.OUT_OF_STOCK
        }
    }
}
