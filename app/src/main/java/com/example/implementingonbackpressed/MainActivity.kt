package com.example.implementingonbackpressed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    /**
     * Callback for Handling back pressed
     * press back button twice within two seconds to move out of the app.
     */
    private val callback = object : OnBackPressedCallback(true) {
        var doubleBackPressed = false
        override fun handleOnBackPressed() {
            if (doubleBackPressed) {
                finish()
                return
            }
            doubleBackPressed = true
            showMessage()
            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackPressed = false
            }, 2000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun showMessage() {
        Toast.makeText(this, "Please click back again to exit!", Toast.LENGTH_SHORT).show()
    }
}