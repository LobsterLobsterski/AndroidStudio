package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  com.example.myapplication.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!

    var pierwszyFragmentCallback: Listener1? = null

    interface Listener1 {
        fun toFragment2(tekst: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            pierwszyFragmentCallback = context as Listener1
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                    + " need to implement Listener1")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.klawiszFa.setOnClickListener { v: View -> onButtonFaClicked(v) }
    }
    private fun onButtonFaClicked(view: View) {
        pierwszyFragmentCallback?.toFragment2(binding.tekstFaOut.text.toString())
    }
    fun zmienTekst(tekst: String) {
        binding.tekstFaIn.text = tekst
    }
}