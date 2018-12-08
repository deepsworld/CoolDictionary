package edu.towson.cosc431.Patel.CoolDictionary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_word_history.*

class WordHistory : AppCompatActivity() {

    // define database
    lateinit var db: IDatabase

    // create a mutable list to add todos
    var wordsList: MutableList<WordDataClass> = mutableListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_history)

        // initialize the database
        db = WordDatabase(this)

        wordsList.addAll(db.getWords())


        //1. instantiate the TodosAdapter
        val wordAdapter = WordAdapter(wordsList)


        //2. set the LayoutManager on the recyclerView
        recyclerHistory.layoutManager = LinearLayoutManager(this)

        //3. set the adapter on the recyclerView
        recyclerHistory.adapter = wordAdapter
    }
}
