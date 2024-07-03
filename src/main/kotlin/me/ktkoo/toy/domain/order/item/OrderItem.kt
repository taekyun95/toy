package me.ktkoo.toy.domain.order.item

import jakarta.persistence.*
import me.ktkoo.common.AbstractEntity
import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.order.Order


@Entity
@Table(name = "order_items")
class OrderItem constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "order_id")
    private val order: Order,

    private val orderCount: Int,
    private val partnerId: Long,
    private val itemId: Long,
    private val itemName: String,
    private val itemToken: String,
    private val itemPrice: Long,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItem", cascade = [CascadeType.PERSIST])
    private val orderItemOptionGroupList: List<OrderItemOptionGroup> = listOf(),

    @Enumerated(EnumType.STRING)
    private val deliveryStatus: DeliveryStatus = DeliveryStatus.BEFORE_DELIVERY
) : AbstractEntity() {


    enum class DeliveryStatus(private val description: String) {
        BEFORE_DELIVERY("배송전"),
        DELIVERY_PREPARE("배송준비중"),
        DELIVERING("배송중"),
        COMPLETE_DELIVERY("배송완료");
    }

    init {
        if (itemName.isEmpty()) throw InvalidParamException("OrderItemLine.itemNo and itemName")
        if (itemToken.isEmpty()) throw InvalidParamException("OrderItemLine.itemToken")
    }

    fun calculateTotalAmount(): Long {
        val itemOptionTotalAmount = orderItemOptionGroupList.sumOf { it.calculateTotalAmount() }
        return (itemPrice + itemOptionTotalAmount) * orderCount
    }
}
