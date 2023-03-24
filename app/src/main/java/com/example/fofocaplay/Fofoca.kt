package com.example.fofocaplay

class Fofoca(private val fofoca: String, private val status: Boolean) {
    fun getStatus(): Boolean {
        return this.status
    }
    fun getFofoca(): String {
        return this.fofoca
    }
}