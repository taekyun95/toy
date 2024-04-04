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
import me.ktkoo.toy.user.User
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.cfg.AvailableSettings
import org.hibernate.id.enhanced.SequenceStyleGenerator

@Entity
@Table(name = "orders")
class Order(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order-id-generator")
    @GenericGenerator(
        name = "order-id-generator",
        strategy = "sequence",
        parameters = [
            Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "hibernate_sequence"),
            Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1000"),
            Parameter(name = AvailableSettings.PREFERRED_POOLED_OPTIMIZER, value = "pooled-lotl")
        ]
    )
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne
    val user: User,

    @Column(name = "order_date_time", nullable = false)
    val orderDateTime: ZonedDateTime = ZonedDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: OrderStatus = OrderStatus.PENDING,
)
