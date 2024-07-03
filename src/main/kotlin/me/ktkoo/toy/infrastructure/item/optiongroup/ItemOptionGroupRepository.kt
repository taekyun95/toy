package me.ktkoo.toy.infrastructure.item.optiongroup

import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroup
import org.springframework.data.jpa.repository.JpaRepository


interface ItemOptionGroupRepository : JpaRepository<ItemOptionGroup, Long>
