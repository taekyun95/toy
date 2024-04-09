package me.ktkoo.toy.product

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.cfg.AvailableSettings
import org.hibernate.id.enhanced.SequenceStyleGenerator

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product-id-generator")
    @GenericGenerator(
        name = "order-product-id-generator",
        strategy = "sequence",
        parameters = [
            Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "hibernate_sequence"),
            Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1000"),
            Parameter(name = AvailableSettings.PREFERRED_POOLED_OPTIMIZER, value = "pooled-lotl")
        ]
    )
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

    fun toResponseDto(): ProductResponseDto {
        return ProductResponseDto(
            id = this.id!!,
            name = this.name,
            price = this.price,
            stockQuantity = this.stockQuantity,
        )
    }
}
