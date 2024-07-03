package me.ktkoo.toy.domain.user

interface UserInfoMapper {
    fun of(user: User): UserInfo.Main
}
