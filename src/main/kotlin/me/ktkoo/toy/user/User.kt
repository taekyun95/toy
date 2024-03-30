package me.ktkoo.toy.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    val username: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val phoneNumber: String,

    val roles: String = "ROLE_USER"

) {
    companion object {
        fun fromDto(userDto: UserDto, password: String): User = User(
            username = userDto.username,
            email = userDto.email,
            password = password,
            phoneNumber = userDto.phoneNumber,
        )
    }
}
