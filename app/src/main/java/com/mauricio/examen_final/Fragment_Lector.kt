package com.mauricio.examen_final

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment__escritor.*
import kotlinx.android.synthetic.main.fragment__lector.*


class Fragment_Lector : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val arregloLectores:ArrayList<Lector> = arrayListOf<Lector>()

    var lector1 : Lector = Lector(0,"sheldon","12345",R.drawable.sheldon)
    var lector2 : Lector = Lector(1,"michael","12345",R.drawable.michael)
    var lector3 : Lector = Lector(2,"penny","12345",R.drawable.penny)
    var memoria : SharedPreferences = SharedPreferences()
    var gson: Gson = Gson()
    var j:Int = 0
    var fav:Int = 0
    var fav1:Int = 0
    var fav2:Int = 0
    var fav3:Int = 0
    var jsonArreglo = String()

    var arregloTodosLibros: MutableList<Libro>? = mutableListOf<Libro>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment__lector, container, false)

        val imgFav = v.findViewById<ImageView>(R.id.imgv_Fav)
        imgFav.setImageResource(R.drawable.corazonnegro)

        return  v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arregloLectores.add(lector1)
        arregloLectores.add(lector2)
        arregloLectores.add(lector3)

        if (arguments != null){

            val idLectores = requireArguments().getInt("numLec")

            if (idLectores == 0){
                imgv_FotoLector.setImageResource(arregloLectores[0].img)
                txt_Lector.text = arregloLectores[0].userName

                if (loadFavSheldon().isNullOrEmpty()){
                    if (memoria.loadKafka(requireContext()).isNullOrEmpty() || memoria.loadStant(requireContext()).isNullOrEmpty()||memoria.loadStephen(requireContext()).isNullOrEmpty()){
                        val toast = Toast.makeText(requireContext(), "Que tal si tratas creando libros primero", Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.TOP, 0, 140)
                        toast.show()
                        val fragment_LogIn = LogIn()
                        val transaccionLog: FragmentTransaction = requireFragmentManager().beginTransaction()
                        transaccionLog.replace(R.id.mainActivity, fragment_LogIn)
                        transaccionLog.commit()
                    }else {
                        arregloTodosLibros?.addAll(memoria.loadStephen(requireContext())!!)
                        arregloTodosLibros?.addAll(memoria.loadKafka(requireContext())!!)
                        arregloTodosLibros?.addAll(memoria.loadStant(requireContext())!!)
                    }
                    if (loadFavSheldon()==null) {
                        txtv_NumFav.text = fav1.toString()
                    }
                }else{
                    arregloTodosLibros = loadFavSheldon()
                    fav1 = loadNumSheldon()!!
                    txtv_NumFav.text= loadNumSheldon().toString()
                    println("Sale este porque ya habia algo guardado" + arregloTodosLibros)
                }
            }else  if (idLectores == 1){
                imgv_FotoLector.setImageResource(arregloLectores[1].img)
                txt_Lector.text  = arregloLectores[1].userName

                if (loadFavMichael().isNullOrEmpty()){
                    if (memoria.loadKafka(requireContext()).isNullOrEmpty() || memoria.loadStant(requireContext()).isNullOrEmpty()||memoria.loadStephen(requireContext()).isNullOrEmpty()){
                        val toast = Toast.makeText(requireContext(), "Que tal si tratas creando libros primero", Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.TOP, 0, 140)
                        toast.show()
                        val fragment_LogIn = LogIn()
                        val transaccionLog: FragmentTransaction = requireFragmentManager().beginTransaction()
                        transaccionLog.replace(R.id.mainActivity, fragment_LogIn)
                        transaccionLog.commit()
                    }else {
                        arregloTodosLibros?.addAll(memoria.loadStephen(requireContext())!!)
                        arregloTodosLibros?.addAll(memoria.loadKafka(requireContext())!!)
                        arregloTodosLibros?.addAll(memoria.loadStant(requireContext())!!)
                        txtv_NumFav.text = fav2.toString()
                        jsonArreglo = gson.toJson(arregloTodosLibros)

                        saveFavMichael(jsonArreglo, fav2)
                    }
                }else{
                    arregloTodosLibros = loadFavMichael()
                    fav2 = loadNumMichael()!!
                    txtv_NumFav.text = loadNumMichael().toString()
                    println("Sale este porque ya habia algo guardado" + arregloTodosLibros)
                }
            }else  if (idLectores == 2){
                imgv_FotoLector.setImageResource(arregloLectores[2].img)
                txt_Lector.text = arregloLectores[2].userName

                if (loadFavPenny().isNullOrEmpty()){
                    if (memoria.loadKafka(requireContext()).isNullOrEmpty() || memoria.loadStant(requireContext()).isNullOrEmpty()||memoria.loadStephen(requireContext()).isNullOrEmpty()){
                        val toast = Toast.makeText(requireContext(), "Que tal si tratas creando libros primero", Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.TOP, 0, 140)
                        toast.show()
                        val fragment_LogIn = LogIn()
                        val transaccionLog: FragmentTransaction = requireFragmentManager().beginTransaction()
                        transaccionLog.replace(R.id.mainActivity, fragment_LogIn)
                        transaccionLog.commit()
                    }else {
                        arregloTodosLibros?.addAll(memoria.loadStephen(requireContext())!!)
                        arregloTodosLibros?.addAll(memoria.loadKafka(requireContext())!!)
                        arregloTodosLibros?.addAll(memoria.loadStant(requireContext())!!)
                        txtv_NumFav.text = fav3.toString()

                        jsonArreglo = gson.toJson(arregloTodosLibros)
                        saveFavPenny(jsonArreglo, fav3)
                    }
                }else{
                    arregloTodosLibros = loadFavPenny()
                    fav3 = loadNumPenny()!!
                    txtv_NumFav.text = loadNumPenny().toString()
                    println("Sale este porque ya habia algo guardado" + arregloTodosLibros)
                }
            }

            btn_MasLector.setOnClickListener {
                j++
                if(j  > arregloTodosLibros!!.lastIndex){j=0}
                if (arregloTodosLibros!!.lastIndex >= j){
                    imgv_PortadaenLector.setImageResource(arregloTodosLibros!![j].portada)
                    txtv_TituloLectores.text = arregloTodosLibros!![j].titulo
                    if (arregloTodosLibros!![j].fav == false){
                        imgv_Fav.setImageResource(R.drawable.corazonnegro)
                    }else if (arregloTodosLibros!![j].fav == true){
                        imgv_Fav.setImageResource(R.drawable.rojo)
                    }
                }
            }

            btn_MenosLector.setOnClickListener {
                j--
                if (j < 0){j = arregloTodosLibros!!.lastIndex}
                if (arregloTodosLibros!!.lastIndex >= j){
                    imgv_PortadaenLector.setImageResource(arregloTodosLibros!![j].portada)
                    txtv_TituloLectores.text = arregloTodosLibros!![j].titulo
                    if (arregloTodosLibros!![j].fav == false){
                        imgv_Fav.setImageResource(R.drawable.corazonnegro)
                    }else if (arregloTodosLibros!![j].fav == true){
                        imgv_Fav.setImageResource(R.drawable.rojo)
                    }
                }
            }

            imgv_Fav.setOnClickListener {
                if (arregloTodosLibros!![j].fav == false){
                    arregloTodosLibros!![j].fav = true
                }else if (arregloTodosLibros!![j].fav == true){
                    arregloTodosLibros!![j].fav = false
                }

                if (arregloTodosLibros!![j].fav == false){
                    imgv_Fav.setImageResource(R.drawable.corazonnegro)
                    if(idLectores == 0){
                        fav1--
                        txtv_NumFav.text = fav1.toString()
                        saveFavSheldon(jsonArreglo, fav1)
                    }else if(idLectores == 1){
                        fav2--
                        txtv_NumFav.text = fav2.toString()
                        saveFavMichael(jsonArreglo, fav2)
                    }else if(idLectores == 2){
                        fav3--
                        txtv_NumFav.text = fav3.toString()
                        saveFavPenny(jsonArreglo, fav3)
                    }
                }else if (arregloTodosLibros!![j].fav == true){
                    imgv_Fav.setImageResource(R.drawable.rojo)
                    if(idLectores == 0){
                        fav1++
                        txtv_NumFav.text = fav1.toString()
                        saveFavSheldon(jsonArreglo, fav1)
                    }else if(idLectores == 1){
                        fav2++
                        txtv_NumFav.text = fav2.toString()
                        saveFavMichael(jsonArreglo, fav2)
                    }else if(idLectores == 2){
                        fav3++
                        txtv_NumFav.text = fav3.toString()
                        saveFavPenny(jsonArreglo, fav3)
                    }
                }

                if (idLectores == 0){
                    jsonArreglo = gson.toJson(arregloTodosLibros)
                    saveFavSheldon(jsonArreglo, fav1)
                }else if (idLectores == 1){
                    jsonArreglo = gson.toJson(arregloTodosLibros)
                    saveFavMichael(jsonArreglo, fav2)
                }else if(idLectores == 2){
                    jsonArreglo = gson.toJson(arregloTodosLibros)
                    saveFavPenny(jsonArreglo, fav3)
                }

            }
        }
    }
    fun saveFavSheldon(json:String, fav:Int){
            val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val editor = pref.edit()

            editor.putString("FAVSHELDON", json).apply()
            editor.putInt("INTSHELDON", fav).apply()
            val toast = Toast.makeText(requireContext(), "Se agrego a favoritos", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        println("Se guardo el numero de favoritos " + fav)
        println("Se guarda el json " + json)
    }

    fun loadFavSheldon(): MutableList<Libro>? {
        var json:String?
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var arregloLibros: MutableList<Libro>?

        pref.apply {
            json = getString("FAVSHELDON", null).toString()
        }

        val LibroType = object : TypeToken<MutableList<Libro>>() {}.type
        arregloLibros = gson.fromJson(json, LibroType)
        println("Esto es lo que saca el load " + json)
        return  arregloLibros
    }

    fun loadNumSheldon():Int?{
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var numFav:Int?
        pref.apply {
            numFav = getInt("INTSHELDON", fav)
        }
        println("Este es el numero de favoritos que sale del load " + numFav)
        return numFav
    }

    fun saveFavMichael(json:String, fav:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = pref.edit()

        editor.putString("FAVMICHAEL", json).apply()
        editor.putInt("INTMICHAEL", fav).apply()
        val toast = Toast.makeText(requireContext(), "Se agrego a favoritos", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP, 0, 140)
        toast.show()
        println("Se guarda el json " + json)
    }

    fun loadFavMichael(): MutableList<Libro>? {
        var json:String?
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var arregloLibros: MutableList<Libro>?

        pref.apply {
            json = getString("FAVMICHAEL", null).toString()
        }

        val LibroType = object : TypeToken<MutableList<Libro>>() {}.type
        arregloLibros = gson.fromJson(json, LibroType)
        println("Esto es lo que saca el load " + json)
        return  arregloLibros
    }

    fun loadNumMichael():Int?{
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var numFav:Int?
        pref.apply {
            numFav = getInt("INTMICHAEL", fav)
        }
        return numFav
    }

    fun saveFavPenny(json:String, fav:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = pref.edit()

        editor.putString("FAVPENNY", json).apply()
        editor.putInt("INTPENNY", fav).apply()
        val toast = Toast.makeText(requireContext(), "Se agrego a favoritos", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP, 0, 140)
        toast.show()
        println("Se guarda el json " + json)
    }

    fun loadFavPenny(): MutableList<Libro>? {
        var json:String?
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var arregloLibros: MutableList<Libro>?

        pref.apply {
            json = getString("FAVPENNY", null).toString()
        }

        val LibroType = object : TypeToken<MutableList<Libro>>() {}.type
        arregloLibros = gson.fromJson(json, LibroType)
        println("Esto es lo que saca el load " + json)
        return  arregloLibros
    }

    fun loadNumPenny():Int?{
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var numFav:Int?
        pref.apply {
            numFav = getInt("INTPENNY", fav)
        }
        return numFav
    }
}