package me.ktkoo.toy.domain.user

import org.mapstruct.factory.Mappers
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userStore: UserStore,
    private val userRead: UserRead,
    private val encoder: PasswordEncoder,
) : UserService {

    private val userInfoMapper = Mappers.getMapper(UserInfoMapper::class.java)

    override fun store(command: UserCommand.RegisterUser): String {
        val encodePassword = encoder.encode(command.password)
        val user = command.toEntity(encodePassword)
        userStore.store(user)
        return user.userToken
    }

    @Transactional(readOnly = true)
    override fun getUser(userToken: String): UserInfo.Main {
        val user = userRead.getUser(userToken)
        return userInfoMapper.of(user)
    }
}
