package com.example.security.hashing

import io.ktor.server.application.*


interface HashingService {

    fun generateSaltedHash(value:String , saltLength:Int = 32,application: Application):SaltedHash

    fun verify(value: String,saltedHash: SaltedHash,app:Application):Boolean

}