package me.ktkoo.toy.infrastructure.user

import me.ktkoo.toy.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>{
    fun findByUsername(username: String): User?

    fun findByUserToken(userToken: String): User?
}
