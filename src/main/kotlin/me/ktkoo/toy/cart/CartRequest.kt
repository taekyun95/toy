package me.ktkoo.toy.cart

data class CartRequest(
    val productId: Long,
    val quantity: Int,
)
