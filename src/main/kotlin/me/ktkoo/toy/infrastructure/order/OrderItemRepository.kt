package me.ktkoo.toy.infrastructure.order

import me.ktkoo.toy.domain.order.item.OrderItem
import org.springframework.data.jpa.repository.JpaRepository


interface OrderItemRepository : JpaRepository<OrderItem, Long>
