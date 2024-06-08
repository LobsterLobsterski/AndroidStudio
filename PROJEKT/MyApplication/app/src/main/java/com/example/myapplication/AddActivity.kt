package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityAddBinding
import com.example.myapplication.databinding.ActivityDetailsBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.addActivityButton.setOnClickListener {
            val passed = saveRecord()
            val intent = Intent(this, DetailsActivity::class.java)
            if (passed)startActivity(intent)
        }
    }


    private fun saveRecord(): Boolean {

        val u_id = findViewById<EditText>(R.id.u_id)
        val u_name = findViewById<EditText>(R.id.u_name)
        val u_desc = findViewById<EditText>(R.id.u_email)
        val id = u_id.text.toString()
        val name = u_name.text.toString()
        val desc = u_desc.text.toString()
        val databaseHandler: DatabaseHelper= DatabaseHelper(this)

        if(id.trim()!="" && name.trim()!="" && desc.trim()!=""){
            val status = databaseHandler.addAttraction(AttractionsModelClass(Integer.parseInt(id), name, desc))

            if(status > -1){
                Toast.makeText(applicationContext,"record save", Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_name.text.clear()
                u_desc.text.clear()
            }
            return true
        }else{
            binding.errorTextView.text = "id lub nazwa lub opis nie może być pusty!!!"
            Toast.makeText(applicationContext,"id or name or email cannot be blank", Toast.LENGTH_LONG).show()
            return false
        }

    }
}