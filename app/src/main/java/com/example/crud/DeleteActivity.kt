package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DeleteActivity : AppCompatActivity() {

    val db = Firebase.firestore
    lateinit var deleteIds: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                deleteIds = Array(result.size()){""}
                for ((index, doc) in result.withIndex()) {
                    deleteIds[index] = doc.id
                    Log.d("TAG", deleteIds.joinToString(", "))
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Falha ao buscar", Toast.LENGTH_SHORT).show()
            }

        val deleteButton: Button = findViewById(R.id.delete_button)
        deleteButton.setOnClickListener {
            delete()
        }
    }

    private fun delete() {
        val idToDelete: Int = findViewById<TextView>(R.id.delete_id).text.toString().toInt()

        db.collection("products")
            .document(deleteIds[idToDelete - 1])
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Produto deletado com sucesso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this, "Falha ao deletar", Toast.LENGTH_SHORT).show()
            }
    }
}