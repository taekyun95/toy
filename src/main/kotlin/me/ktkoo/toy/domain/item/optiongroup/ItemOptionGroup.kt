package me.ktkoo.toy.domain.item.optiongroup

import jakarta.persistence.*
import me.ktkoo.common.AbstractEntity
import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.item.Item
import me.ktkoo.toy.domain.item.option.ItemOption


@Entity
@Table(name = "item_option_groups")
class ItemOptionGroup(item: Item, ordering: Int, itemOptionGroupName: String) : AbstractEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @ManyToOne
    @JoinColumn(name = "item_id")
    private val item: Item
    val ordering: Int
    val itemOptionGroupName: String

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = [CascadeType.PERSIST])
    val itemOptionList: MutableList<ItemOption> = mutableListOf()

    init {
        if (itemOptionGroupName.isBlank()) throw InvalidParamException("ItemOptionGroup.itemOptionGroupName")

        this.item = item
        this.ordering = ordering
        this.itemOptionGroupName = itemOptionGroupName
    }

    fun addItemOption(itemOption: ItemOption): ItemOptionGroup {
        itemOptionList.add(itemOption)
        return this
    }
}
