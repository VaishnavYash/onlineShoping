package yash.com.example.majorquizapp.models

data class Product(
    val title: String,
    val photoURL: Int,
    val price: String,
    val description: String,
    val imageProduct: Int,
    var hidden: Boolean = false
    )