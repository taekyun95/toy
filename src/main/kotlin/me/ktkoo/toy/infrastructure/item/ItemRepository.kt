package me.ktkoo.toy.infrastructure.item

import me.ktkoo.toy.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository


interface ItemRepository : JpaRepository<Item, Long> {
    fun findByItemToken(itemToken: String): Item?
}
