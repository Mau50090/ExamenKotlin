package com.mauricio.examen_final

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment__edit.*


class Fragment_Edit : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var gson: Gson = Gson()
    var i =0
    val arregloPortadas: ArrayList<Int> = ArrayList()
    var idDelEscritor:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =inflater.inflate(R.layout.fragment__edit, container, false)

        val btnmas = v.findViewById<Button>(R.id.button7)
        val btn_menos = v.findViewById<Button>(R.id.button8)
        val imgv_PortadasNuevo= v.findViewById<ImageView>(R.id.imgv_EditPortada)
        arregloPortadas.add(R.drawable.espanol)
        arregloPortadas.add(R.drawable.romance)
        arregloPortadas.add(R.drawable.naruto)
        arregloPortadas.add(R.drawable.spider)
        arregloPortadas.add(R.drawable.terror)

        btnmas.setOnClickListener {
            i++
            if(i>4){i=0}
            if (i == 0){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }else if (i == 1){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }else if (i == 2){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }else if (i == 3){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }else if (i == 4){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }
            println(i)
        }

        btn_menos.setOnClickListener {
            i--
            if (i < 0 ){i = 4}
            if (i == 0){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }else if (i == 1){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }else if (i == 2){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }else if (i == 3){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }else if (i == 4){
                imgv_PortadasNuevo.setImageResource(arregloPortadas[i])
            }
            println(i)
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val idactua = requireArguments().getInt("IdActualizar")
        val poscicion = requireArguments().getInt("Posicion")
        val memoria: SharedPreferences = SharedPreferences()

        if(idactua == 0){
            val jsonstephen = requireArguments().getString("ActuaStephen")
            val libroStephen: Libro = gson.fromJson(jsonstephen, Libro::class.java)
            imgv_EditPortada.setImageResource(libroStephen.portada)
            etxt_TituloEdit.setText(libroStephen.titulo)
            txtv_DescripcionEdit.setText(libroStephen.descripcion)
        }else if (idactua == 1){
            val jsonskafka = requireArguments().getString("ActuaKafka")
            val libroKafka: Libro = gson.fromJson(jsonskafka, Libro::class.java)
            println("Libro Kafka " + libroKafka)
            imgv_EditPortada.setImageResource(libroKafka.portada)
            etxt_TituloEdit.setText(libroKafka.titulo)
            txtv_DescripcionEdit.setText(libroKafka.descripcion)
        }else if (idactua == 2){
            val jsonstan = requireArguments().getString("ActuaStan")
            val libroStan: Libro = gson.fromJson(jsonstan, Libro::class.java)
            imgv_EditPortada.setImageResource(libroStan.portada)
            etxt_TituloEdit.setText(libroStan.titulo)
            txtv_DescripcionEdit.setText(libroStan.descripcion)
        }

        button9.setOnClickListener {
            if (idactua == 0){

                    val LibroActua: Libro = Libro(
                        txtv_DescripcionEdit.text.toString(),
                        arregloPortadas[i],
                        etxt_TituloEdit.text.toString()
                    )
                    var listaactual: MutableList<Libro> = mutableListOf()
                    listaactual = memoria.loadStephen(requireContext())!!
                    listaactual.removeAt(poscicion)
                    listaactual.add(LibroActua)
                    val jsonActua = gson.toJson(listaactual)
                println("LISTA ACTUALIZADA " + listaactual)
                    println("JSON de ACTUALIZADO " + jsonActua)
                    memoria.saveNewLibroKing(jsonActua, requireContext())

            }else if(idactua == 1){

                val LibroActua:Libro = Libro(txtv_DescripcionEdit.text.toString(),arregloPortadas[i],etxt_TituloEdit.text.toString())
                var listaactual :MutableList<Libro> = mutableListOf()
                listaactual = memoria.loadKafka(requireContext())!!
                listaactual.removeAt(poscicion)
                listaactual.add(LibroActua)
                val jsonActua = gson.toJson(listaactual)
                println("JSON de ACTUALIZADO " + jsonActua)
                memoria.saveNewLibroKafka(jsonActua,requireContext())

            }else if(idactua == 2){

                val LibroActua:Libro = Libro(txtv_DescripcionEdit.text.toString(),arregloPortadas[i],etxt_TituloEdit.text.toString())
                var listaactual :MutableList<Libro> = mutableListOf()
                listaactual = memoria.loadStant(requireContext())!!
                listaactual.removeAt(poscicion)
                listaactual.add(LibroActua)
                val jsonActua = gson.toJson(listaactual)
                println("JSON de ACTUALIZADO " + jsonActua)
                memoria.saveNewLibroStant(jsonActua,requireContext())

            }
            val LogIn = LogIn()
            val transaccion : FragmentTransaction = requireFragmentManager().beginTransaction()
            transaccion.replace(R.id.mainActivity,LogIn)
            val toast = Toast.makeText(requireContext(), "Logese de nuevo para ver los cambios", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
            transaccion.commit()
        }
    }
}