package com.example.lastlong_assign2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_member.*

class member : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)

        auth = FirebaseAuth.getInstance()

        val userdata = auth!!.currentUser
        tvoutput.text = userdata?.uid.toString()+""+userdata!!.email

        cmdlogout.setOnClickListener {
            auth!!.signOut()
            Toast.makeText(this, "Logout Complete", Toast.LENGTH_LONG).show()

            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
            finish()
        }

    }
}