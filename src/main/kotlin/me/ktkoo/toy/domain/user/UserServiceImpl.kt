package me.ktkoo.toy.domain.user

import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.extensions.isValidPassword
import me.ktkoo.extensions.isValidPhoneNumber
import me.ktkoo.toy.infrastructure.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userStore: UserStore,
    private val userRead: UserRead,
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val userInfoMapper: UserInfoMapper
) : UserService {

    override fun store(command: UserCommand.RegisterUser): String {
        validateUserInput(command)
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

    private fun validateUserInput(command: UserCommand.RegisterUser) {
        if (userRepository.existsByUsername(command.username)) {
            throw InvalidParamException("Username is already taken")
        }
        if (userRepository.existsByEmail(command.email)) {
            throw InvalidParamException("Email is already in use")
        }

        if (!command.password.isValidPassword()) {
            throw InvalidParamException("Invalid password format.")
        }

        if (!command.phoneNumber.isValidPhoneNumber()) {
            throw InvalidParamException("Invalid phone number format.")
        }
    }
}
