package me.ktkoo.toy.domain.order

import me.ktkoo.toy.domain.order.item.OrderItemOption
import me.ktkoo.toy.domain.order.item.OrderItem
import me.ktkoo.toy.domain.order.item.OrderItemOptionGroup

interface OrderStore {
    fun store(order: Order): Order
    fun store(orderItem: OrderItem): OrderItem
    fun store(orderItemOptionGroup: OrderItemOptionGroup): OrderItemOptionGroup
    fun store(orderItemOption: OrderItemOption): OrderItemOption
}
