package me.ktkoo.toy.common

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import java.time.ZonedDateTime
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseTimeEntity {
    @CreationTimestamp
    private val createdAt: ZonedDateTime? = null

    @UpdateTimestamp
    private val updatedAt: ZonedDateTime? = null
}
