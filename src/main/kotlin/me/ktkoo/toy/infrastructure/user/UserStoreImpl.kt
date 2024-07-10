package me.ktkoo.toy.infrastructure.user

import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.user.User
import me.ktkoo.toy.domain.user.UserStore
import org.springframework.stereotype.Component

@Component
class UserStoreImpl(private val userRepository: UserRepository) : UserStore {
    override fun store(user: User): User {
        return userRepository.save(user)
    }
}
