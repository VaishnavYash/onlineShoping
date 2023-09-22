package yash.com.example.majorquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val regBtn : Button = findViewById(R.id.btn_reg)
        val oldBtn : Button = findViewById(R.id.oldAccount)

        val editUserName: EditText = findViewById(R.id.editUserName)
        val editEmailName: EditText = findViewById(R.id.editUserEmail)
        val editPassword: EditText = findViewById(R.id.editUserPassword)
        val editConPass: EditText = findViewById(R.id.editConfirmPassword)

        val textUserName: TextInputLayout = findViewById(R.id.textUserName)
        val textEmailName: TextInputLayout = findViewById(R.id.textUserEmail)
        val textPassword: TextInputLayout = findViewById(R.id.textUserPassword)
        val textConPass: TextInputLayout = findViewById(R.id.textConfirmPassword)


        val db = DatabaseTable(this)
        regBtn.setOnClickListener{
            if(!InputValidation.isInputEditTextFilled(editUserName, textUserName, "Enter Full Name")){
                return@setOnClickListener
            }
            if(!InputValidation.isInputEditEmailFilled(editEmailName, textEmailName, "Enter Valid Email")){
                return@setOnClickListener
            }
            if(!InputValidation.isInputEditTextFilled(editPassword, textPassword, "Set Password Here")){
                return@setOnClickListener
            }
            if(!InputValidation.isInputEditMatchFilled(editPassword, editConPass, textConPass, "Password Does not Matches")){
                return@setOnClickListener
            }

            if(db.checkEmail(editEmailName.text.toString().trim())) {
                val name = editUserName.text.toString()
                val email = editEmailName.text.toString()
                val pass = editPassword.text.toString()
                db.addUser(name,email,pass)
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()

                val intent = Intent(this, OldUserActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Email Already Exists", Toast.LENGTH_LONG).show()
            }
        }

        oldBtn.setOnClickListener {
            val intent = Intent(this, OldUserActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

}