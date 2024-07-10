package me.ktkoo.toy.interfaces.user

data class UserUpdateDto(
    val email: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null,
)
