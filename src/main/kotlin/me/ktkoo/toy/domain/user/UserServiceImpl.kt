package me.ktkoo.toy.domain.user

import me.ktkoo.extensions.isValidEmail
import me.ktkoo.extensions.isValidPassword
import me.ktkoo.extensions.isValidPhoneNumber
import me.ktkoo.toy.infrastructure.user.UserRepository
import me.ktkoo.toy.interfaces.user.UserUpdateDto
import org.mapstruct.factory.Mappers
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userStore: UserStore,
    private val userRead: UserRead,
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,

) : UserService {
    var userInfoMapper: UserInfoMapper = Mappers.getMapper(UserInfoMapper::class.java)

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
            throw IllegalArgumentException("Username is already taken")
        }
        if (userRepository.existsByEmail(command.email)) {
            throw IllegalArgumentException("Email is already in use")
        }

        if (!command.password.isValidPassword()) {
            throw IllegalArgumentException("Invalid password format.")
        }

        if (!command.phoneNumber.isValidPhoneNumber()) {
            throw IllegalArgumentException("Invalid phone number format.")
        }
    }
}
