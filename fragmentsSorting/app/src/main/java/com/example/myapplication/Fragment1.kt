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

    var pierwszyFragmentCallback: PierwszyListener? = null
    interface PierwszyListener {
        fun doFragmentu2(tekst: String)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            pierwszyFragmentCallback = context as PierwszyListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                    + " trzeba zaimplementować PierwszyListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener { v: View -> onButtonClicked(v) }
    }
    private fun onButtonClicked(view: View) {
        pierwszyFragmentCallback?.doFragmentu2(binding.dataInput.text.toString())

    }
}
