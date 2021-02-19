package com.example.calculadora

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val et1=findViewById<EditText>(R.id.et1)
        val et2=findViewById<EditText>(R.id.et2)
        val boton1=findViewById<Button>(R.id.boton1)
        val boton2=findViewById<Button>(R.id.boton2)
        //Dentro de un bloque try catch creamos un objeto de la clase OutputStreamWriter
        //        y le pasamos el dato que devuelve la función openFileOutput
        //        (esta función recibe el nombre del archivo de texto a crear):
        //La función lambda que se ejecuta cuando presionamos el botón 1 tiene primero que extraer
        // la fecha ingresada en el primer EditText y remplazar las barras de la fecha
        // por guiones ya que no se puede utilizar este caracter dentro de un nombre
        // de archivo en Android:
        boton1.setOnClickListener {
            val nomarchivo = et1.text.toString().replace('/','-')
            try {
                //Creamos un objeto de la clase OutputStreamWriter y al constructor de dicha clase
                // le enviamos el dato que retorna el método openFileOutput propio de la clase
                // AppCompatActivity que le pasamos como parámetro el nombre del archivo
                // de texto y el modo de apertura.
                val archivo = OutputStreamWriter(openFileOutput(nomarchivo, Activity.MODE_PRIVATE))
                //Grabamos en el archivo de texto el contenido del et1,
                // confirmamos la grabación llamando al método flush y
                // finalmente cerramos el archivo de texto creado:
                //Seguidamente si el archivo se creó correctamente procedemos a llamar
                // al método write y le pasamos el String a grabar, en este caso
                // extraemos los datos del EditText:
                archivo.write(et2.text.toString())
                //Luego de grabar con el método write llamamos al método flush
                // para que vuelque todos los datos que pueden haber quedado en el buffer
                // y procedemos al cerrado del archivo:
                archivo.flush()
                archivo.close()
            } catch (e: IOException) {
            }
            //Finalizamos el programa mostrando un mensaje que los datos fueron grabados:
            Toast.makeText(this, "Los datos fueron grabados", Toast.LENGTH_SHORT).show()
            et1.setText("")
            et2.setText("")
        }

//Cada vez que se graba un dato se genera un archivo de texto para dicha
// fecha, si ya había un archivo con el mismo nombre (es decir la misma fecha) se pisa el anterior.
//Para recuperar los datos de una determinada fecha se ejecuta la función lambda que le
// pasamos al botón 2:
        boton2.setOnClickListener {
            //Lo primero que hacemos es recuperar del EditText la fecha que ingresó el operador
            // para buscar y remplazamos las barras por guiones (recordemos que grabamos guiones en
            // la carga ya que la barra de una fecha no es un caracter válido en un
            // archivo en Android)::
            var nomarchivo = et1.text.toString().replace('/', '-')
            //Por otro lado cada vez que se inicia el programa verificamos si existe el archivo de
            // texto "notas.txt" llamando al método fileList() que nos retorna un objeto
            // de la clase List con todos los archivos de texto existentes en la aplicación,
            // y a partir de esta lista llamamos al método contains que devuelve verdadero si existe
            // el nombre de archivo que le pasamos como referencia:
            //Verificamos si existe un archivo con dicha fecha:
            if (fileList().contains(nomarchivo)) {
                //En el caso que exista el archivo procedemos a abrirlo para leer creando un objeto
                // de la clase InputStreamReader:
                //Si existe el archivo procedemos a abrirlo, leerlo y cargar el et2 con los
                    // datos del archivo:
                    try {
                    val archivo = InputStreamReader(openFileInput(nomarchivo))
                    //Creamos un objeto de la clase BufferedReader para hacer más eficiente
                    // la lectura del archivo:
                    val br = BufferedReader(archivo)
                    //Luego dentro de una estructura repetitiva procedemos a leer cada línea
                    // del archivo de texto y guardar los datos en memoria en un objeto
                    // de la clase StringBulder:
                    var linea = br.readLine()
                    val todo = StringBuilder()
                    while (linea != null) {
                        todo.append(linea + "\n")
                        linea = br.readLine()
                    }
                    br.close()
                    archivo.close()
                   //Mostramos los datos recuperados del archivo en el EditText:
                    et2.setText(todo)
                } catch (e: IOException) {
                }
            } else {
                Toast.makeText(this, "No hay datos grabados para dicha fecha", Toast.LENGTH_LONG).show()
                et2.setText("")
            }
        }
    }
}