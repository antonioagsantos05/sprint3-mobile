package com.example.myapplication.network

import com.example.myapplication.model.CadastroRequest
import com.example.myapplication.model.CadastroResponse
import com.example.myapplication.model.LoginRequest
import com.example.myapplication.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ClienteService {

    // Endpoint para cadastro de clientes (POST /clientes) - Recebe uma lista de CadastroRequest
    @POST("clientes")
    fun cadastro(@Body cadastroRequest: List<CadastroRequest>): Call<List<CadastroResponse>>

    // Endpoint para "login" de clientes, usando GET com parâmetros para simular autenticação (GET /clientes?email=email&senha=senha)
    @GET("clientes")
    fun login(
        @Query("email") email: String,
        @Query("senha") senha: String
    ): Call<LoginResponse>
}
