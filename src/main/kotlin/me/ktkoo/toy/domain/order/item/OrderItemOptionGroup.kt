package me.ktkoo.toy.domain.order.item

import jakarta.persistence.*
import me.ktkoo.common.AbstractEntity
import me.ktkoo.common.exception.InvalidParamException


@Entity
@Table(name = "order_item_option_groups")
class OrderItemOptionGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    val orderItem: OrderItem,
    val ordering: Int,
    val itemOptionGroupName: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItemOptionGroup", cascade = [CascadeType.PERSIST])
    val orderItemOptionList: MutableList<OrderItemOption> = ArrayList()
) : AbstractEntity() {

    init {
        if (itemOptionGroupName.isBlank()) {
            throw InvalidParamException("ItemOptionGroupName cannot be blank")
        }
    }

    fun calculateTotalAmount(): Long = orderItemOptionList.sumOf { it.itemOptionPrice }
}
