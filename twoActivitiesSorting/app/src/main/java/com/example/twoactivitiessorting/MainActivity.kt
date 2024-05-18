package com.example.twoactivitiessorting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twoactivitiessorting.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            data?.let {
                if (it.hasExtra("sortedOutputs")) {
                    val sortedOutputs = it.extras?.getString("sortedOutputs")
                    binding.outputText.text = sortedOutputs
                }
            }
        }
        //binding.outputText.text = "activity two doesn't crash"
    }


    fun sendData(view: View){
        val i = Intent(this, MainActivity2::class.java)
        val textOut = binding.dataInput.text.toString()
        //b, i, s, m, q
        val sortsActive = binding.bubbleBox.isChecked.toString()+','+binding.insertBox.isChecked.toString()+','+
            binding.selectBox.isChecked.toString()+','+binding.mergeBox.isChecked.toString()+','+binding.quickBox.isChecked.toString()

        //binding.outputText.text = sortsActive

        i.putExtra("dataInput", textOut)
        i.putExtra("sortsActive", sortsActive)
        startForResult.launch(i)
    }
}