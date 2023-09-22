package yash.com.example.majorquizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import yash.com.example.majorquizapp.ProductsAdapter
import yash.com.example.majorquizapp.R
import yash.com.example.majorquizapp.models.Product
import yash.com.example.majorquizapp.models.ProductsCart

class HomeFragment : Fragment(), ProductsAdapter.OnItemClickListener {
    private var recyclerView: RecyclerView? = null
    val products = arrayListOf<Product>()
    val productsCart = arrayListOf<ProductsCart>()

    override fun onItemClick(position: Int) {
        val item = products[position - productsCart.size]
        if (productsCart.isNotEmpty()) {
            val cartItem = productsCart[position]
            item.hidden = !item.hidden
            cartItem.isHidden = item.hidden
        } else {
            item.hidden = !item.hidden
        }
        recyclerView?.adapter?.notifyItemChanged(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view :View? = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view!!.findViewById(R.id.recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        products.add(Product("Levi's",  R.drawable.shopping_first,  "Rs. 999", "Black Mens TShirt",R.drawable.shopping_cart_red, false))
        products.add(Product("GUCCI",  R.drawable.shopping_third,  "Rs. 999999", "Gucci Original UniSex TShirt",R.drawable.shopping_cart_red,false))
        products.add(Product("Alen Solly",  R.drawable.shopping_second,  "Rs. 2499", "Women's TShirt",R.drawable.shopping_cart_red,false))
        products.add(Product("Nike",  R.drawable.shopping_forth,  "Rs. 9999", "Unisex Shoes",R.drawable.shopping_cart_red,false))

        recyclerView?.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerView?.adapter = ProductsAdapter(products, productsCart,this)

    }


}