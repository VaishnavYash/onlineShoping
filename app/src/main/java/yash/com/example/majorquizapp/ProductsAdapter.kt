package yash.com.example.majorquizapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import yash.com.example.majorquizapp.models.Product
import yash.com.example.majorquizapp.models.ProductsCart

class ProductsAdapter(
        private val products: List<Product>,
        private val products_cart: List<ProductsCart>,
        private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ONE = 1
    private val TYPE_TWO = 2

    class ViewHolderOne(itemView: View, private var listener: OnItemClickListener): RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val imageBtn: ImageView = itemView.findViewById(R.id.cartBtn)
        val description: TextView = itemView.findViewById(R.id.description)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    class ViewHolderTwo(itemView: View, private var listener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        val image_cart: ImageView = itemView.findViewById(R.id.photo_cart)
        val title_cart: TextView = itemView.findViewById(R.id.title_cart)
        val price_cart: TextView = itemView.findViewById(R.id.price_cart)
        val description_cart: TextView = itemView.findViewById(R.id.description_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_ONE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row,
                    parent, false)
                ViewHolderOne(view, listener)
            }
            TYPE_TWO -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row_cart,
                    parent, false)
                ViewHolderTwo(view,listener)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)) {
            TYPE_ONE -> {
                val currentItem = products[position]
                val viewHolder = holder as? ViewHolderOne
                viewHolder!!.title.text = currentItem.title
                viewHolder.description.text = currentItem.description

                viewHolder.price.text = currentItem.price
                viewHolder.image.setImageResource(currentItem.photoURL)

                viewHolder.imageBtn.setImageResource(if (currentItem.hidden) R.drawable.shopping_cart_green else R.drawable.shopping_cart_red)

                viewHolder.imageBtn.setOnClickListener {
                    listener?.onItemClick(position)
                }

            }
            TYPE_TWO -> {
                val currentItemCart = products_cart[position-products.size]
                val viewHolderCart = holder as? ViewHolderTwo
                if (currentItemCart.isHidden) {
                    holder.itemView.visibility = View.GONE
                } else {
                    holder.itemView.visibility = View.VISIBLE
                }
                viewHolderCart!!.title_cart.text = currentItemCart.title_cart
                viewHolderCart.description_cart.text = currentItemCart.description_cart
                viewHolderCart.price_cart.text = currentItemCart.price_cart
                viewHolderCart.image_cart.setImageResource(currentItemCart.photoURL_cart)
            }
        }

    }

    override fun getItemCount() = products.size+products_cart.size

    override fun getItemViewType(position: Int): Int {
        return if (position < products.size) {
            TYPE_ONE
        } else {
            TYPE_TWO
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}