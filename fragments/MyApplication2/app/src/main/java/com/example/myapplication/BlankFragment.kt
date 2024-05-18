package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {
    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    var activityCallback: BlankFragment.PierwszyListener? = null
    interface PierwszyListener {
        fun onButtonClick(text: String)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as PierwszyListener
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
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.klawiszF1a.setOnClickListener { v: View -> buttonClicked(v) }
    }
    private fun buttonClicked(view: View) {
        activityCallback?.onButtonClick(binding.tekstF1a.text.toString())
    }

}