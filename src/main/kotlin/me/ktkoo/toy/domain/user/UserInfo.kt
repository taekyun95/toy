package me.ktkoo.toy.domain.user

class UserInfo {
    class Main(
        val userId: Long?,
        val userToken: String,
        val username: String,
        val email: String,
        val phoneNumber: String
    )
}
