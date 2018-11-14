package edu.towson.cosc431.Patel.CoolDictionary


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError

import com.androidnetworking.interfaces.ParsedRequestListener
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBtn.setOnClickListener { searchWord() }

        AndroidNetworking.initialize(this)
        AndroidNetworking.enableLogging()

    }


    fun searchWord() {

        var word = editText.text
        val API_URL = "https://googledictionaryapi.eu-gb.mybluemix.net/?define="
        val url = URL("${API_URL}${word}&lang=en")

        AndroidNetworking.get(url.toString())
                .build()
                .getAsObject(ApiResult :: class.java, object: ParsedRequestListener<ApiResult> {
                   override fun onResponse(response: ApiResult?) {
                       if(response == null){
                           Log.d("manohman", "Null bro")
                       }
                       if(response != null) {
                           Log.d("manohman", url.toString())
                           Log.d("manohman", response.toString())
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(this@MainActivity, "Failed to download user list", Toast.LENGTH_SHORT).show()
                    }
                })



    }

}

