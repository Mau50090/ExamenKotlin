package com.mauricio.examen_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

    var gson: Gson = Gson()

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
            }else  if (idLectores == 1){
                imgv_FotoLector.setImageResource(arregloLectores[1].img)
                txt_Lector.text = arregloLectores[1].userName
            }else  if (idLectores == 2){
                imgv_FotoLector.setImageResource(arregloLectores[2].img)
                txt_Lector.text = arregloLectores[2].userName
            }
        }

    }
}