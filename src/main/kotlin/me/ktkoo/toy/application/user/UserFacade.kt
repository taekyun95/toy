package me.ktkoo.toy.application.user

import me.ktkoo.toy.domain.user.UserCommand
import me.ktkoo.toy.domain.user.UserInfo
import me.ktkoo.toy.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class UserFacade(private val userService: UserService) {
    fun registerUser(command: UserCommand.RegisterUser): String {
        return userService.store(command)
    }

    fun getUser(userToken: String): UserInfo.Main {
        return userService.getUser(userToken)
    }
}
