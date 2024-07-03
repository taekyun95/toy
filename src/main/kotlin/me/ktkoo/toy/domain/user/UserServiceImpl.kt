package me.ktkoo.toy.domain.user

import me.ktkoo.extensions.isValidEmail
import me.ktkoo.extensions.isValidPassword
import me.ktkoo.extensions.isValidPhoneNumber
import me.ktkoo.toy.infrastructure.user.UserRepository
import me.ktkoo.toy.interfaces.user.UserUpdateDto
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userStore: UserStore,
    private val userRepository: UserRepository, private val encoder: PasswordEncoder) : UserService {

    override fun createUser(command: UserCommand.RegisterUser): String {
        validateUserInput(command)
        val encodePassword = encoder.encode(command.password)
        val user = command.toEntity(encodePassword)
        userStore.store(user)
        return user.userToken
    }

    override fun updateUser(id: Long, userUpdateDto: UserUpdateDto): User {
        val existingUser =
            userRepository.findById(id).orElseThrow { NoSuchElementException("User not found.") }

        val updatedEmail = updateEmail(existingUser.email, userUpdateDto.email)
        val updatedPassword = updatePassword(existingUser.password, userUpdateDto.password)
        val updatedPhoneNumber = updatePhoneNumber(existingUser.phoneNumber, userUpdateDto.phoneNumber)

        val updatedUser = User(
            id = existingUser.id,
            username = existingUser.username,
            email = updatedEmail,
            password = updatedPassword,
            phoneNumber = updatedPhoneNumber,
        )

        userRepository.save(updatedUser)
        return updatedUser
    }

    @Transactional(readOnly = true)
    override fun getUser(id: Long): User {
        return userRepository.findById(id).orElseThrow { NoSuchElementException("User not found.") }
    }

    @Transactional(readOnly = true)
    override fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username)
            .orElseThrow { throw UsernameNotFoundException("User not found $username") }
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

    private fun <T> validateAndUpdateValue(
        existingValue: T,
        newValue: T?,
        validator: (T) -> Boolean,
    ): T {
        return newValue?.let {
            if (!validator(it)) {
                throw IllegalArgumentException("Invalid value format.")
            }
            it
        } ?: existingValue
    }

    private fun updateEmail(existingEmail: String, newEmail: String?): String {
        return validateAndUpdateValue(existingEmail, newEmail) { email -> email.isValidEmail() }
    }

    private fun updatePassword(existingPassword: String, newPassword: String?): String {
        return validateAndUpdateValue(
            existingPassword,
            newPassword,
        ) { password -> password.isValidPassword() }
    }

    private fun updatePhoneNumber(existingPhoneNumber: String, newPhoneNumber: String?): String {
        return validateAndUpdateValue(
            existingPhoneNumber,
            newPhoneNumber,
        ) { phoneNumber -> phoneNumber.isValidPhoneNumber() }
    }
}
