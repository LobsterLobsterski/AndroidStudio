package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, InfoFragment())
            .commit()

        binding.addAttractonButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        viewRecords()
    }


    private fun viewRecords(){

        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHelper= DatabaseHelper(this)

        //calling the viewAttractions method of DatabaseHandler class to read the records
        val emp: List<AttractionsModelClass> = databaseHandler.viewAttractions()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}
        var index = 0

        for(e in emp){
            empArrayId[index] = e.attractionId.toString()
            empArrayName[index] = e.attractionName
            empArrayEmail[index] = e.attractionDescription
            index++
        }

        val myListAdapter = MyListAdapter(this,empArrayName,empArrayEmail)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = myListAdapter
    }

    fun gotoAddAttraction(){

    }


}
