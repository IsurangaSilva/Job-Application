package com.example.madd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class EditCompanyUsers : AppCompatActivity() {

    private lateinit var tvCompId: TextView
    private lateinit var tvCompAge: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_company_users)
        initView()
        setValuesToViews()

    }

    private fun initView() {
        tvCompId = findViewById(R.id.tvCompId)
        tvCompAge = findViewById(R.id.tvCompAge)

    }

    private fun setValuesToViews() {
        tvCompId.text = intent.getStringExtra("empId")
        tvCompAge.text = intent.getStringExtra("empAge")
    }

}