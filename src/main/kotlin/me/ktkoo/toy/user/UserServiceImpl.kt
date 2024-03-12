package me.ktkoo.toy.user

import me.ktkoo.toy.extensions.isValidEmail
import me.ktkoo.toy.extensions.isValidPassword
import me.ktkoo.toy.extensions.isValidPhoneNumber
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository, private val encoder: PasswordEncoder) : UserService {

    override fun createUser(userDto: UserDto): User {
        validateUserInput(userDto)
        userDto.encodingPassword = encoder.encode(userDto.password)
        val user = User.fromDto(userDto)

        userRepository.save(user)
        return user
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

    @Transactional
    override fun getUser(id: Long): User {
        return userRepository.findById(id).orElseThrow { NoSuchElementException("User not found.") }
    }

    override fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username)
            .orElseThrow { throw UsernameNotFoundException("User not found $username") }
    }

    private fun validateUserInput(userDto: UserDto) {
        if (!userDto.email.isValidEmail()) {
            throw IllegalArgumentException("Invalid email format.")
        }

        if (!userDto.password.isValidPassword()) {
            throw IllegalArgumentException("Invalid password format.")
        }

        if (!userDto.phoneNumber.isValidPhoneNumber()) {
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
