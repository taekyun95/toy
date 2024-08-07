package me.ktkoo.toy.domain.user

class UserCommand {
    data class RegisterUser(
        val username: String,
        val email: String,
        val password: String,
        val phoneNumber: String
    ) {

        fun toEntity(encodePassword: String): User {
            return User(
                username = username,
                email = email,
                password = encodePassword,
                phoneNumber = phoneNumber,
            )
        }
    }

    data class UpdateUser(
        val email: String,
        val password: String,
        val phoneNumber: String,
    )
}
