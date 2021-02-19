package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent


import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //Aqui Realizamos el evento de pulsar la imagen de la calculadora que nos lleva hasta la
        //pantalla2
        btnIr.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        //Aqui Realizamos el evento de pulsar la imagen de la calculadora que nos lleva hasta la
        //pantalla3
        boton1.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
            finish()
        }
    }
}