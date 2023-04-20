package me.ktkoo.toy.extensions

fun String.isValidEmail(): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return this.matches(emailPattern.toRegex())
}

fun String.isValidPassword(): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}\$"
    return this.matches(passwordPattern.toRegex())
}

fun String.isValidPhoneNumber(): Boolean {
    val phoneNumberPattern = """^\+(?:[0-9] ?){6,14}[0-9]$""".toRegex()
    return this.matches(phoneNumberPattern)
}
