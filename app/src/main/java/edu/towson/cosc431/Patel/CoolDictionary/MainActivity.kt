package edu.towson.cosc431.Patel.CoolDictionary


import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError

import com.androidnetworking.interfaces.ParsedRequestListener
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Search button listener.
        searchBtn.setOnClickListener {
            launchWordDisplay() }

        // Word of the day textview listener.

        // History button listener.

        // Trivia question button listener.

        // Favorite word button listener.


    }

    // Method to launch the word display activity.
    private fun launchWordDisplay() {
        // initialize the intent
        val intent = Intent()

        // specify the intent to open new activity.
        intent.component = ComponentName(this, WordDisplay :: class.java)

        // get the word from the editText field.
        val word = editText.text.toString()

        when{
            word.isEmpty() -> Toast.makeText(this@MainActivity, "Enter a cool word", Toast.LENGTH_SHORT).show()
            else -> {
                intent.putExtra(MSG_TEXT, word )
                startActivity(intent)
            }
        }

    }


    companion object {
        val MSG_TEXT = "man oh man"
    }

}

