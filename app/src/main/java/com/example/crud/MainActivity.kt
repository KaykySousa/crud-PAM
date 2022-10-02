package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createIntent = Intent(this, CreateActivity::class.java)
        val listIntent = Intent(this, ListActivity::class.java)
        val updateIntent = Intent(this, FirstUpdateActivity::class.java)
        val deleteIntent = Intent(this, DeleteActivity::class.java)

        val menuCreateButton: Button = findViewById<Button>(R.id.menu_create_button)
        menuCreateButton.setOnClickListener {
            startActivity(createIntent)
        }

        val menuReadButton: Button = findViewById<Button>(R.id.menu_read_button)
        menuReadButton.setOnClickListener {
            startActivity(listIntent)
        }

        val menuDeleteButton: Button = findViewById<Button>(R.id.menu_delete_button)
        menuDeleteButton.setOnClickListener {
            startActivity(deleteIntent)
        }

        val menuUpdateButton: Button = findViewById<Button>(R.id.menu_update_button)
        menuUpdateButton.setOnClickListener {
            startActivity(updateIntent)
        }
    }
}