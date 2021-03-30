package com.example.lastlong_assign2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class register : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        if (auth!!.currentUser != null){
            val it = Intent(this, member::class.java)
            startActivity(it)
            finish()
        }

        cmdregisters.setOnClickListener {
            val email = edtemail.text.toString().trim()
            val pass = edtpassword.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please input the Email.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (pass.isEmpty()) {
                Toast.makeText(this, "Please input the password.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            auth!!.createUserWithEmailAndPassword(email,pass).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (pass.length < 8) {
                        edtpassword.error = "Please input password more than 8 words."
                    } else {
                        Toast.makeText(
                            this,
                            "Login error because:" + task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(this, "Login Success!", Toast.LENGTH_LONG).show()
                    val it = Intent(this, member::class.java)
                    startActivity(it)
                    finish()
                }
            }
        }

        cmdlogins.setOnClickListener {
            val it = Intent(this, cmdlogins::class.java)
            startActivity(it)
        }

    }
}