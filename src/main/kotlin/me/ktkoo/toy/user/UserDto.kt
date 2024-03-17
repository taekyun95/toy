package me.ktkoo.toy.user

data class UserDto(
    val username: String,  // 사용자명
    val email: String,      // 사용자 이메일
    val password: String,   // 사용자 비밀번호
    val phoneNumber: String // 사용자 전화번호
)
