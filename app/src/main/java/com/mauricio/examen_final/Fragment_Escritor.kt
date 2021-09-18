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
    val memoria:SharedPreferences = SharedPreferences()

    var escritor1 : Escritor = Escritor(0,"stephenking","12345",R.drawable.stephen)
    var escritor2 : Escritor = Escritor(1,"kafka","12345",R.drawable.kafka)
    var escritor3 : Escritor = Escritor(2, "stanlee","12345",R.drawable.stan)

    var arregloAxuliarStephen :MutableList<Libro>? = mutableListOf<Libro>()
    var arregloAxuliarKafka :MutableList<Libro>? = mutableListOf<Libro>()
    var arregloAxuliarStan :MutableList<Libro>? = mutableListOf<Libro>()

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
                        var libroStephen: Libro = gson.fromJson(json1, Libro::class.java)

                        if (memoria.loadStephen(requireContext()).isNullOrEmpty()){
                            arregloAxuliarStephen?.add(libroStephen)
                            val jsonArreglo = gson.toJson(arregloAxuliarStephen)
                            memoria.saveNewLibroKing(jsonArreglo, requireContext())
                        }else{
                            arregloAxuliarStephen = memoria.loadStephen(requireContext())
                            arregloAxuliarStephen?.add(libroStephen)
                            val jsonArreglo = gson.toJson(arregloAxuliarStephen)
                            memoria.saveNewLibroKing(jsonArreglo, requireContext())
                        }
                    }else if(idEscritorNuevo == 1){
                        idescritor = idEscritorNuevo
                        var libroKafka :Libro = gson.fromJson(json2, Libro::class.java)

                        if (memoria.loadKafka(requireContext()).isNullOrEmpty()){
                            arregloAxuliarKafka?.add(libroKafka)
                            val jsonArreglo = gson.toJson(arregloAxuliarKafka)
                            memoria.saveNewLibroKafka(jsonArreglo, requireContext())
                        }else{
                            arregloAxuliarKafka = memoria.loadKafka(requireContext())
                            arregloAxuliarKafka?.add(libroKafka)
                            val jsonArreglo = gson.toJson(arregloAxuliarKafka)
                            memoria.saveNewLibroKafka(jsonArreglo, requireContext())
                        }
                    }else if(idEscritorNuevo == 2) {
                        idescritor = idEscritorNuevo
                        var libroStan: Libro = gson.fromJson(json3, Libro::class.java)

                        if (memoria.loadStant(requireContext()).isNullOrEmpty()) {
                            arregloAxuliarStan?.add(libroStan)
                            val jsonArreglo = gson.toJson(arregloAxuliarStan)
                            memoria.saveNewLibroStant(jsonArreglo, requireContext())
                        } else {
                            arregloAxuliarStan = memoria.loadStant(requireContext())
                            arregloAxuliarStan?.add(libroStan)
                            val jsonArreglo = gson.toJson(arregloAxuliarStan)
                            memoria.saveNewLibroStant(jsonArreglo, requireContext())
                        }
                    }
                }
            if (idescritor == 0){
                imgv_FotoEscritor.setImageResource(arregloEscritores[0].img)
                txt_Escritor.text = arregloEscritores[0].userName

                    if(memoria.loadStephen(requireContext()) != null) {
                        btn_EscritorMas.setOnClickListener {
                            j++
                            if(j  > memoria.loadStephen(requireContext())!!.lastIndex){j=0}
                            if (memoria.loadStephen(requireContext())!!.lastIndex >= j){
                                imgv_PortadaEscritor.setImageResource(memoria.loadStephen(requireContext())!![j].portada)
                                txtv_TituloEscritor.text = memoria.loadStephen(requireContext())!![j].titulo
                            }
                        }

                        btn_EscritorMenos.setOnClickListener {
                            j--
                            if (j < 0){j = memoria.loadStephen(requireContext())!!.lastIndex}
                            if (memoria.loadStephen(requireContext())!!.lastIndex >= j){
                                imgv_PortadaEscritor.setImageResource(memoria.loadStephen(requireContext())!![j].portada)
                                txtv_TituloEscritor.text = memoria.loadStephen(requireContext())!![j].titulo
                            }
                        }
                    }

            }else  if (idescritor == 1){
                imgv_FotoEscritor.setImageResource(arregloEscritores[1].img)
                txt_Escritor.text = arregloEscritores[1].userName

                if(memoria.loadKafka(requireContext()) != null) {

                    btn_EscritorMas.setOnClickListener {
                        j++
                        if(j  > memoria.loadKafka(requireContext())!!.lastIndex){j=0}
                        if (memoria.loadKafka(requireContext())!!.lastIndex >= j){
                            imgv_PortadaEscritor.setImageResource(memoria.loadKafka(requireContext())!![j].portada)
                            txtv_TituloEscritor.text = memoria.loadKafka(requireContext())!![j].titulo
                        }
                    }

                    btn_EscritorMenos.setOnClickListener {
                        j--
                        if (j < 0){j = memoria.loadKafka(requireContext())!!.lastIndex}
                        if (memoria.loadKafka(requireContext())!!.lastIndex >= j){
                            imgv_PortadaEscritor.setImageResource(memoria.loadKafka(requireContext())!![j].portada)
                            txtv_TituloEscritor.text = memoria.loadKafka(requireContext())!![j].titulo
                        }
                    }
                }

            }else  if (idescritor == 2){
                imgv_FotoEscritor.setImageResource(arregloEscritores[2].img)
                txt_Escritor.text = arregloEscritores[2].userName

                if(memoria.loadStant(requireContext()) != null) {
                    btn_EscritorMas.setOnClickListener {
                        j++
                        if(j  > memoria.loadStant(requireContext())!!.lastIndex){j=0}
                        if (memoria.loadStant(requireContext())!!.lastIndex >= j){
                            imgv_PortadaEscritor.setImageResource(memoria.loadStant(requireContext())!![j].portada)
                            txtv_TituloEscritor.text = memoria.loadStant(requireContext())!![j].titulo
                        }
                    }

                    btn_EscritorMenos.setOnClickListener {
                        j--
                        if (j < 0){j = memoria.loadStant(requireContext())!!.lastIndex}
                        if (memoria.loadStant(requireContext())!!.lastIndex >= j){
                            imgv_PortadaEscritor.setImageResource(memoria.loadStant(requireContext())!![j].portada)
                            txtv_TituloEscritor.text = memoria.loadStant(requireContext())!![j].titulo
                        }
                    }
                }
            }
            btn_MandarActualizar.setOnClickListener {

                if (idescritor == 0){
                    val jsonActualizar = gson.toJson(memoria.loadStephen(requireContext())!![j])
                    val Fragment_Edit = Fragment_Edit()
                    val bundle = Bundle()
                    bundle.putString("ActuaStephen", jsonActualizar)
                    bundle.putInt("IdActualizar", 0)
                    bundle.putInt("Posicion", j)
                    Fragment_Edit.arguments = bundle
                    val transaccion: FragmentTransaction = requireFragmentManager().beginTransaction()
                    transaccion.replace(R.id.mainActivity, Fragment_Edit).addToBackStack(null)
                    transaccion.commit()
                }else if(idescritor == 1){
                    val Fragment_Edit = Fragment_Edit()
                    val bundle = Bundle()
                    val jsonActualizar = gson.toJson(memoria.loadKafka(requireContext())!![j])
                    bundle.putString("ActuaKafka", jsonActualizar)
                    bundle.putInt("IdActualizar", 1)
                    bundle.putInt("Posicion", j)
                    Fragment_Edit.arguments = bundle
                    val transaccion: FragmentTransaction = requireFragmentManager().beginTransaction()
                    transaccion.replace(R.id.mainActivity, Fragment_Edit).addToBackStack(null)
                    transaccion.commit()
                }else if (idescritor == 2){
                    val Fragment_Edit = Fragment_Edit()
                    val bundle = Bundle()
                    val jsonActualizar = gson.toJson(memoria.loadStant(requireContext())!![j])
                    bundle.putString("ActuaStan", jsonActualizar)
                    bundle.putInt("IdActualizar", 2)
                    bundle.putInt("Posicion", j)
                    Fragment_Edit.arguments = bundle
                    val transaccion: FragmentTransaction = requireFragmentManager().beginTransaction()
                    transaccion.replace(R.id.mainActivity, Fragment_Edit).addToBackStack(null)
                    transaccion.commit()
                }
            }
        }
    }
}

