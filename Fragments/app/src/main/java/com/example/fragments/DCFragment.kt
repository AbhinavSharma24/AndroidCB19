package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_dc2.view.*

class DCFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dc2, container, false)

        val name = arguments?.getString("NAME")
        if(name == "DC MOVIES") {
            view.dcLv.adapter = ArrayAdapter<String>(
                requireContext(), android.R.layout.simple_list_item_1,
                arrayOf("Wonder Woman", "Aqua Man", "Batman vs Superman", "Justice League", "Flash")
            )
        }
        else if(name == "MARVEL MOVIES"){
            view.dcLv.adapter = ArrayAdapter<String>(
                requireContext(), android.R.layout.simple_list_item_1,
                arrayOf("Hulk", "Thor", "Iron Man", "Captain America", "Black Panther")
            )
        }
        return view
    }
}
