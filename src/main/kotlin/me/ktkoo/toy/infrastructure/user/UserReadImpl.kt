package me.ktkoo.toy.infrastructure.user

import me.ktkoo.common.exception.EntityNotFoundException
import me.ktkoo.toy.domain.user.User
import me.ktkoo.toy.domain.user.UserRead
import org.springframework.stereotype.Component

@Component
class UserReadImpl(private val userRepository: UserRepository) : UserRead {
    override fun getUser(userToken: String): User {
        return userRepository.findByUserToken(userToken) ?: throw EntityNotFoundException()
    }

    override fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username) ?: throw EntityNotFoundException()
    }
}
