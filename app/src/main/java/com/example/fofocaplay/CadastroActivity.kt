package com.example.fofocaplay

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class CadastroActivity : AppCompatActivity() {
    private lateinit var listaFofoca: ListaFofoca
    private lateinit var etInputCadastro: EditText
    private lateinit var rgButtons: RadioGroup
    private lateinit var rbMentiraCadastro: RadioButton
    private lateinit var rbVerdadeCadastro: RadioButton
    private lateinit var btSalvar: Button
    private lateinit var btCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        this.etInputCadastro = findViewById(R.id.etInputCadastro)
        this.rgButtons = findViewById(R.id.rgButtons)
        this.rbMentiraCadastro = findViewById(R.id.rbMentiraCadastro)
        this.rbVerdadeCadastro = findViewById(R.id.rbVerdadeCadastro)

        this.atualizarTelaCadastro()

        this.btSalvar = findViewById(R.id.btSalvar)
        this.btSalvar.setOnClickListener { this.addFofoca() }

        this.btCancelar = findViewById(R.id.btCancelar)
        this.btCancelar.setOnClickListener { this.backToMain() }

        this.listaFofoca = ListaFofoca()
    }

    private fun atualizarTelaCadastro() {
        this.etInputCadastro.setText("")
        this.rgButtons.clearCheck()
    }

    private fun addFofoca() {
        val status = this.rgButtons.checkedRadioButtonId == this.rbVerdadeCadastro.id
        val fofoca = Fofoca(this.etInputCadastro.text.toString(), status)

        this.listaFofoca.addFofoca(fofoca)
        this.atualizarTelaCadastro()

    }
    private fun backToMain() {
        finish()
    }
}