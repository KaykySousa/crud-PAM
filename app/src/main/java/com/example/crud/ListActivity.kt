package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        read()
    }

    private fun read() {

        val productListTextView = findViewById<TextView>(R.id.read_product_list)

        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                for ((index, doc) in result.withIndex()) {
                    productListTextView.text = productListTextView.text.toString() + "${index + 1} - ${doc.data["name"]} - R$ ${doc.data["price"]} - ${doc.data["amount"]} itens \n"

                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Falha ao buscar", Toast.LENGTH_SHORT).show()
            }
    }
}