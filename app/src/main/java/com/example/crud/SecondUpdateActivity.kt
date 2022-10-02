package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SecondUpdateActivity : AppCompatActivity() {

    val db = Firebase.firestore

    lateinit var idToUpdate: String

    lateinit var nameInput: EditText
    lateinit var priceInput: EditText
    lateinit var amountInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_update)

        nameInput = findViewById<EditText>(R.id.second_update_name)
        priceInput = findViewById<EditText>(R.id.second_update_price)
        amountInput = findViewById<EditText>(R.id.second_update_amount)

        idToUpdate = intent.getStringExtra("idToUpdate").toString()

        if (idToUpdate === null) {
            Toast.makeText(this, "Falha ao buscar dados", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            return
        }

        db.collection("products")
            .document(idToUpdate)
            .get()
            .addOnSuccessListener { result ->
                if (result !== null) {
                    Log.d("DOCUMENT TO UPDATE", result.data.toString())

                    nameInput.setText(result.data?.get("name").toString())
                    priceInput.setText(result.data?.get("price").toString())
                    amountInput.setText(result.data?.get("amount").toString())

                } else {
                    Toast.makeText(this, "Falha ao buscar dados", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }

            }
            .addOnFailureListener {
                Toast.makeText(this, "Falha ao buscar dados", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }

        val secondUpdateButton: Button = findViewById<Button>(R.id.second_update_button)
        secondUpdateButton.setOnClickListener {
            update()
        }

    }

    private fun update() {

        val name = nameInput.text.toString()
        val price = priceInput.text.toString()
        val amount = amountInput.text.toString()

        val updatedProduct = mapOf(
            "name" to name,
            "price" to price,
            "amount" to amount
        )

        db.collection("products")
            .document(idToUpdate)
            .update(updatedProduct)
            .addOnSuccessListener {
                Toast.makeText(this, "Produto atualizado", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this, "Falha ao atualizar", Toast.LENGTH_SHORT).show()
            }
    }
}