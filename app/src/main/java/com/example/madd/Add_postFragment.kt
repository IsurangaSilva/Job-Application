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


class Add_postFragment : Fragment() {

    private lateinit var etPostUserName: EditText
    private lateinit var etPostName: EditText
    private lateinit var etPostEmail: EditText
    private lateinit var etPostDescription: EditText
    private lateinit var btnSaveData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_add_post, container, false)

        etPostUserName = view.findViewById(R.id.editTextTextPersonName18)
        etPostName = view.findViewById(R.id.editTextTextPersonName5)
        etPostEmail = view.findViewById(R.id.editTextTextPersonName6)
        etPostDescription = view.findViewById(R.id.editTextTextPersonName7)



        btnSaveData = view.findViewById(R.id.button4)

        dbRef = FirebaseDatabase.getInstance().getReference("Posts")

        btnSaveData.setOnClickListener {
            saveEmployeeData()
        }

        return view
    }

    private fun saveEmployeeData() {

        //getting values
        val postUserName = etPostUserName.text.toString()
        val postName = etPostName.text.toString()
        val postEmail = etPostEmail.text.toString()
        val postDescription = etPostDescription.text.toString()

        if (postUserName.isEmpty()) {
            etPostUserName.error = "Please enter User name"
        }
        if (postName.isEmpty()) {
            etPostName.error = "Please enter name"
        }

        if (postEmail.isEmpty()) {
            etPostEmail.error = "Please enter Email"
        }
        if (postDescription.isEmpty()) {
            etPostDescription.error = "Please enter Description"
        }




        val empId = dbRef.push().key!!

        val employee = PostModel(empId,postUserName, postName,postEmail, postDescription)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(requireContext(), "Data inserted successfully", Toast.LENGTH_LONG).show()


                etPostUserName.text.clear()
                etPostName.text.clear()
                etPostEmail.text.clear()
                etPostDescription.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(requireContext(), "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}