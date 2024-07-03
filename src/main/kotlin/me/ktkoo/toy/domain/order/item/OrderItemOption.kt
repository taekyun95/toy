package dev.practice.order.domain.order.item

import jakarta.persistence.*
import me.ktkoo.common.AbstractEntity
import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.order.item.OrderItemOptionGroup

@Entity
@Table(name = "order_item_options")
class OrderItemOption(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "order_item_option_group_id")
    val orderItemOptionGroup: OrderItemOptionGroup,

    val ordering: Int,
    val itemOptionName: String,
    val itemOptionPrice: Long
) : AbstractEntity() {
    init {
        if(ordering >= 0) { throw InvalidParamException("Ordering must be non-negative") }
        if(itemOptionName.isNotBlank()) { throw InvalidParamException("ItemOptionName cannot be blank") }
        if(itemOptionPrice >= 0) { throw InvalidParamException("ItemOptionPrice must be non-negative") }
    }

    companion object {
    }
}
