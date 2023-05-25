package me.ktkoo.toy.product

import java.math.BigDecimal

data class ProductResponseDto(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val stockQuantity: Long,
) {
    companion object {
        fun fromEntity(product: Product): ProductResponseDto {
            return ProductResponseDto(
                id = product.id ?: throw IllegalStateException("Product ID must not be null"),
                name = product.name,
                price = product.price,
                stockQuantity = product.stockQuantity,
            )
        }
    }
}
