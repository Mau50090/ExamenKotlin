package com.mauricio.examen_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment__more_info.*

class Fragment_MoreInfo : Fragment() {

    var gson: Gson = Gson()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__more_info, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null){
            val jsonMoreInfo = requireArguments().getString("ObjMoreInfo")
            var libroMoreInfo:Libro = gson.fromJson(jsonMoreInfo, Libro::class.java)

            txtv_TituloMoreInfo.text = libroMoreInfo.titulo
            imgv_PortadaMoreInfo.setImageResource(libroMoreInfo.portada)
            txtv_DescripcionMoreInfo.text = libroMoreInfo.descripcion
        }

        btn_RegresarMoreInfo.setOnClickListener {
            val Fragment_LogIn = LogIn()
            val transaccion: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaccion.replace(R.id.mainActivity, Fragment_LogIn).addToBackStack(null)
            transaccion.commit()
        }
    }
}