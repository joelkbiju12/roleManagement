package com.joelkbiju.roleManagement.auth

data class RegisterRequest(
    private val firstName: String,
    private val lastName: String,
    private val email: String,
    private val password: String,
)
