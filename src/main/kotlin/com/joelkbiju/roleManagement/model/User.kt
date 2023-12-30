package com.joelkbiju.roleManagement.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="app_user")
class User : UserDetails {
    @Id
    @GeneratedValue
    private val id: Int? = null
    private val firstname: String? = null
    private val lastname: String? = null
    private val email: String? = null
    private val password: String? = null
    @Enumerated
    private val role: Role? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        TODO("Not yet implemented")
        return mutableListOf(SimpleGrantedAuthority(role?.name))
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }


}
