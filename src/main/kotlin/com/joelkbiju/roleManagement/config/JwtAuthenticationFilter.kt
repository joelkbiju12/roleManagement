package com.joelkbiju.roleManagement.config

import com.joelkbiju.roleManagement.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.NonNull
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter : OncePerRequestFilter {
    private val jwtService: JwtService

    constructor(@Autowired jwtService: JwtService){
        this.jwtService = jwtService
    }

    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {
        val authHeader : String? =  request.getHeader("Authorization")
        val jwt: String?;
        val userEmail: String?;
        if ((authHeader == null) || ( !authHeader.startsWith("Bearer"))){
            filterChain.doFilter(request,response)
            return
        }
        jwt = authHeader.substring(7)
        userEmail = jwtService.extractUsername(authHeader)
    }

//    protected fun

}
