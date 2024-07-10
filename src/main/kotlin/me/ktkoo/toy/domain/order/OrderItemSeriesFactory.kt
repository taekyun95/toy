package me.ktkoo.toy.domain.order

import me.ktkoo.toy.domain.order.OrderCommand.RegisterOrder
import me.ktkoo.toy.domain.order.item.OrderItem


interface OrderItemSeriesFactory {
    fun store(order: Order, requestOrder: RegisterOrder): List<OrderItem>
}
