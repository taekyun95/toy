package me.ktkoo.toy.domain.coupon

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.ktkoo.common.util.TokenGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter

@Entity
@Table(name = "coupons")
class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon-id-generator")
    @GenericGenerator(
        name = "coupon-id-generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "hibernate_sequence"),
            Parameter(name = "increment_size", value = "1000"),
            Parameter(name = "optimizer", value = "pooled-lo")
        ]
    )
    var id: Long? = null,

    val couponToken: String = TokenGenerator.randomCharacterWithPrefix("coupon_"),

    val count: Long
)
