package com.example.calculadora


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //Declaramos variables que usaremos(Solo manejamos dos numeros por cada calculo no mas)
    private var num1 = 0.0
    private var num2 = 0.0
    //Esto me servira para saber la operacion que estoy realizando
    private var operacion = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Le asiganmos al texto valor 0)
        resultadoText.text = "0"
        //Le asiganmos a la variable operacion el valor 0 para volver a hacer una operacion)
        operacion = SIN_OPERACION
        //Le asiganmos a cada boton a la hora del evento un valor )
        unoBoton.setOnClickListener { numeroPresionado("1") }
        dosBoton.setOnClickListener { numeroPresionado("2") }
        tresBoton.setOnClickListener { numeroPresionado("3") }
        cuatroBoton.setOnClickListener { numeroPresionado("4") }
        cincoBoton.setOnClickListener { numeroPresionado("5") }
        seisBoton.setOnClickListener { numeroPresionado("6") }
        sieteBoton.setOnClickListener { numeroPresionado("7") }
        ochoBoton.setOnClickListener { numeroPresionado("8") }
        nueveBoton.setOnClickListener { numeroPresionado("9") }
        ceroBoton.setOnClickListener { numeroPresionado("0") }
        puntoBoton.setOnClickListener { numeroPresionado(".") }
        //Le asiganmos al boton clear un metodo reseteo para ponerlo a 0 )
        clearBoton.setOnClickListener {resetAll()}
        sumaBoton.setOnClickListener { operacionPresionada(SUMA) }
        restaBoton.setOnClickListener { operacionPresionada(RESTA) }
        multBoton.setOnClickListener { operacionPresionada(MULTIPLICACION) }
        divBoton.setOnClickListener { operacionPresionada(DIVISION) }
        porcentajeBoton.setOnClickListener { operacionPresionada(PORCENTAJE) }
        potenciaBoton.setOnClickListener { operacionPresionada(POTENCIA) }
        //Le asiganmos al boton igual un metodo para resolverlo)
        igualBoton.setOnClickListener { resolvePressed() }


    }
    //Aqui creamos una funcion para saber que numero hemos pulsado(ya que pasa siendo string)
    private fun numeroPresionado(num: String){
        if(resultadoText.text == "0" && num != "." ) {
            resultadoText.text = "$num"
        } else {
            resultadoText.text = "${resultadoText.text}$num"
        }
        //Para distinguir
        if(operacion == SIN_OPERACION){
            num1 = resultadoText.text.toString().toDouble()
        } else {
            num2 = resultadoText.text.toString().toDouble()
        }
    }
    //Esta funcion nos indica cuando una operacion fue presionada y que operacion fue
    //Las operaciones las usamos con ayuda de constantes
    private fun operacionPresionada(operacion: Double){
        this.operacion = operacion
        //Convertimos a double lo de la pantalla
        num1 = resultadoText.text.toString().toDouble()

        resultadoText.text = "0"
    }
        //Indicamos que si es operacion x realice segun este marcada y si no devuelva 0
    private fun resolvePressed(){
        val result = when(operacion) {
            SUMA -> num1 + num2
            RESTA -> num1 - num2
            MULTIPLICACION -> num1 * num2
            DIVISION -> num1 / num2
            PORCENTAJE -> num1 / 100 * num2
            POTENCIA -> Math.pow(num1.toDouble(), num2.toDouble())
            else -> 0
        }

        num1 = result as Double
        //Solucion de error que se vea un .0 al calcular con decimales
        resultadoText.text = if("$result".endsWith(".0")||"$result".endsWith(",0")) { "$result".replace(".0", "") } else { "%.2f".format(result) }
        //Reemplazar las comas por puntos con el replace
        resultadoText.text = "$result".replace(",", ".")
    }
//Declaramos constantes y el valor es para saber mas adelante cual estamos haciendo
companion object {
    const val SUMA = 1.0
    const val RESTA = 2.0
    const val MULTIPLICACION = 3.0
    const val DIVISION = 4.0
    const val PORCENTAJE = 5.0
    const val POTENCIA = 6.0
    const val SIN_OPERACION = 0.0
    }
    //Para resetear Todos los valores a 0
private fun resetAll(){
    resultadoText.text = "0"
    num1 = 0.0
    num2 = 0.0
}
}