package com.example.security.hashing

import io.ktor.server.application.*
import io.ktor.util.*
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class SHA256HashingServiceImpl:HashingService {
    override fun generateSaltedHash(value: String, saltLength: Int,application: Application): SaltedHash {
        val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLength)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$saltAsHex$value")



        return SaltedHash(
            hash = hash,
            salt = saltAsHex
        )

    }

    override fun verify(value: String, saltedHash: SaltedHash,app: Application): Boolean {
        app.log.info("data is ${DigestUtils.sha256Hex(saltedHash.salt+value)}")
        app.log.info("salted hash is ${saltedHash.hash}")
       return DigestUtils.sha256Hex(saltedHash.salt + value) == saltedHash.hash
    }
}