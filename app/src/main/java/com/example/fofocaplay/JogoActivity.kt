package com.example.fofocaplay

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class JogoActivity: AppCompatActivity() {
    private lateinit var pbCronometro: ProgressBar
    private lateinit var tvJogarTitle: TextView
    private lateinit var tvFofoca: TextView
    private lateinit var rbVerdadeJogar: RadioButton
    private lateinit var rbMentiraJogar: RadioButton
    private lateinit var btResponder: Button
    private lateinit var fofoca: Fofoca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogar)

        this.fofoca = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("FOFOCA", Fofoca::class.java)
        } else {
            intent.getSerializableExtra("FOFOCA")
        } as Fofoca

        this.pbCronometro = findViewById(R.id.pbCronometro)
        this.tvJogarTitle = findViewById(R.id.tvJogarTitle)
        this.tvFofoca = findViewById(R.id.tvFofoca)
        this.rbVerdadeJogar = findViewById(R.id.rbVerdadeJogar)
        this.rbMentiraJogar = findViewById(R.id.rbMentiraJogar)
        this.btResponder = findViewById(R.id.btResponder)

        this.btResponder.setOnClickListener { responder() }
        this.tvFofoca.text = fofoca.fofoca

        iniciaTempo()
    }

    fun responder() {
        if  ((this.fofoca.status) && (this.rbVerdadeJogar.isChecked)) {
            setResult(RESULT_OK)
        } else if ((!this.fofoca.status) && (this.rbMentiraJogar.isChecked)) {
            setResult(RESULT_OK)
        }
        finish()
    }

    fun iniciaTempo() {
        Thread {
            while (this.pbCronometro.progress < 100) {
                this.pbCronometro.progress += 1
                Thread.sleep(100)
            }
            finish()
        }.start()
    }
}