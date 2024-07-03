package me.ktkoo.toy.domain.item

interface ItemStore {
    fun store(initItem: Item): Item
}

