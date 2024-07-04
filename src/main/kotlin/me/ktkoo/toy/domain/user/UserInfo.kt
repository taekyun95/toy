package me.ktkoo.toy.domain.user

import java.time.ZonedDateTime
import me.ktkoo.toy.domain.order.OrderInfo

class UserInfo {
    class Main(
        val userId: Long?,
        val userToken: String,
        val username: String,
        val email: String,
        val phoneNumber: String
    )
}
