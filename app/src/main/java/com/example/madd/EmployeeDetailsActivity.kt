package com.example.madd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EmployeeDetailsActivity : AppCompatActivity() {
    private lateinit var etEmpName: TextView
    private lateinit var etEmpJobTitle: TextView
    private lateinit var  etEmpEducation: TextView
    private lateinit var etEmpAddress: TextView
    private lateinit var etEmpPhone: TextView
    private lateinit var etEmpEmail: TextView
    private lateinit var  etEmpAbout: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        initView()
        setValuesToViews()


    }

    private fun initView() {

        etEmpName = findViewById(R.id.textView11)
        etEmpJobTitle = findViewById(R.id.textView21)
        etEmpEducation = findViewById(R.id.textView24)
        etEmpAddress = findViewById(R.id.textView16)
        etEmpPhone = findViewById(R.id.textView13)
        etEmpEmail = findViewById(R.id.textView27)
        etEmpAbout = findViewById(R.id.textView22)

        btnUpdate = findViewById(R.id.update12)
        btnDelete = findViewById(R.id.delete12)
    }

    private fun setValuesToViews() {

        etEmpName.text  = intent.getStringExtra("empName")
        etEmpJobTitle.text  = intent.getStringExtra("empJobTitle")
        etEmpEducation.text  = intent.getStringExtra("empEducation")
        etEmpAddress.text  = intent.getStringExtra("empAddress")
        etEmpPhone.text  = intent.getStringExtra("empPhone")
        etEmpEmail.text  = intent.getStringExtra("empEmail")
        etEmpAbout.text  = intent.getStringExtra("empAbout")


    }
}




