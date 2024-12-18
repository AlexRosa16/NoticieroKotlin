package com.alexrosa.noticieroalex
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences: SharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val etUser = findViewById<TextInputEditText>(R.id.ntTextInputEditTextuser)
        val etPass = findViewById<TextInputEditText>(R.id.ntTextInputEditTextpassword)
        val btnLogin = findViewById<MaterialButton>(R.id.ntButtonLogin)

        etUser.setText(sharedPreferences.getString("user", ""))
        etPass.setText(sharedPreferences.getString("password", ""))

        btnLogin.setOnClickListener {
            val user = etUser.text.toString()
            val password = etPass.text.toString()

            if (user.isNotEmpty() && password.isNotEmpty()) {
                editor.putString("user", user)
                editor.putString("password", password)
                editor.apply()

                val intent = Intent(this, NoticiasActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                if (user.isEmpty()) etUser.error = "Por favor, introduce un usuario"
                if (password.isEmpty()) etPass.error = "Por favor, introduce una contraseña"
            }
        }

        val passwordField = findViewById<TextInputEditText>(R.id.ntTextInputEditTextpassword)
        val textInputLayout = findViewById<TextInputLayout>(R.id.ntTextInputLayoutpassword)

        var isPasswordVisible = false

        textInputLayout.setEndIconOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                textInputLayout.endIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_visibility)
            } else {
                passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                textInputLayout.endIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_visibility_off)
            }
            passwordField.setSelection(passwordField.text?.length ?: 0)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageView = findViewById<ImageView>(R.id.ntImageViewFondo)
        Glide.with(this)
            .load("https://img.freepik.com/foto-gratis/vista-superior-viejos-periodicos-franceses_23-2149318857.jpg")
            .centerCrop()
            .into(imageView)
    }
}
