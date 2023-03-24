package com.example.fofocaplay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btJogar: Button
    private lateinit var btCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btJogar = findViewById(R.id.btJogar)
        this.btJogar.setOnClickListener { goToJogar() }

        this.btCadastrar = findViewById(R.id.btCadastrar)
        this.btCadastrar.setOnClickListener { goToCadastrar() }
    }

    fun goToJogar() {
        val intent = Intent(this, JogarActivity::class.java)
        startActivity(intent)
    }


    fun goToCadastrar() {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }
}