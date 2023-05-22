package me.ktkoo.toy.order

enum class OrderStatus {
    PENDING, // 대기중
    CONFIRMED, // 확인됨
    SHIPPED, // 배송 시작됨
    DELIVERED, // 배송 완료됨
    CANCELLED, // 취소됨
}
