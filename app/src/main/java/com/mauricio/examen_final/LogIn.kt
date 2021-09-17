package com.mauricio.examen_final

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class LogIn : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val arregloEscritores:ArrayList<Escritor> = arrayListOf<Escritor>()
    val arregloLectores:ArrayList<Lector> = arrayListOf<Lector>()

    var escritor1 : Escritor = Escritor(0,"stephenking","12345",R.drawable.stephen)
    var escritor2 : Escritor = Escritor(1,"kafka","12345",R.drawable.kafka)
    var escritor3 : Escritor = Escritor(2, "stanlee","12345",R.drawable.stan)

    var lector1 : Lector = Lector(0,"sheldon","12345",R.drawable.sheldon)
    var lector2 : Lector = Lector(1,"michael","12345",R.drawable.michael)
    var lector3 : Lector = Lector(2,"penny","12345",R.drawable.penny)

    var gson:Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_log_in, container, false)
        val tex1 = v.findViewById<EditText>(R.id.etxt_UserName)
        val tex2 = v.findViewById<EditText>(R.id.etxt_Password)
        val btnLogin = v.findViewById<Button>(R.id.btn_LogIn)
        val imgv = v.findViewById<ImageView>(R.id.imgv_Principal)
        val fragment_Escritor = Fragment_Escritor()
        val transaccionEsc:FragmentTransaction = requireFragmentManager().beginTransaction()
        transaccionEsc.replace(R.id.mainActivity, fragment_Escritor).addToBackStack(null)

        val fragment_Lector = Fragment_Lector()
        val transaccionLec:FragmentTransaction = requireFragmentManager().beginTransaction()
        transaccionLec.replace(R.id.mainActivity, fragment_Lector).addToBackStack(null)

        imgv.setImageResource(R.drawable.leer)

        arregloEscritores.add(escritor1)
        arregloEscritores.add(escritor2)
        arregloEscritores.add(escritor3)
        arregloLectores.add(lector1)
        arregloLectores.add(lector2)
        arregloLectores.add(lector3)

        tex1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tex2.isEnabled=true
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        tex2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnLogin.isEnabled=true
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        btnLogin.setOnClickListener {

            val bundle = Bundle()

            if (tex1.text.toString() == arregloEscritores[0].userName && tex2.text.toString() == arregloEscritores[0].password) {

                bundle.putInt("numEsc", arregloEscritores[0].id)
                fragment_Escritor.arguments = bundle
                transaccionEsc.commit()
                println("Se logueo " + arregloEscritores[0].userName)

            } else if (tex1.text.toString() == arregloEscritores[1].userName && tex2.text.toString() == arregloEscritores[1].password) {

                bundle.putInt("numEsc", arregloEscritores[1].id)
                fragment_Escritor.arguments = bundle
                transaccionEsc.commit()
                println("Se logueo " + arregloEscritores[1].userName)

            } else if (tex1.text.toString() == arregloEscritores[2].userName && tex2.text.toString() == arregloEscritores[2].password) {

                bundle.putInt("numEsc", arregloEscritores[2].id)
                fragment_Escritor.arguments = bundle
                transaccionEsc.commit()
                println("Se logueo " + arregloEscritores[2].userName)

            }else if (tex1.text.toString() == arregloLectores[0].userName && tex2.text.toString() == arregloLectores[0].password) {

                bundle.putInt("numLec", arregloLectores[0].id)
                fragment_Lector.arguments = bundle
                transaccionEsc.commit()
                println("Se logueo " + arregloLectores[0].userName)

            }else if (tex1.text.toString() == arregloLectores[1].userName && tex2.text.toString() == arregloLectores[1].password) {

                bundle.putInt("numLec", arregloLectores[1].id)
                fragment_Lector.arguments = bundle
                transaccionEsc.commit()
                println("Se logueo " + arregloLectores[1].userName)

            }else if (tex1.text.toString() == arregloLectores[2].userName && tex2.text.toString() == arregloLectores[2].password) {

                bundle.putInt("numLec", arregloLectores[2].id)
                fragment_Lector.arguments = bundle
                transaccionEsc.commit()
                println("Se logueo " + arregloLectores[2].userName)

            }
        }
            return v
        }
    }