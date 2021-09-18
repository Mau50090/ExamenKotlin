package com.mauricio.examen_final

import android.content.Context
import android.preference.PreferenceManager
import android.view.Gravity
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferences {
    var gson: Gson = Gson()

    fun saveNewLibroKing(json:String, contexto:Context){
        if (json != null) {
            val pref = PreferenceManager.getDefaultSharedPreferences(contexto)
            val editor = pref.edit()

            editor.putString("JSONKING", json).apply()
            val toast = Toast.makeText(contexto, "Se creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }else{
            val toast = Toast.makeText(contexto, "No creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }
    }

    fun saveNewLibroKafka(json:String, contexto:Context){
        if (json != null) {
            val pref = PreferenceManager.getDefaultSharedPreferences(contexto)
            val editor = pref.edit()

            editor.putString("JSONKAFKA", json).apply()
            val toast = Toast.makeText(contexto, "Se creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }else{
            val toast = Toast.makeText(contexto, "No creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }
    }

    fun saveNewLibroStant(json:String, contexto:Context){
        if (json != null) {
            val pref = PreferenceManager.getDefaultSharedPreferences(contexto)
            val editor = pref.edit()

            editor.putString("JSONSTANT", json).apply()
            val toast = Toast.makeText(contexto, "Se creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }else{
            val toast = Toast.makeText(contexto, "No creo nuevo Libro", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 140)
            toast.show()
        }
    }

    fun loadStephen(contexto:Context): MutableList<Libro>? {
        var json:String?
        val pref = PreferenceManager.getDefaultSharedPreferences(contexto)
        var arregloStephen :MutableList<Libro>?

        pref.apply {
            json = getString("JSONKING", null).toString()
        }

        val StephenType = object : TypeToken<MutableList<Libro>>() {}.type
        arregloStephen = gson.fromJson(json, StephenType)

        return arregloStephen
    }

    fun loadKafka(contexto:Context): MutableList<Libro>? {
        var json:String?
        val pref = PreferenceManager.getDefaultSharedPreferences(contexto)
        var arregloKafka :MutableList<Libro>?

        pref.apply {
            json = getString("JSONKAFKA", null).toString()
        }

        val KafkaType = object : TypeToken<MutableList<Libro>>() {}.type
        arregloKafka = gson.fromJson(json, KafkaType)

        return arregloKafka
    }

    fun loadStant(contexto:Context): MutableList<Libro>? {
        var json:String?
        val pref = PreferenceManager.getDefaultSharedPreferences(contexto)
        var arregloStan :MutableList<Libro>?

        pref.apply {
            json = getString("JSONSTANT", null).toString()
        }

        val StanType = object : TypeToken<MutableList<Libro>>() {}.type
        arregloStan = gson.fromJson(json, StanType)

        return arregloStan
    }
}