package com.example.madd

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.*
import java.util.*
import kotlin.concurrent.timerTask

class AdminPerformance : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private var totalUsers: Long = 0
    private lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_performance)

        var newTotalUsers: Long = 0
        var prevTotal: Long = 0

        dbRef = FirebaseDatabase.getInstance().getReference("AppUsers")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                totalUsers = dataSnapshot.childrenCount
                val textView9 = findViewById<TextView>(R.id.textView9)
                textView9.text = "$totalUsers"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Failed to read value.", error.toException())
            }
        })

        // Start the timer to get the updated total users count every 5 minutes
        timer = Timer()
        timer.scheduleAtFixedRate(timerTask {
            dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    newTotalUsers = dataSnapshot.childrenCount
                    val textView21 = findViewById<TextView>(R.id.textView21)
                    var difference = newTotalUsers - prevTotal
                    prevTotal = newTotalUsers
                    textView21.text = "$difference"
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(TAG, "Failed to read value.", error.toException())
                }
            })

            // Update the total users count

        }, 0 * 30 * 1000, 1 * 30 * 1000) // 5 minutes in milliseconds

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}