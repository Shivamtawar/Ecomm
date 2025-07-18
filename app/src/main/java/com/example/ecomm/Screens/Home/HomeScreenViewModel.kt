package com.example.ecomm.Screens.Home

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomm.Models.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.content.Context
import android.nfc.Tag
import android.util.Log

class HomeScreenViewModel @Inject constructor() : ViewModel() {


    private val db = FirebaseDatabase.getInstance().getReference("products")

    private val _product = MutableStateFlow<List<Product>>(emptyList())
    val product : StateFlow<List<Product>> get() = _product

    init {
        fetchProducts()
    }

    fun addProduct (product : Product){
        val newDb = db.push()
        val newProduct = product.copy(id = newDb.key ?: "")
        newDb.setValue(newProduct)

    }

    private fun fetchProducts(){
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = mutableListOf<Product>()
                for(item in snapshot.children){
                    try {
                        val product = item.getValue(Product::class.java)?.copy(id = item.key ?: "")
                        if (product != null) {
                            productList.add(product)

                        }
                    }catch (e : Exception){
                       Log.e( "this ", e.message ?: "")
                    }

                    viewModelScope. launch {
                        _product.value = productList

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

}