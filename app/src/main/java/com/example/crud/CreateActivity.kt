package com.example.crud

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val createButton = findViewById<Button>(R.id.create_button)
        createButton.setOnClickListener {
            create()
        }
    }

    private fun create() {
        val name: String = findViewById<EditText>(R.id.create_name).text.toString()
        val price: String = findViewById<EditText>(R.id.create_price).text.toString()
        val amount: String = findViewById<EditText>(R.id.create_amount).text.toString()

        val product = hashMapOf(
            "name" to name,
            "price" to price,
            "amount" to amount
        )

        db.collection("products")
            .add(product)
            .addOnSuccessListener {
                Toast.makeText(this, "Produto cadastrado", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this, "Falha ao cadastrar", Toast.LENGTH_SHORT).show()
            }

    }
}