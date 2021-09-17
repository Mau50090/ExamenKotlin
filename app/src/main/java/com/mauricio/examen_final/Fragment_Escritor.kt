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

    var arregloAxuliarStephen :MutableList<LibroStephen>? = mutableListOf<LibroStephen>()
    var arregloAxuliarKafka :MutableList<LibroKafka>? = mutableListOf<LibroKafka>()
    var arregloAxuliarStan :MutableList<LibroStan>? = mutableListOf<LibroStan>()

    val TypeLibroStephen = object :TypeToken<MutableList<LibroStephen>>() {}.type

    var gson:Gson = Gson()
    var idescritor:Int = 0
    var j: Int = 0
    var idEscritor:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment__escritor, container, false)
        val btnactua = v.findViewById<Button>(R.id.btn_MandarActualizar)
        val btncrear = v.findViewById<Button>(R.id.btn_MandarCrear)
        val transaccion: FragmentTransaction = requireFragmentManager().beginTransaction()


        btnactua.setOnClickListener {
            val Fragment_Edit = Fragment_Edit()
            transaccion.replace(R.id.mainActivity, Fragment_Edit).addToBackStack(null)
            transaccion.commit()
        }

        btncrear.setOnClickListener {
            val Fragment_Nuevo = Fragment_Nuevo()
            val bundle = Bundle()
            bundle.putInt("idEscritorActual", idescritor)
            Fragment_Nuevo.arguments = bundle

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
            idEscritor = requireArguments().getInt("numEsc")
            this.idescritor = idEscritor
            val json1 = requireArguments().getString("LibroStephen")
            val json2 = requireArguments().getString("LibroKafka")
            val json3 = requireArguments().getString("LibroStan")
            val idEscritorNuevo = requireArguments().getInt("LibroNuevo")
            val escritorType = object : TypeToken<ArrayList<Escritor>>() {}.type

                if(json1!=null || json2!=null || json3!=null){
                    if (idEscritorNuevo == 0) {
                        idescritor = idEscritorNuevo
                        var libroStephen: LibroStephen = gson.fromJson(json1, LibroStephen::class.java)

                        if (loadStephen().isNullOrEmpty()){
                            arregloAxuliarStephen?.add(libroStephen)
                            val jsonArreglo = gson.toJson(arregloAxuliarStephen)
                            saveNewLibroKing(jsonArreglo)
                        }else{
                            arregloAxuliarStephen = loadStephen()
                            arregloAxuliarStephen?.add(libroStephen)
                            val jsonArreglo = gson.toJson(arregloAxuliarStephen)
                            saveNewLibroKing(jsonArreglo)
                        }
                    }else if(idEscritorNuevo == 1){
                        idescritor = idEscritorNuevo
                        var libroKafka :LibroKafka = gson.fromJson(json2, LibroKafka::class.java)

                        if (loadKafka().isNullOrEmpty()){
                            arregloAxuliarKafka?.add(libroKafka)
                            val jsonArreglo = gson.toJson(arregloAxuliarKafka)
                            saveNewLibroKafka(jsonArreglo)
                        }else{
                            arregloAxuliarKafka = loadKafka()
                            arregloAxuliarKafka?.add(libroKafka)
                            val jsonArreglo = gson.toJson(arregloAxuliarKafka)
                            saveNewLibroKafka(jsonArreglo)
                        }
                    }else if(idEscritorNuevo == 2) {
                        idescritor = idEscritorNuevo
                        var libroStan: LibroStan = gson.fromJson(json3, LibroStan::class.java)

                        if (loadStant().isNullOrEmpty()) {
                            arregloAxuliarStan?.add(libroStan)
                            val jsonArreglo = gson.toJson(arregloAxuliarStan)
                            saveNewLibroStant(jsonArreglo)
                        } else {
                            arregloAxuliarStan = loadStant()
                            arregloAxuliarStan?.add(libroStan)
                            val jsonArreglo = gson.toJson(arregloAxuliarStan)
                            saveNewLibroStant(jsonArreglo)
                        }
                    }
                }
            //Se debe de cambiar idEscritorio por el que regrese el otro fragment
            if (idescritor == 0){
                imgv_FotoEscritor.setImageResource(arregloEscritores[0].img)
                txt_Escritor.text = arregloEscritores[0].userName

                    if(loadStephen() != null) {
                        btn_EscritorMas.setOnClickListener {
                            j++
                            if(j  > loadStephen()!!.lastIndex){j=0}
                            if (loadStephen()!!.lastIndex >= j){
                                imgv_PortadaEscritor.setImageResource(loadStephen()!![j].portada)
                                txtv_TituloEscritor.text = loadStephen()!![j].titulo
                            }
                        }

                        btn_EscritorMenos.setOnClickListener {
                            j--
                            if (j < 0){j = loadStephen()!!.lastIndex}
                            if (loadStephen()!!.lastIndex >= j){
                                imgv_PortadaEscritor.setImageResource(loadStephen()!![j].portada)
                                txtv_TituloEscritor.text = loadStephen()!![j].titulo
                            }
                        }
                    }

            }else  if (idescritor == 1){
                imgv_FotoEscritor.setImageResource(arregloEscritores[1].img)
                txt_Escritor.text = arregloEscritores[1].userName

                if(loadKafka() != null) {
                    btn_EscritorMas.setOnClickListener {
                        j++
                        if(j  > loadKafka()!!.lastIndex){j=0}
                        if (loadKafka()!!.lastIndex >= j){
                            imgv_PortadaEscritor.setImageResource(loadKafka()!![j].portada)
                            txtv_TituloEscritor.text = loadKafka()!![j].titulo
                        }
                    }

                    btn_EscritorMenos.setOnClickListener {
                        j--
                        if (j < 0){j = loadKafka()!!.lastIndex}
                        if (loadKafka()!!.lastIndex >= j){
                            imgv_PortadaEscritor.setImageResource(loadKafka()!![j].portada)
                            txtv_TituloEscritor.text = loadKafka()!![j].titulo
                        }
                    }
                }

            }else  if (idescritor == 2){
                imgv_FotoEscritor.setImageResource(arregloEscritores[2].img)
                txt_Escritor.text = arregloEscritores[2].userName

                if(loadStant() != null) {
                    btn_EscritorMas.setOnClickListener {
                        j++
                        if(j  > loadStant()!!.lastIndex){j=0}
                        if (loadStant()!!.lastIndex >= j){
                            imgv_PortadaEscritor.setImageResource(loadStant()!![j].portada)
                            txtv_TituloEscritor.text = loadStant()!![j].titulo
                        }
                    }

                    btn_EscritorMenos.setOnClickListener {
                        j--
                        if (j < 0){j = loadStant()!!.lastIndex}
                        if (loadStant()!!.lastIndex >= j){
                            imgv_PortadaEscritor.setImageResource(loadStant()!![j].portada)
                            txtv_TituloEscritor.text = loadStant()!![j].titulo
                        }
                    }
                }
            }
        }
    }


    fun saveNewLibroKing(json:String){
        if (json != null) {
            val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val editor = pref.edit()

            editor.putString("JSONKING", json).apply()
            val toast = Toast.makeText(requireContext(), "Se creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }else{
            val toast = Toast.makeText(requireContext(), "No creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }
    }

    fun saveNewLibroKafka(json:String){
        if (json != null) {
            val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val editor = pref.edit()

            editor.putString("JSONKAFKA", json).apply()
            val toast = Toast.makeText(requireContext(), "Se creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }else{
            val toast = Toast.makeText(requireContext(), "No creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }
    }

    fun saveNewLibroStant(json:String){
        if (json != null) {
            val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val editor = pref.edit()

            editor.putString("JSONSTANT", json).apply()
            val toast = Toast.makeText(requireContext(), "Se creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }else{
            val toast = Toast.makeText(requireContext(), "No creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }
    }

    fun loadStephen(): MutableList<LibroStephen>? {
        var json:String?
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var arregloStephen :MutableList<LibroStephen>?

        pref.apply {
            json = getString("JSONKING", null).toString()
        }

        val StephenType = object :TypeToken<MutableList<LibroStephen>>() {}.type
        arregloStephen = gson.fromJson(json, StephenType)

        return arregloStephen
    }

    fun loadKafka(): MutableList<LibroKafka>? {
        var json:String?
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var arregloKafka :MutableList<LibroKafka>?

        pref.apply {
            json = getString("JSONKAFKA", null).toString()
        }

        val KafkaType = object :TypeToken<MutableList<LibroKafka>>() {}.type
        arregloKafka = gson.fromJson(json, KafkaType)

        return arregloKafka
    }

    fun loadStant(): MutableList<LibroStan>? {
        var json:String?
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var arregloStan :MutableList<LibroStan>?

        pref.apply {
            json = getString("JSONSTANT", null).toString()
        }

        val StanType = object :TypeToken<MutableList<LibroStan>>() {}.type
        arregloStan = gson.fromJson(json, StanType)

        return arregloStan
    }
}

