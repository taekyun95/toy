package me.ktkoo.toy.order

data class OrderDto(
    val userId: Long,
    val status: OrderStatus
)