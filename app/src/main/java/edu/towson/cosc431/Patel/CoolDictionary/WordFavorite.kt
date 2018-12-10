package edu.towson.cosc431.Patel.CoolDictionary

import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_word_favorite.*

class WordFavorite : AppCompatActivity(), IController {


    // define database
    lateinit var db: IDatabase

    // create a mutable list to add todos
    var wordsList: MutableList<WordDataClass> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_favorite)

        // initialize the database
        db = WordDatabase(this)

        wordsList.addAll(db.getFavorite())


        //1. instantiate the TodosAdapter
        val wordAdapter = WordAdapter(wordsList, this)


        //2. set the LayoutManager on the recyclerView
        recyclerFavorite.layoutManager = LinearLayoutManager(this)

        //3. set the adapter on the recyclerView
        recyclerFavorite.adapter = wordAdapter
    }


    // Method to launch the word display activity.
    override fun launchWordActivity(word: String) {
        // initialize the intent
        val intent = Intent()

        // specify the intent to open new activity.
        intent.component = ComponentName(this, WordDisplay :: class.java)

        when{
            word.isEmpty() -> Toast.makeText(this@WordFavorite, "Enter a cool word", Toast.LENGTH_SHORT).show()
            else -> {
                intent.putExtra(MainActivity.MSG_TEXT, word)
                startActivity(intent)
            }
        }

    }
}
