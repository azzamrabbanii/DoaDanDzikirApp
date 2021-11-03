package com.example.bebas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun main(){
    val karyawan = Orang()
    karyawan.bernafas()
    println(karyawan.hidung)

    val guru = Orang()
    guru.berbicara()
    println(guru.mulut)

}

class Orang(){
    var mata = "normal"
    var mulut = "berbicara"
    var hidung = "bernafas"
    var telinga = "mendengar"
    var tangan: String? = "nyentuh"
    var kaki: Int? = 2

    fun melihat() {}
    fun berbicara() {}
    fun bernafas() {}
    fun mendengar() {}
    fun memukul() {}
    fun berjalan() {}
}