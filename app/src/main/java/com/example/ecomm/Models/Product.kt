package com.example.ecomm.Models

data class Product(
    val id : String,
    val productName : String,
    val price : String,
    val description : String
){
    constructor() : this("","","","")
}
