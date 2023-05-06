package com.example.madd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase


class EditProfile : AppCompatActivity() {

    private lateinit var etEmpName: EditText
    private lateinit var etEmpJobTitle: EditText
    private lateinit var etEmpEducation: EditText
    private lateinit var etEmpAddress: EditText
    private lateinit var etEmpPhone: EditText
    private lateinit var etEmpEmail: EditText
    private lateinit var etEmpAbout: EditText
    private lateinit var btnSaveData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        etEmpName = findViewById(R.id.editTextTextPersonName)
        etEmpJobTitle = findViewById(R.id.editTextTextPersonName4)
        etEmpEducation = findViewById(R.id.editTextTextPersonName2)
        etEmpAddress = findViewById(R.id.editTextTextPostalAddress2)
        etEmpPhone = findViewById(R.id.editTextPhone)
        etEmpEmail = findViewById(R.id.editTextTextEmailAddress)
        etEmpAbout = findViewById(R.id.editTextTextPersonName3)

        btnSaveData = findViewById(R.id.button123)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        btnSaveData.setOnClickListener {
            savePostData()
        }
    }

    private fun savePostData() {
        //getting values
        val empName = etEmpName.text.toString()
        val empJobTitle = etEmpJobTitle.text.toString()
        val empEducation = etEmpEducation.text.toString()
        val empAddress = etEmpAddress.text.toString()
        val empPhone  = etEmpPhone.text.toString()
        val empEmail = etEmpEmail.text.toString()
        val empAbout = etEmpAbout.text.toString()

        if (empName.isEmpty()) {
            etEmpName.error = "Please enter name"
            return
        }
        if (empJobTitle.isEmpty()) {
            etEmpJobTitle.error = "Please enter age"
            return
        }
        if (empEducation.isEmpty()) {
            etEmpEducation.error = "Please enter salary"
            return
        }
        if (empAddress.isEmpty()) {
            etEmpAddress.error = "Please enter name"
            return
        }
        if (empPhone.isEmpty()) {
            etEmpPhone.error = "Please enter age"
            return
        }
        if (empEmail.isEmpty()) {
            etEmpEmail.error = "Please enter salary"
            return
        }
        if (empAbout.isEmpty()) {
            etEmpAbout.error = "Please enter salary"
            return
        }

        val empId = dbRef.push().key!!

        val employeModel = EmployeModel(empId, empName, empJobTitle, empEducation,empAddress,empPhone,empEmail,empAbout)

        dbRef.child(empId).setValue(employeModel)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etEmpName.text.clear()
                etEmpJobTitle.text.clear()
                etEmpEducation.text.clear()
                etEmpAddress.text.clear()
                etEmpPhone.text.clear()
                etEmpEmail.text.clear()
                etEmpAbout.text.clear()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}