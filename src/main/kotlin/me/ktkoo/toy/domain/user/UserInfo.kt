package me.ktkoo.toy.domain.user

import java.time.ZonedDateTime
import me.ktkoo.toy.domain.order.OrderInfo

class UserInfo {
    class Main(
        private val userId: Long,
        private val userToken: String,
        private val username: String,
        private val email: String,
        private val phoneNumber: String
    )
}
