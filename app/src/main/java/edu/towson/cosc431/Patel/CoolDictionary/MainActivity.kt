package edu.towson.cosc431.Patel.CoolDictionary


import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordOfTheDay()

        // get the date
        val dateCreated = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"))

        dateView.append(dateCreated)

        // Search button listener.
        searchBtn.setOnClickListener {
            launchWordDisplay() }

        // Word of the day textview listener.
        wordView.setOnClickListener {
            launchWordOTD()
        }

        // History button listener.
        historyBtn.setOnClickListener {
            launchHistoryDisplay()
        }

        // Trivia question button listener.
        playTriviaBtn.setOnClickListener {
            launchTriviaDisplay()
        }


        // Favorite word button listener.
        viewFavoriteBtn.setOnClickListener {
            launchFavoriteDisplay()
        }


    }

    private fun launchTriviaDisplay() {
        // initialize the intent
        val intent = Intent()

        // specify the intent to open new activity.
        intent.component = ComponentName(this, TriviaActivity:: class.java)


        startActivity(intent)
    }

    private fun launchWordOTD() {
        // initialize the intent
        val intent = Intent()

        // specify the intent to open new activity.
        intent.component = ComponentName(this, WordOTDDisplay:: class.java)
        intent.putExtra(MSG_TEXT2, wordView.text)

        startActivity(intent)
    }

    private fun wordOfTheDay(){

        val url = "https://urban-word-of-the-day.herokuapp.com/today"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    val wordData = Gson().fromJson<WordOTD>(response.toString(), WordOTD::class.java )
                    wordView.text = wordData.word
                },

                // if the word cannot be found... handle error here
                Response.ErrorListener { error ->
                    // Error handling here.
                    Log.d("WordOfTheDay", error.toString())
                }
        )

        // Access the RequestQueue through the singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    private fun launchFavoriteDisplay() {
        // initialize the intent
        val intent = Intent()

        // specify the intent to open new activity.
        intent.component = ComponentName(this, WordFavorite :: class.java)

        startActivity(intent)
    }

    private fun launchHistoryDisplay() {
        // initialize the intent
        val intent = Intent()

        // specify the intent to open new activity.
        intent.component = ComponentName(this, WordHistory :: class.java)

        startActivity(intent)
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
        val MSG_TEXT2 = "man oh man2"
    }

}

