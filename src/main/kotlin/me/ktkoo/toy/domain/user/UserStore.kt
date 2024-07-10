package me.ktkoo.toy.domain.user

interface UserStore {
    fun store(user: User): User
}
