package me.ktkoo.toy.order

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface OrderService {
    fun createOrder(orderDto: OrderDto, username: String): Order
    fun updateOrderStatus(orderIds: List<Long>, status: OrderStatus): Int
    fun getOrders(userId: Long, pageable: Pageable): Page<List<Order>>
    fun getOrders(username: String, pageable: Pageable): Page<List<Order>>
}
