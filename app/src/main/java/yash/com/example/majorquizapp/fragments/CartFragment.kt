package yash.com.example.majorquizapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_cart.*
import yash.com.example.majorquizapp.ProductsAdapter
import yash.com.example.majorquizapp.R
import yash.com.example.majorquizapp.models.Product
import yash.com.example.majorquizapp.models.ProductsCart

class CartFragment : Fragment(), ProductsAdapter.OnItemClickListener {
    private var recyclerViewCart: RecyclerView? = null
    val products = arrayListOf<Product>()
    val productsCart = arrayListOf<ProductsCart>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view :View? = inflater.inflate(R.layout.fragment_cart, container, false)
        recyclerViewCart = view!!.findViewById(R.id.recycler_view_cart)

        placeOrderBtn?.setOnClickListener {
            deleteAllItems()
            Toast.makeText(requireContext(), "Order Placed Successfully", Toast.LENGTH_LONG).show()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsCart.add(ProductsCart("Levi's",  R.drawable.shopping_first,  "Rs. 999", "Black Mens TShirt"))
        productsCart.add(ProductsCart("GUCCI",  R.drawable.shopping_third,  "Rs. 999999", "Gucci Original UniSex TShirt"))
        productsCart.add(ProductsCart("Alen Solly",  R.drawable.shopping_second,  "Rs. 2499", "Women's TShirt"))
        productsCart.add(ProductsCart("Nike",  R.drawable.shopping_forth,  "Rs. 9999", "Unisex Shoes"))

        recyclerViewCart?.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerViewCart?.adapter = ProductsAdapter(products, productsCart, this)

    }

    override fun onItemClick(position: Int) {
        val item = productsCart[position]
        Toast.makeText(requireContext(), "Clicked on $item", Toast.LENGTH_LONG).show()
    }

    private fun deleteAllItems() {
        productsCart.clear()
        recyclerViewCart?.adapter?.notifyDataSetChanged()
    }
}