package me.ktkoo.toy.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.ZonedDateTime
import me.ktkoo.toy.common.BaseEntity
import me.ktkoo.toy.user.User

@Entity
@Table(name = "orders")
class Order(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne
    val user: User,

    @Column(name = "order_date_time", nullable = false)
    val orderDateTime: ZonedDateTime = ZonedDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: OrderStatus = OrderStatus.PENDING,
) : BaseEntity()
