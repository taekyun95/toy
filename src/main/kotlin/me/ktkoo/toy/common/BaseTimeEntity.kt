package me.ktkoo.toy.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import java.time.ZonedDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseTimeEntity {
    @CreatedDate
    @Column(name = "created_at")
    val createdAt: ZonedDateTime = ZonedDateTime.now()

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: ZonedDateTime = ZonedDateTime.now()
}
