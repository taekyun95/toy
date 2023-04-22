package me.ktkoo.toy.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    @Modifying
    @Query("UPDATE Order o SET o.status = :status WHERE o.id IN :orderIds")
    fun updateOrderStatus(orderIds: List<Long>, status: OrderStatus): Int
}
