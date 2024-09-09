package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.CadastroActivity


class LoginActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Encontre o TextView que leva à tela de cadastro
        val cadastroLink: TextView = findViewById(R.id.tv_cadastro)

        // Configure o listener de clique para o TextView
        cadastroLink.setOnClickListener {
            // Cria um Intent para iniciar a CadastroActivity
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
