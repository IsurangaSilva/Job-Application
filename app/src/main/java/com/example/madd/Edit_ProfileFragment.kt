package com.example.madd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Edit_ProfileFragment : Fragment() {




    private lateinit var etEmpName: EditText
    private lateinit var etEmpJobTitle: EditText
    private lateinit var etEmpEducation: EditText
    private lateinit var etEmpAddress: EditText
    private lateinit var etEmpPhone: EditText
    private lateinit var etEmpEmail: EditText
    private lateinit var etEmpAbout: EditText
    private lateinit var btnSaveData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_edit__profile, container, false)

        etEmpName = view.findViewById(R.id.editTextTextPersonName)
        etEmpJobTitle = view.findViewById(R.id.editTextTextPersonName4)
        etEmpEducation = view.findViewById(R.id.editTextTextPersonName2)
        etEmpAddress = view.findViewById(R.id.editTextTextPostalAddress2)
        etEmpPhone = view.findViewById(R.id.editTextPhone)
        etEmpEmail = view.findViewById(R.id.editTextTextEmailAddress)
        etEmpAbout = view.findViewById(R.id.editTextTextPersonName3)

        btnSaveData = view.findViewById(R.id.button123)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        btnSaveData.setOnClickListener {
            saveEmployeeData()
        }

        return view
    }

    private fun saveEmployeeData() {

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
        }
        if (empJobTitle.isEmpty()) {
            etEmpJobTitle.error = "Please enter age"
        }
        if (empEducation.isEmpty()) {
            etEmpEducation.error = "Please enter salary"
        }
        if (empAddress.isEmpty()) {
            etEmpAddress.error = "Please enter name"
        }
        if (empPhone.isEmpty()) {
            etEmpPhone.error = "Please enter age"
        }
        if (empEmail.isEmpty()) {
            etEmpEmail.error = "Please enter salary"
        }
        if (empAbout.isEmpty()) {
            etEmpAbout.error = "Please enter salary"
        }


        val empId = dbRef.push().key!!

        val employee = EmployeModel(empId, empName, empJobTitle, empEducation,empAddress,empPhone,empEmail,empAbout)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(requireContext(), "Data inserted successfully", Toast.LENGTH_LONG).show()

                etEmpName.text.clear()
                etEmpJobTitle.text.clear()
                etEmpEducation.text.clear()
                etEmpAddress.text.clear()
                etEmpPhone.text.clear()
                etEmpEmail.text.clear()
                etEmpAbout.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(requireContext(), "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}

