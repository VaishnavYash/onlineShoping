package yash.com.example.majorquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class OldUserActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old_user)
        supportActionBar?.hide()

        val btnLogin : Button = findViewById(R.id.btn_log)
        val newReg : Button = findViewById(R.id.newAccount)

        val editOldEmail: EditText = findViewById(R.id.editOldUserEmail)
        val editOldPass: EditText = findViewById(R.id.editOldUserPassword)

        val textOldEmail: TextInputLayout = findViewById(R.id.textOldUserEmail)
        val textOldPass: TextInputLayout = findViewById(R.id.textOldUserPassword)

        newReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val db = DatabaseTable(this)

        btnLogin.setOnClickListener{
            if(!InputValidation.isInputEditTextFilled(editOldEmail, textOldEmail, "Enter Valid Email")){
                return@setOnClickListener
            }
            if(!InputValidation.isInputEditTextFilled(editOldPass, textOldPass, "Enter Correct Password")){
                return@setOnClickListener
            }
            val email = editOldEmail.text.toString().trim()
            val pass = editOldPass.text.toString().trim()
            if(db.checkEmail(email)){
                Toast.makeText(this, "Email not Registered", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(db.checkLogin(email,pass)){
                val intent = Intent(this, DrawerActivity::class.java)
                intent.putExtra("key", email)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Invalid Email/Incorrect Password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}