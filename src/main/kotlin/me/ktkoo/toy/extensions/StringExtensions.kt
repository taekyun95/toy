package me.ktkoo.toy.extensions

/**
 * 이메일은 @ 기호를 포함해야 합니다.
 * 이메일은 . 기호를 포함해야 합니다.
 * 이메일은 영문자, 숫자, 특수문자를 포함해야 합니다.
 * 이메일은 영문자, 숫자, 특수문자, ., _ 를 포함해야 합니다.
 * 이메일은 영문자, 숫자로 시작해야 합니다.
 * 이메일은 영문자, 숫자로 끝나야 합니다.
 * 예시: test@test.com
 */
fun String.isValidEmail(): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return this.matches(emailPattern.toRegex())
}

/**
 * 적어도 한 개의 숫자를 포함해야 합니다. (예: 0-9)
 * 적어도 한 개의 소문자를 포함해야 합니다. (예: a-z)
 * 적어도 한 개의 대문자를 포함해야 합니다. (예: A-Z)
 * 적어도 한 개의 특수문자를 포함해야 합니다. (예: @#$%^&+=)
 * 공백은 포함되어서는 안됩니다.
 * 길이가 최소한 8개 이상이어야 합니다.
 * 예시: OpenAI@123
 */
fun String.isValidPassword(): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}\$"
    return this.matches(passwordPattern.toRegex())
}

/**
 * 전화번호는 국가 코드를 포함해야 합니다. (예: +82)
 * 국가 코드는 숫자로만 이루어져야 합니다.
 * 국가 코드와 나머지 전화번호는 공백으로 구분되어야 합니다.
 * 나머지 전화번호는 숫자로만 이루어져야 합니다.
 * 나머지 전화번호는 최소한 6자리 이상이어야 합니다.
 * 예시: +82 10 1234 5678
 */
fun String.isValidPhoneNumber(): Boolean {
    val phoneNumberPattern = """^\+(?:[0-9] ?){6,14}[0-9]$""".toRegex()
    return this.matches(phoneNumberPattern)
}
