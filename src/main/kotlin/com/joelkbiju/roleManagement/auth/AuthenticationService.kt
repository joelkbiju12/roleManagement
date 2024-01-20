package com.joelkbiju.roleManagement.auth

import com.joelkbiju.roleManagement.model.Role
import com.joelkbiju.roleManagement.model.User
import com.joelkbiju.roleManagement.repository.UserRepository
import com.joelkbiju.roleManagement.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService @Autowired constructor(
    private val userRepo: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
) {

    fun register(registerRequest: RegisterRequest): AuthenticationResponse {
        try {
            var user: User = User(null, registerRequest.firstName, registerRequest.lastName, registerRequest.email, passwordEncoder.encode(registerRequest.password), Role.USER)
            userRepo.save(user)

            val jwtToken = jwtService.generateToken(userDetails = user)
            println("JWT Token: $jwtToken")
            return AuthenticationResponse(jwtToken)
        } catch (ex: Exception) {
            // Log the exception for debugging purposes
            ex.printStackTrace()
            // Return an appropriate response, such as an error message
            return AuthenticationResponse("Error during user registration")
        }
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password),
        )
        var user = userRepo.findByEmail(request.email)
        val jwtToken = jwtService.generateToken(userDetails = user)
        println("JWT Token: $jwtToken")
        return AuthenticationResponse(jwtToken)
    }
}
