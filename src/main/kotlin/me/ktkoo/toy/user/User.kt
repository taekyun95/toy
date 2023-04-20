package me.ktkoo.toy.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(nullable = false)
    val email: String? = null,

    @Column(nullable = false)
    val password: String? = null,

    @Column(nullable = false)
    val phoneNumber: String? = null
) {
    companion object {
        fun fromDto(userDto: UserDto): User = User(
            email = userDto.email,
            password = userDto.password,
            phoneNumber = userDto.phoneNumber
        )
    }
}

