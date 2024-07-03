package me.ktkoo.toy.domain.user

interface UserRead {
    fun getUser(userToken: String): User
    fun getUserByUsername(username: String): User
}
