package me.ktkoo.toy.domain.partner

interface PartnerStore {
    fun store(initPartner: Partner): Partner
}
