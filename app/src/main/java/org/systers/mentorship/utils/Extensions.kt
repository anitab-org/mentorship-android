package org.systers.mentorship.utils

import java.math.BigInteger
import java.security.MessageDigest

// encrypts the given String via SHA 256 and returns a hex representation of it
fun String.encrypt(): String {
    val digest = MessageDigest.getInstance("SHA-256")
    digest.update(this.toByteArray())
    return String.format("%064x", BigInteger(1, digest.digest()))
}