package com.example.madd

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FetchingFragment :  Fragment(){

    private lateinit var btnFetchPost: Button
    private lateinit var btnFetchData: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fetching, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFetchData = view.findViewById(R.id.btnFetchData)
        btnFetchPost = view.findViewById(R.id.btnFetchPost)

        btnFetchData.setOnClickListener {
            val intent = Intent(requireContext(), FetchingActivity::class.java)
            startActivity(intent)
        }
        btnFetchPost.setOnClickListener {
            val intent = Intent(requireContext(), FetchingPost::class.java)
            startActivity(intent)
        }



    }



}