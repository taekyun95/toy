package me.ktkoo.toy.cart

import java.io.Serializable
import java.math.BigDecimal
import me.ktkoo.toy.product.ProductStatus

/**
 * DTO for {@link me.ktkoo.toy.cart.Cart}
 */
data class CartResponse(
    val id: Long,
    val userId: Long,
    val productId: Long,
    val productName: String,
    val productPrice: BigDecimal,
    val productStockQuantity: Long,
    val productStatus: ProductStatus,
    val quantity: Int,
) : Serializable
