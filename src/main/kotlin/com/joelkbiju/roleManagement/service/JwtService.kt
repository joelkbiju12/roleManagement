package com.joelkbiju.roleManagement.service

// import io.jsonwebtoken.
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsChecker
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
class JwtService {

    @Value("\${application.security.jwt.secret-key}")
    private val secretKey: String? = null

    fun generateToken(extraClaims: Map<String, Object>, userDetails: UserDetails ) : String {
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000*60))
            .signWith(SignatureAlgorithm.HS256,getSignInKey())
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails ): Boolean {
        val username : String = extractUsername(token)
        return (username == userDetails.username) && !(isTokenExpired(token))
    }

    fun isTokenExpired(token: String): Boolean {
        return extractExpiry(token) { claims -> claims.expiration }.before(Date(System.currentTimeMillis()))
    }

    private fun extractExpiry(token: String, claimExtractor: (Claims) -> Date): Date {
        return extractClaim(token, claimExtractor)
    }

    fun extractUsername(token: String): String {
        return extractClaim(token) { claims -> claims.subject }
    }

    fun <T> extractClaim(token: String?, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token!!)
        return claimsResolver.invoke(claims)
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).body
    }

    private fun getSignInKey(): Key {
        val keyBytes: ByteArray = Base64.getDecoder().decode(secretKey)
        return SecretKeySpec(keyBytes, "HmacSHA256")
    }
}
