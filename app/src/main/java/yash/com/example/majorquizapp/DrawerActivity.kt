package yash.com.example.majorquizapp

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.navigation_header.*
import yash.com.example.majorquizapp.fragments.AccInfo
import yash.com.example.majorquizapp.fragments.CartFragment
import yash.com.example.majorquizapp.fragments.HomeFragment

class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var userName: String? = null
    var emailID: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        drawerLayout.openDrawer(Gravity.LEFT)
        nav_view.setNavigationItemSelectedListener(this)

        setToolbarTitle("Home")
        changeFragment(HomeFragment())

        val db = DatabaseTable(this)
        emailID = intent.getStringExtra("key").toString()
        getHeaderEmailView(emailID!!)
        userName = db.getUserName(emailID!!)
        getHeaderUserView(userName!!)

    }

    private fun getHeaderEmailView(texts:String) {
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val headerView: View = navigationView.getHeaderView(0)
        val navUsername: TextView = headerView.findViewById(R.id.emailDisplay)
        navUsername.text = texts
    }
    private fun getHeaderUserView(texts:String) {
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val headerView: View = navigationView.getHeaderView(0)
        val navUsername: TextView = headerView.findViewById(R.id.nameDisplay)
        navUsername.text = texts
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when(item.itemId){
            R.id.nav_home -> {
                setToolbarTitle("Home")
                changeFragment(HomeFragment())
            }
            R.id.nav_info -> {
                setToolbarTitle("Account Information")
                changeFragment(AccInfo())
            }
            R.id.nav_cart -> {
                setToolbarTitle("Cart")
                changeFragment(CartFragment())
            }
            R.id.nav_sign_out -> {
                val intent = Intent(this, OldUserActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return true
    }

    private fun setToolbarTitle(title: String){
        supportActionBar?.title = title
    }

    private fun changeFragment(frag: Fragment){
        val fragment = supportFragmentManager.beginTransaction()
        val args = Bundle()
        args.putString("VALUE", emailID)
        args.putString("name", userName)
        frag.arguments = args
        fragment.replace(R.id.fragment_container,frag).commit()
    }

}