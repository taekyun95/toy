package me.ktkoo.toy.order

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface OrderService {
    fun createOrder(orderDto: OrderDto): Order
    fun updateOrderStatus(orderIds: List<Long>, status: OrderStatus): Int
    fun getOrders(userId: Long, pageable: Pageable): Page<List<Order>>
}
