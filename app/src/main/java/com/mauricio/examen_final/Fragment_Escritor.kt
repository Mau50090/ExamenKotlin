package com.mauricio.examen_final

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment__escritor.*

class Fragment_Escritor : Fragment() {

    val arregloEscritores:ArrayList<Escritor> = arrayListOf<Escritor>()

    var escritor1 : Escritor = Escritor(0,"stephenking","12345",R.drawable.stephen)
    var escritor2 : Escritor = Escritor(1,"kafka","12345",R.drawable.kafka)
    var escritor3 : Escritor = Escritor(2, "stanlee","12345",R.drawable.stan)

    var arreglojson :String = ""
    var arregloAxuliarStephen :MutableList<LibroStephen> = mutableListOf<LibroStephen>()
    val TypeLibroStephen = object :TypeToken<MutableList<LibroStephen>>() {}.type
    var gson:Gson = Gson()
    var j: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment__escritor, container, false)
        val btnactua = v.findViewById<Button>(R.id.btn_MandarActualizar)
        val btncrear = v.findViewById<Button>(R.id.btn_MandarCrear)
        val btnmas = v.findViewById<Button>(R.id.btn_EscritorMas)
        val btnmenos = v.findViewById<Button>(R.id.btn_EscritorMenos)
        val transaccion: FragmentTransaction = requireFragmentManager().beginTransaction()

            btnmas.setOnClickListener {

            }

        btnactua.setOnClickListener {
            val Fragment_Edit = Fragment_Edit()
            transaccion.replace(R.id.mainActivity, Fragment_Edit).addToBackStack(null)
            transaccion.commit()
        }

        btncrear.setOnClickListener {
            val Fragment_Nuevo = Fragment_Nuevo()
            transaccion.replace(R.id.mainActivity, Fragment_Nuevo).addToBackStack(null)
            transaccion.commit()
        }
        return  v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var gson: Gson = Gson()

        arregloEscritores.add(escritor1)
        arregloEscritores.add(escritor2)
        arregloEscritores.add(escritor3)

        if (arguments != null){
            val idEscritor = requireArguments().getInt("numEsc")
            val escritorType = object : TypeToken<ArrayList<Escritor>>() {}.type

            if (idEscritor == 0){
                imgv_FotoEscritor.setImageResource(arregloEscritores[0].img)
                txt_Escritor.text = arregloEscritores[0].userName
            }else  if (idEscritor == 1){
                imgv_FotoEscritor.setImageResource(arregloEscritores[1].img)
                txt_Escritor.text = arregloEscritores[1].userName
            }else  if (idEscritor == 2){
                imgv_FotoEscritor.setImageResource(arregloEscritores[2].img)
                txt_Escritor.text = arregloEscritores[2].userName
            }
        }
    }


    fun saveNewStephen(json:String){
        if (json != null) {
            val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val editor = pref.edit()

            editor.putString("JSON", json).apply()
            val toast = Toast.makeText(requireContext(), "Se creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }else{
            val toast = Toast.makeText(requireContext(), "No creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }
    }

    fun loadStephen(): MutableList<LibroStephen>{
        var json:String
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())

        pref.apply {
            json = getString("JSON", arreglojson).toString()
        }

        val StephenType = object :TypeToken<MutableList<LibroStephen>>() {}.type
        var arregloStephen :MutableList<LibroStephen> = gson.fromJson<ArrayList<LibroStephen>>(json, StephenType)

        return arregloStephen
    }
}