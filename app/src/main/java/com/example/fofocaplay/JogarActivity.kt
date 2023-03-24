package com.example.fofocaplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.*

class JogarActivity : AppCompatActivity() {
    private lateinit var pgCronometro: ProgressBar
    private lateinit var tvJogarTitle: TextView
    private lateinit var tvFofoca: TextView
    private lateinit var rgButtonsJogar: RadioGroup
    private lateinit var rbVerdadeJogar: RadioButton
    private lateinit var rbMentiraJogar: RadioButton
    private lateinit var btResponder: Button

    private lateinit var listaFofoca: ListaFofoca
    private lateinit var timerThread: Thread
    private val TIMER_END_MESSAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogar)

        this.pgCronometro = findViewById(R.id.pbCronometro)
        this.tvJogarTitle = findViewById(R.id.tvJogarTitle)
        this.tvFofoca = findViewById(R.id.tvFofoca)
        this.rgButtonsJogar = findViewById(R.id.rgButtonsJogar)
        this.rbVerdadeJogar = findViewById(R.id.rbVerdadeJogar)
        this.rbMentiraJogar = findViewById(R.id.rbMentiraJogar)
        this.listaFofoca = ListaFofoca()

        this.btResponder = findViewById(R.id.btResponder)
        this.btResponder.setOnClickListener { this.chute() }

        this.iniciar()
    }
    private fun chute() {
        val resposta = this.rgButtonsJogar.checkedRadioButtonId == rbVerdadeJogar.id
        val gameStatus = this.listaFofoca.chute(resposta)

        Toast.makeText(this, gameStatus.toString(), Toast.LENGTH_SHORT).show()
        this.atualizarTelaJogar()
    }

    private fun atualizarFofoca() {
        this.listaFofoca.alterarFofocaAtual()
        this.atualizarTelaJogar()
    }

    private fun iniciar() {
        this.atualizarFofoca()
        this.progress()
    }

    private fun atualizarTelaJogar() {
        this.tvFofoca.text = this.listaFofoca.getFofocaAtual().getFofoca()
        this.rgButtonsJogar.clearCheck()
        this.pgCronometro.progress = 0
    }

    private fun progress() {
        val tempoMax = 5000
        this.pgCronometro.max = tempoMax

        val handler = Handler(Looper.getMainLooper()) {
            if (it.what == this.TIMER_END_MESSAGE) {
                Toast.makeText(this, ListaFofoca.GameStatus.INCORRETO.toString(), Toast.LENGTH_SHORT).show()
                finish()
            }
            true
        }

        this.timerThread = Thread{this.timer(tempoMax, handler)}
        this.timerThread.start()
    }

    private fun timer(tempoMax: Int, handler: Handler) {
        while (this.pgCronometro.progress < tempoMax) {
            this.pgCronometro.progress += 1
            Thread.sleep(1)
        }

        handler.sendMessage((Message.obtain().apply {
            what = TIMER_END_MESSAGE
        }))
    }
}
