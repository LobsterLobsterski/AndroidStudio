package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.Fragment2Binding


class Fragment2 : Fragment() {

    private var _binding: Fragment2Binding? = null
    private val binding get() = _binding!!

    var drugiFragmentCallback: DrugiListener? = null
    interface DrugiListener {
        fun doFragmentu1(tekst: String)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            drugiFragmentCallback = context as DrugiListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                    + " trzeba zaimplementować DrugiListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.klawiszFb.setOnClickListener { v: View -> onButtonFbClicked(v) }
    }
    private fun onButtonFbClicked(view: View) {
        drugiFragmentCallback?.doFragmentu1(binding.tekstFbOut.text.toString())
    }

    fun zmienTekst(tekst: String) {
        binding.tekstFbIn.text = tekst
    }

}