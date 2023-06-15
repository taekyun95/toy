package me.ktkoo.toy.cart

data class CartRequest(
    val userId: Long,
    val productId: Long,
    val quantity: Int,
)
