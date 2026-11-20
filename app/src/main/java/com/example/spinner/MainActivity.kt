package com.example.spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var paises: MutableList<String> = mutableListOf()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var spinner: Spinner
    private lateinit var txtSeleccion: TextView
    private lateinit var btnDuplicar: Button

    init {
        paises = Datos.paises.toMutableList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinnerPaises)
        txtSeleccion = findViewById(R.id.txtSeleccion)
        btnDuplicar = findViewById(R.id.btnDuplicar)

        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paises)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                txtSeleccion.text = paises[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                txtSeleccion.text = ""
            }
        }

        btnDuplicar.setOnClickListener {
            val actual = txtSeleccion.text.toString()
            if (actual.isNotEmpty()) {
                paises.add(actual)
                adapter.notifyDataSetChanged()
            }
        }
    }
}