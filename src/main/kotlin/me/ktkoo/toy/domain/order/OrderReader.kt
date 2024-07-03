package me.ktkoo.toy.domain.order

interface OrderReader {
    fun getOrder(orderToken: String): Order
}
