package com.joelkbiju.roleManagement.repository

import com.joelkbiju.roleManagement.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Int> {

    fun findByEmail(email: String): User?
}
