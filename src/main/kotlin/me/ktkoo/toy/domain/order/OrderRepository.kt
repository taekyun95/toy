package me.ktkoo.toy.domain.order

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun findByOrderToken(orderToken: String): Order?
}

