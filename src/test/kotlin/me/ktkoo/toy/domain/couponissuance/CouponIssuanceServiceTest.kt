package me.ktkoo.toy.domain.couponissuance

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import me.ktkoo.toy.domain.coupon.CouponInfo
import me.ktkoo.toy.infrastructure.couponissuance.CouponIssuanceRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CouponIssuanceServiceTest {

    @Autowired
    private lateinit var couponIssuanceService: CouponIssuanceService

    @Autowired
    private lateinit var couponIssuanceRepository: CouponIssuanceRepository

    @Test
    fun 한번만응모() {
        couponIssuanceService.registerIssuanceCoupon(
            CouponInfo.Main(
                1L, "couponToken1", 100
            ), 1L
        )

        val count = couponIssuanceRepository.count()
        assertThat(count).isEqualTo(1)
    }

    @Test
    fun 여러명응모() {
        val threadCount = 1000
        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(threadCount)

        for (i in 1..threadCount) {
            val userId = i
            executorService.execute {
                try {
                    couponIssuanceService.registerIssuanceCoupon(
                        CouponInfo.Main(
                            1L, "couponToken1", 100
                        ), userId.toLong()
                    )
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        val count = couponIssuanceRepository.count()
        assertThat(count).isEqualTo(100)
    }
}
