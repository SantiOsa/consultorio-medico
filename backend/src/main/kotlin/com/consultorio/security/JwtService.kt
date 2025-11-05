package com.consultorio.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.crypto.SecretKey

@Service
class JwtService {
    
    @Value("\${jwt.secret}")
    private lateinit var secret: String
    
    @Value("\${jwt.expiration}")
    private val expiration: Long = 0
    
    private val key: SecretKey by lazy {
        // Gerar uma chave segura com base no segredo configurado
        Keys.hmacShaKeyFor(secret.toByteArray())
    }
    
    fun extractUsername(token: String): String? {
        return try {
            extractClaim(token) { claims -> claims.subject }
        } catch (e: Exception) {
            null
        }
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token) { claims -> claims.expiration }
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }    fun generateToken(userDetails: UserDetails, userId: String, userType: String): String {
        val claims = mapOf(
            "userId" to userId,
            "userType" to userType
        )
        return createToken(claims, userDetails.username)
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(expiration)))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }
    
    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username && !isTokenExpired(token))
    }
}
