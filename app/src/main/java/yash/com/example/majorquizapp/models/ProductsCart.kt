package yash.com.example.majorquizapp.models

data class ProductsCart(
    val title_cart: String,
    val photoURL_cart: Int,
    val price_cart: String,
    val description_cart: String,
    var isHidden: Boolean = false
)