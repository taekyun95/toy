package me.ktkoo.toy.infrastructure.order

import me.ktkoo.toy.domain.order.item.OrderItemOptionGroup
import org.springframework.data.jpa.repository.JpaRepository


interface OrderItemOptionGroupRepository : JpaRepository<OrderItemOptionGroup, Long>
