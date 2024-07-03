package me.ktkoo.toy.infrastructure.user

import me.ktkoo.toy.domain.user.UserRead
import org.springframework.stereotype.Component

@Component
class UserReadImpl(private val userRepository: UserRepository) : UserRead{

}
