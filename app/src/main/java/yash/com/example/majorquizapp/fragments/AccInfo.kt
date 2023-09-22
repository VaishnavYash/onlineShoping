package yash.com.example.majorquizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import yash.com.example.majorquizapp.R

class AccInfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view :View? = inflater.inflate(R.layout.fragment_acc_info, container, false)

        val homeBtn: Button = view!!.findViewById(R.id.acc_info_home)
        val cartBtn: Button = view.findViewById(R.id.acc_info_cart)
        val signOut: Button = view.findViewById(R.id.acc_info_logout)
        val nameDis: TextView = view.findViewById(R.id.acc_info_name)
        val emailDis: TextView = view.findViewById(R.id.acc_info_email)

        val args = arguments
        val value: String? = args!!.getString("VALUE")
        val name: String? = args!!.getString("name")
        emailDis.text = value
        nameDis.text = name

        homeBtn.setOnClickListener {
            val fragment = requireFragmentManager().beginTransaction()
            fragment.replace(R.id.fragment_container,HomeFragment()).commit()
            fragment.addToBackStack(null)
        }

        cartBtn.setOnClickListener {
            val fragment = requireFragmentManager().beginTransaction()
            fragment.replace(R.id.fragment_container,CartFragment()).commit()
            fragment.addToBackStack(null)
        }

        signOut.setOnClickListener {
            activity?.finish()
        }
        return view
    }

}