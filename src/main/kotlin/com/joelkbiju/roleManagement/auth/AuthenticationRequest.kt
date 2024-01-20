package com.joelkbiju.roleManagement.auth

data class AuthenticationRequest(
    val email: String,
    val password: String,
)
