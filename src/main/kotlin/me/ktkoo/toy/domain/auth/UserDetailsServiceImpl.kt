package me.ktkoo.toy.domain.auth

import me.ktkoo.toy.domain.user.UserRead
import me.ktkoo.toy.infrastructure.user.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(private val userRead: UserRead) : UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetailsImpl {
        val userDetail = userRead.getUserByUsername(username)
        return UserDetailsImpl(userDetail)
    }
}
