package me.ktkoo.toy.infrastructure.user

import me.ktkoo.toy.domain.user.User
import java.util.Optional
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>{
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean

    fun findByUserToken(userToken: String): User?
}
