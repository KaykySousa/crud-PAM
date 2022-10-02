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

class FirstUpdateActivity : AppCompatActivity() {

    val db = Firebase.firestore
    lateinit var updateIds: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_update)

        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                updateIds = Array(result.size()){""}
                for ((index, doc) in result.withIndex()) {
                    updateIds[index] = doc.id
                    Log.d("TAG", updateIds.joinToString(", "))
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Falha ao buscar", Toast.LENGTH_SHORT).show()
            }

        val firstUpdateButton: Button = findViewById<Button>(R.id.first_update_button)
        firstUpdateButton.setOnClickListener {
            updateUI()
        }
    }

    private fun updateUI() {
        val idToUpdate: Int = findViewById<EditText>(R.id.first_update_id).text.toString().toInt()

        val secondUpdateIntent = Intent(this, SecondUpdateActivity::class.java)
        secondUpdateIntent.putExtra("idToUpdate", updateIds[idToUpdate - 1])
        startActivity(secondUpdateIntent)
    }
}