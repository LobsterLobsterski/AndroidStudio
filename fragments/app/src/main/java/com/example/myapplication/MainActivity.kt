package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Fragment1.Listener1, Fragment2.DrugiListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun toFragment2(tekst: String) {
        var drugiFragment = supportFragmentManager.findFragmentById(R.id.fragment2) as Fragment2
        drugiFragment.zmienTekst(tekst)
    }
    override fun doFragmentu1(tekst: String) {
        var pierwszyFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentA) as Fragment1
        pierwszyFragment.zmienTekst(tekst)
    }
}