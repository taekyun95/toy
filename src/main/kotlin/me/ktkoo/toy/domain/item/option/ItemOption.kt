package me.ktkoo.toy.domain.item.option

import jakarta.persistence.*
import me.ktkoo.common.AbstractEntity
import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroup


@Entity
@Table(name = "item_options")
class ItemOption constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "item_option_group_id")
    private val itemOptionGroup: ItemOptionGroup,
    val ordering: Int,
    val itemOptionName: String,
    val itemOptionPrice: Long
) : AbstractEntity() {


    init {
        if (itemOptionName.isBlank()) throw InvalidParamException("ItemOption.itemOptionName")
    }
}
