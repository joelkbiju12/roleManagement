package com.joelkbiju.roleManagement.auth

data class AuthenticationRequest(
    private val email: String,
    private val password: String,
)
