package me.ktkoo.toy.order

import me.ktkoo.toy.user.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    @Modifying
    @Query("UPDATE Order o SET o.status = :status WHERE o.id IN :orderIds")
    fun updateOrderStatus(orderIds: List<Long>, status: OrderStatus): Int
    fun findByUserId(userId: Long, pageable: Pageable): Page<List<Order>>
    fun findByUser(user: User, pageable: Pageable): Page<List<Order>>
}
