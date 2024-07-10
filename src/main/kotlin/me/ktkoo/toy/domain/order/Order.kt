package me.ktkoo.toy.domain.order

import jakarta.persistence.*
import java.time.ZonedDateTime
import me.ktkoo.common.exception.IllegalStatusException
import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.common.util.TokenGenerator.randomCharacterWithPrefix
import me.ktkoo.toy.domain.order.fragment.DeliveryFragment
import me.ktkoo.toy.domain.order.item.OrderItem


@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var orderToken: String = randomCharacterWithPrefix(ORDER_PREFIX),

    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    var payMethod: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = [CascadeType.PERSIST])
    val orderItemList: List<OrderItem> = listOf(),

    @Embedded
    val deliveryFragment: DeliveryFragment,

    @Column(nullable = false)
    var orderedAt: ZonedDateTime = ZonedDateTime.now(),

    @Enumerated(EnumType.STRING)
    var status: Status = Status.INIT
) {
    init {
        if (payMethod.isEmpty()) throw InvalidParamException("Order.payMethod must not be empty.")
    }

    enum class Status(val description: String) {
        INIT("주문시작"),
        ORDER_COMPLETE("주문완료"),
        DELIVERY_PREPARE("배송준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료");
    }

    /**
     * 주문 가격 = 주문 상품의 총 가격
     * 주문 하나의 상품의 가격 = (상품 가격 + 상품 옵션 가격) * 주문 갯수
     */
    fun calculateTotalAmount(): Long =
        orderItemList.sumOf { it.calculateTotalAmount() }

    fun orderComplete() {
        if (this.status != Status.INIT) throw IllegalStatusException()
        this.status = Status.ORDER_COMPLETE
    }

    fun isAlreadyPaymentComplete(): Boolean {
        return status in setOf(Status.ORDER_COMPLETE, Status.DELIVERY_PREPARE, Status.IN_DELIVERY, Status.DELIVERY_COMPLETE)
    }


    companion object {
        private const val ORDER_PREFIX = "ord_"
    }
}
