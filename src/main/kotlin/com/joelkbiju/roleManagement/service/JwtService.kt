package com.joelkbiju.roleManagement.service

//import io.jsonwebtoken.
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec


@Service
class JwtService {

    @Value("\${application.security.jwt.secret-key}")
    private val secretKey: String? = null
    fun extractUsername(token: String): String?{
//        return extractClaim(token, Claims::subject)
        return null
    }

    fun <T> extractClaim(token: String?, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token!!)
        return claimsResolver.invoke(claims)
    }

    fun extractAllClaims(token: String) : Claims{
        return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws().body
    }


    private fun getSignInKey(): Key? {
        val keyBytes: ByteArray = Base64.getDecoder().decode(secretKey)
        return SecretKeySpec(keyBytes, "HmacSHA256")
    }

}

//e: file:///Users/apple/Desktop/roleManagement/src/main/kotlin/com/joelkbiju/roleManagement/service/JwtService.kt:18:13 Expecting member declaration

//e: file:///Users/apple/Desktop/roleManagement/src/main/kotlin/com/joelkbiju/roleManagement/service/JwtService.kt:29:74 No value passed for parameter 'p0'
