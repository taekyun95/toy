package me.ktkoo.toy.infrastructure.notification

import me.ktkoo.toy.domain.notification.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Component
class NotificationExecutor : NotificationService {
    companion object {
        private val log = LoggerFactory.getLogger(NotificationExecutor::class.java)
    }

    override fun sendEmail(email: String?, title: String?, description: String?) {
        log.info("sendEmail")
    }

    override fun sendKakao(phoneNo: String?, description: String?) {
        log.info("sendKakao")
    }

    override fun sendSms(phoneNo: String, description: String) {
        log.info("sendSms")
    }
}
