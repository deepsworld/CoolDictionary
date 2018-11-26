package edu.towson.cosc431.Patel.CoolDictionary


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

        searchBtn.setOnClickListener { searchWord() }


    }
    fun searchWord() {

        var word = editText.text
        val API_URL = "https://googledictionaryapi.eu-gb.mybluemix.net/?define="
        val url = "${API_URL}${word}&lang=en"
        var wordResponse: String = ""


        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    wordResponse = response.toString()
                    println(wordResponse)
                    val word_data = Gson().fromJson<Word>(wordResponse, Word::class.java )
                    println(word_data)
                    textView.text = word_data.toString()
                },
                Response.ErrorListener { error ->
                    // Error handling here.
                    Toast.makeText(this@MainActivity, "Failed to get the word", Toast.LENGTH_SHORT).show()
                }
        )

        // Access the RequestQueue through the singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

}

