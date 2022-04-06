package com.ifgoiano.mvipattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ifgoiano.mvipattern.model.Data
import com.ifgoiano.mvipattern.remote.ProductAdapter
import com.ifgoiano.mvipattern.remote.ProductViewModel

class RemoteActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {

    private lateinit var name: EditText
    private lateinit var price: EditText
    private lateinit var description: EditText
    private lateinit var submit: Button
    private lateinit var rvList: RecyclerView

    private lateinit var productAdapter: ProductAdapter
    private lateinit var list: ArrayList<Data>

    private var selected: Data = Data()

    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote)

        rvList = findViewById(R.id.rvList)

        initElement()
        initViewModel()
    }

    private fun initElement() {

        productViewModel.getList()

    }

    private fun initViewModel() {
        productViewModel.createLiveData.observe(this, {
            onCreate(it)
        })

        productViewModel.updateLiveData.observe(this, {
            onUpdate(it)
        })

        productViewModel.deleteLiveData.observe(this, {
            onDelete(it)
        })

        productViewModel.getListLiveData.observe(this, {
            onGetList(it)
        })
    }

    private fun onCreate(it: Boolean) {
        if (it) {
            productViewModel.getList()
            resetText()
        }
    }

    private fun onUpdate(it: Boolean) {
        if (it) {
            productViewModel.getList()
            resetText()
        }
    }

    private fun onDelete(it: Boolean) {
        if (it) {
            productViewModel.getList()
            resetText()
        }
    }

    private fun onGetList(it: List<Data>) {
        list = ArrayList()
        list.addAll(it)

        productAdapter = ProductAdapter(list, this)

        rvList.adapter = productAdapter
        rvList.layoutManager = LinearLayoutManager(baseContext)

        productAdapter.notifyDataSetChanged()
    }

    private fun create() {
        val product = Data(
            selected.id,
            name.text.toString()
        )
        if (product.id != null) {
            productViewModel.update(product)
        } else {
            productViewModel.create(product)
        }
    }

    private fun resetText() {
        selected = Data()

        name.text = null
        price.text = null
        description.text = null
    }

    override fun onClick(item: Data, position: Int) {
        selected = item
        name.setText(selected.name)
    }

    override fun onDelete(item: Data, position: Int) {
        productViewModel.delete(item.id!!)
    }
}