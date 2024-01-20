package com.joelkbiju.roleManagement.auth

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
data class AuthenticationResponse(
    private val token: String,
)
