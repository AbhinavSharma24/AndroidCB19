package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_marvel.view.*

class MarvelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_marvel, container, false)
        val name = arguments?.getString("NAME")
        if (view.id == R.id.marvelLv) {
            view.marvelLv.adapter = ArrayAdapter<String>(
                requireContext(), android.R.layout.simple_list_item_1,
                arrayOf("Hulk", "Thor", "Iron Man", "Captain America", "Black Panther")
            )
        }
        return view
    }
}
