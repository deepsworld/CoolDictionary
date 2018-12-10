package edu.towson.cosc431.Patel.CoolDictionary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_word_display.*
import kotlinx.android.synthetic.main.activity_word_otddisplay.*

class WordOTDDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_otddisplay)
        searchWord()
    }


    // Function to get the word from the api in JSON format.
    fun searchWord() {
        // get the word from intent as string
        val word_main = intent.extras?.get(MainActivity.MSG_TEXT2) as String
        val word = word_main
        word_title_OTD.text = word

        val url = "https://urban-word-of-the-day.herokuapp.com/today"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    val wordData = Gson().fromJson<WordOTD>(response.toString(), WordOTD::class.java)
                    showMeaning(wordData)
                },

                // if the word cannot be found... handle error here
                Response.ErrorListener { error ->
                    // Error handling here.
                    Log.d("WordDisplay", error.toString())
                    Toast.makeText(this@WordOTDDisplay, "We don't do such words", Toast.LENGTH_SHORT).show()
                }
        )

        // Access the RequestQueue through the singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }


    // method to organize and display the received data.
    private fun showMeaning(wordData: WordOTD) {

        if (wordData.meaning != null) {
            wordContentViewOTD.append(getString(R.string.useAdjective))
            var i = 0

            wordContentViewOTD.append("-Defination: ")
            wordContentViewOTD.append(wordData.meaning.replace("\n", " "))

            wordContentViewOTD.append("\n\n")

            wordContentViewOTD.append("-Example: ")
            wordContentViewOTD.append(wordData.example.replace("\n", " "))


        }
    }
}
