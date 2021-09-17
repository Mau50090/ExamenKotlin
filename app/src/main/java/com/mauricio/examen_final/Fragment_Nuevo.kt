package com.mauricio.examen_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment__nuevo.*


class Fragment_Nuevo : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var i =0
    val arregloPortadas: ArrayList<Int> = ArrayList()
    var idDelEscritor:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment__nuevo, container, false)
        val btnmas = v.findViewById<Button>(R.id.btn_MasNuevo)
        val btn_menos = v.findViewById<Button>(R.id.btn_MenosNuevo)
        val txtTitulo = v.findViewById<EditText>(R.id.etxt_TituloNuevo)
        val txtDescripcion = v.findViewById<EditText>(R.id.etxt_DescripcionNueva)

        val btn_nuevo = v.findViewById<Button>(R.id.btn_CrearNuevo)

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

        btn_nuevo.setOnClickListener {

            var gson: Gson = Gson()
            val bundle = Bundle()
            val Fragment_Escritor = Fragment_Escritor()
            val transaccion : FragmentTransaction = requireFragmentManager().beginTransaction()
            bundle.putInt("LibroNuevo", idDelEscritor)

            if (idDelEscritor == 0){
                val libroNuevo :LibroStephen = LibroStephen(txtDescripcion.text.toString(), arregloPortadas[i], txtTitulo.text.toString())
                val jsonStephen:String = gson.toJson(libroNuevo)
                bundle.putString("LibroStephen", jsonStephen)
            }else if (idDelEscritor == 1){
                val libroNuevo :LibroKafka = LibroKafka(txtDescripcion.text.toString(), arregloPortadas[i], txtTitulo.text.toString())
                val jsonKafka:String = gson.toJson(libroNuevo)
                bundle.putString("LibroKafka", jsonKafka)
            }else if(idDelEscritor == 2){
                val libroNuevo :LibroStan = LibroStan(txtDescripcion.text.toString(), arregloPortadas[i], txtTitulo.text.toString())
                val jsonStan:String = gson.toJson(libroNuevo)
                bundle.putString("LibroStan", jsonStan)
            }
            Fragment_Escritor.arguments = bundle
            transaccion.replace(R.id.mainActivity,Fragment_Escritor).addToBackStack(null)
            transaccion.commit()
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null){

            idDelEscritor = requireArguments().getInt("idEscritorActual")
            println("Este es el id que se recive en nuevo" + idDelEscritor)
        }
    }
}