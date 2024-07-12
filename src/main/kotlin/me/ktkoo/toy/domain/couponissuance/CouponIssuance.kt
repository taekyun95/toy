package me.ktkoo.toy.domain.couponissuance

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.ktkoo.common.util.TokenGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter

@Entity
@Table(name = "coupon_issuances")
class CouponIssuance(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon-issuance-id-generator")
    @GenericGenerator(
        name = "coupon-issuance-id-generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "hibernate_sequence"),
            Parameter(name = "increment_size", value = "1000"),
            Parameter(name = "optimizer", value = "pooled-lo")
        ]
    )
    val id: Long? = null,

    val couponIssuanceToken: String = TokenGenerator.randomCharacterWithPrefix("couponissuance_"),

    val couponId: Long,
    val couponToken: String,

    val userId: Long
)
