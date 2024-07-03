package me.ktkoo.toy.domain.user

import me.ktkoo.toy.interfaces.user.UserDto
import me.ktkoo.toy.interfaces.user.UserUpdateDto

interface UserService {
    fun store(command: UserCommand.RegisterUser): String
    fun updateUser(id: Long, userUpdateDto: UserUpdateDto): User
    fun getUser(id: Long): User
    fun getUserByUsername(username: String): User
}
