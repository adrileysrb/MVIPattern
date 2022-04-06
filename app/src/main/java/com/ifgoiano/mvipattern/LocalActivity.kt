package com.ifgoiano.mvipattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifgoiano.mvipattern.databinding.ActivityLocalBinding
import com.ifgoiano.mvipattern.local.DatabaseHandler
import com.ifgoiano.mvipattern.local.ListaAdapter
import com.ifgoiano.mvipattern.model.Data

class LocalActivity : AppCompatActivity() { // Iniciando a RecyclerView
var listaAdapter: ListaAdapter? = null
var linearLayoutManager: LinearLayoutManager? = null

// SQLite
var pessoaList = ArrayList<Data>()
var databaseHandler = DatabaseHandler(this)

private lateinit var binding: ActivityLocalBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityLocalBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
    initView()

    binding.deleteData.setOnClickListener()
}

override fun onResume() {
    super.onResume()
    initView()
}

private fun initView(){
    pessoaList = databaseHandler.pessoas()
    listaAdapter = ListaAdapter(pessoaList,this, this::deleteAdapter)
    linearLayoutManager = LinearLayoutManager(this)
    binding.listSql.layoutManager = linearLayoutManager
    binding.listSql.adapter = listaAdapter
}
private fun deleteAdapter(position: Int){
    pessoaList.removeAt(position)
    listaAdapter!!.notifyItemRemoved(position)
}
}