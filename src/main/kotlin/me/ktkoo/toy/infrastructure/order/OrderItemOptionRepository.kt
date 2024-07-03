package me.ktkoo.toy.infrastructure.order

import me.ktkoo.toy.domain.order.item.OrderItemOption
import org.springframework.data.jpa.repository.JpaRepository


interface OrderItemOptionRepository : JpaRepository<OrderItemOption, Long>

