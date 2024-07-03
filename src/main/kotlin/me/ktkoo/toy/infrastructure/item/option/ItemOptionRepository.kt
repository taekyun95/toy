package me.ktkoo.toy.infrastructure.item.option

import me.ktkoo.toy.domain.item.option.ItemOption
import org.springframework.data.jpa.repository.JpaRepository


interface ItemOptionRepository : JpaRepository<ItemOption, Long>

