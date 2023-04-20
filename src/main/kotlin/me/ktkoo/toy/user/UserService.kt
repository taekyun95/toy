package me.ktkoo.toy.user

interface UserService {
    fun createUser(userDto: UserDto): User
    fun updateUser(id: Long, userUpdateDto: UserUpdateDto): User
}
