package com.hilguener.marvelsuperheroes.data.util

import com.hilguener.marvelsuperheroes.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com"
        val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = BuildConfig.MARVEL_API_KEY
        private const val PRIVATE_KEY = BuildConfig.MARVEL_PRIVATE_KEY
        const val LIMIT = 20

        fun hash(): String {
            val input = "$timestamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
            return hash
        }
    }
}
