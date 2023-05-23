package com.example.filtrador_buscador_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filtrador_buscador_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptador: AdaptadorUsuario

    var listaUsuarios = arrayListOf<Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        llenarLista()
        setupRecyclerView()

        binding.etBuscador.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {
                filtrar(p0.toString())
            }

        })
    }

    fun llenarLista() {
        listaUsuarios.add(Usuario("Ana", "12345", "ana@mail.com"))
        listaUsuarios.add(Usuario("Luis", "56789", "luis@mail.com"))
        listaUsuarios.add(Usuario("Sergio", "98765", "sergio@mail.com"))
        listaUsuarios.add(Usuario("Cesar", "54321", "cesar@mail.com"))
        listaUsuarios.add(Usuario("Laura", "82123", "laura@mail.com"))
    }

    fun setupRecyclerView() {
        binding.rvLista.layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorUsuario(listaUsuarios)
        binding.rvLista.adapter = adaptador
    }

    fun filtrar(texto: String) {
        var listaFiltrada = arrayListOf<Usuario>()

        listaUsuarios.forEach {
            if (it.nombre.toLowerCase().contains(texto.toLowerCase())) {
                listaFiltrada.add(it)
            }
        }

        adaptador.filtrar(listaFiltrada)
    }
}