package kh.edu.rupp.homeworkmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * LoginActivity handles user authentication.
 * For now, it simply navigates to the MainActivity after validation.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for the login screen
        setContentView(R.layout.activity_login)

        // Link the Kotlin variables to the XML views using their IDs
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val tvRegister: TextView = findViewById(R.id.tvRegister)

        // Set up the click listener for the Login button
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Simple check to ensure fields are not empty
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Create an Intent to navigate from LoginActivity to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                
                // Finish LoginActivity so the user cannot go back to it after logging in
                finish()
            } else {
                // Show a small message if fields are empty
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up the click listener for the Register link
        tvRegister.setOnClickListener {
            // For now, just show a message since the Register screen isn't ready
            Toast.makeText(this, "Registration feature is coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}
