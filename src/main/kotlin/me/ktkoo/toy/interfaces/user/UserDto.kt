package me.ktkoo.toy.interfaces.user

class UserDto{
    class RegisterUserRequest(
        val username: String,
        val email: String,
        val password: String,
        val phoneNumber: String
    )

    class RegisterResponse(
        private val orderToken: String,
    )
}

