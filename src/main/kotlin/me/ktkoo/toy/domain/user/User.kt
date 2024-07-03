package me.ktkoo.toy.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable
import me.ktkoo.common.util.TokenGenerator
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.cfg.AvailableSettings
import org.hibernate.id.enhanced.SequenceStyleGenerator

@Entity
@Table(name = "Users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user-id-generator")
    @GenericGenerator(
        name = "user-id-generator",
        strategy = "sequence",
        parameters = [
            Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "hibernate_sequence"),
            Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1000"),
            Parameter(name = AvailableSettings.PREFERRED_POOLED_OPTIMIZER, value = "pooled-lotl")
        ]
    )
    @Column(name = "id")
    val id: Long? = null,

    val userToken: String = TokenGenerator.randomCharacterWithPrefix("user_"),

    val username: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val phoneNumber: String,

    val roles: String = "ROLE_USER"

) : Serializable
