package me.ktkoo.toy.domain.item

import jakarta.persistence.*
import java.math.BigDecimal
import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.common.util.TokenGenerator
import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroup
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter

@Entity
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item-id-generator")
    @GenericGenerator(
        name = "item-id-generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "hibernate_sequence"),
            Parameter(name = "increment_size", value = "1000"),
            Parameter(name = "optimizer", value = "pooled-lo")
        ]
    )
    val id: Long? = null,

    val itemToken: String = TokenGenerator.randomCharacterWithPrefix("item_"),

    val partnerId: Long,
    var itemName: String,
    var itemPrice: BigDecimal,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = [CascadeType.PERSIST])
    val itemOptionGroupList: List<ItemOptionGroup> = listOf(),
    @Enumerated(EnumType.STRING)
    var status: Status = Status.PREPARE
) {
    init {
        if (itemName.isBlank()) throw InvalidParamException("Item.itemName must not be blank")
    }

    enum class Status(val description: String) {
        PREPARE("판매준비중"),
        ON_SALE("판매중"),
        END_OF_SALE("판매종료")
    }

    fun changeOnSale() {
        status = Status.ON_SALE
    }

    fun changeEndOfSale() {
        status = Status.END_OF_SALE
    }

    fun availableSales(): Boolean = status == Status.ON_SALE


}
