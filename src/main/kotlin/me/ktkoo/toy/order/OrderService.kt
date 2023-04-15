package me.ktkoo.toy.order

interface OrderService {
    fun createOrder(orderDto: OrderDto): Order
    fun updateOrderStatus(orderIds: List<Long>, status: OrderStatus): Int
}