package com.example.firehot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btclear.setOnClickListener {
            firstname.setText("")
            surname.setText("")
        }

        btsend.setOnClickListener {
            val first = firstname.text.toString()
            val last = surname.text.toString()

            val firebase = FirebaseDatabase.getInstance()
            val ref = firebase.getReference("employee")
            val id:String? = ref.push().key

            val employee = employee(id.toString(),first,last)
            
            ref.child(id.toString()).setValue(employee).addOnCompleteListener {
                Toast.makeText(applicationContext,"complete",Toast.LENGTH_LONG).show()
                firstname.setText("")
                surname.setText("")
            }
        }

    }
}