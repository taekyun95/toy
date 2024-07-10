package me.ktkoo.toy.domain.partner

import jakarta.persistence.*
import me.ktkoo.common.AbstractEntity
import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.common.util.TokenGenerator.randomCharacterWithPrefix


@Entity
@Table(name = "partners")
class Partner constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val partnerToken: String = randomCharacterWithPrefix(PREFIX_PARTNER),
    val partnerName: String,
    val businessNo: String,
    val email: String,

    @Enumerated(EnumType.STRING)
    var status: Status = Status.ENABLE
) : AbstractEntity() {

    enum class Status(private val description: String) {
        ENABLE("활성화"), DISABLE("비활성화");
    }

    init {
        if (partnerName.isEmpty()) throw InvalidParamException("empty partnerName")
        if (businessNo.isEmpty()) throw InvalidParamException("empty businessNo")
        if (email.isEmpty()) throw InvalidParamException("empty email")
    }

    fun enable() {
        this.status = Status.ENABLE
    }

    fun disable() {
        this.status = Status.DISABLE
    }

    companion object {
        private const val PREFIX_PARTNER = "ptn_"
    }
}
