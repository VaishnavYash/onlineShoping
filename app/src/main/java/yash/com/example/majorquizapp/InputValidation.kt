package yash.com.example.majorquizapp

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

object InputValidation {

     fun isInputEditTextFilled(textInputEditText: EditText, textInputLayout: TextInputLayout,
                                      message: String): Boolean {
          var value: String = textInputEditText.text.toString().trim()
          return if(value.isEmpty()){
               textInputLayout.error = message
               false
          }else{
               textInputLayout.isErrorEnabled=false
               true
          }
     }
     fun isInputEditEmailFilled(textInputEditText: EditText, textInputLayout: TextInputLayout,
                               message: String): Boolean {
          var value: String = textInputEditText.text.toString().trim()
          return if(value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()){
               textInputLayout.error = message
               false
          }else{
               textInputLayout.isErrorEnabled=false
               true
          }
     }

     fun isInputEditMatchFilled(password: EditText, textInputEditText: EditText, textInputLayout: TextInputLayout,
                                message: String):Boolean {
          var value: String = textInputEditText.text.toString().trim()
          var pass: String = password.text.toString().trim()
          return if(value.isEmpty() || value !=pass){
               textInputLayout.error = message
               false
          }else{
               textInputLayout.isErrorEnabled=false
               true
          }
     }
}

