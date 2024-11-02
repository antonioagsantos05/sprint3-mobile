// LoginResponse.kt
package com.example.myapplication.model

data class LoginResponse(
    val id_cliente: Long,
    val nm_cliente: String,
    val nmEmail: String,
    val token: String
)
