package edu.towson.cosc431.Patel.CoolDictionary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_word_display.*

class WordDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_display)
        searchWord()
    }

    // Function to get the word from the api in JSON format.
    fun searchWord() {
        // get the word from intent as string
        val word_main = intent.extras?.get(MainActivity.MSG_TEXT) as String?
        val word = word_main
        val API_URL = "https://googledictionaryapi.eu-gb.mybluemix.net/?define="
        val url = "${API_URL}${word?.trim()}&lang=en"
        word_title.text = word

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    val wordData = Gson().fromJson<Word>(response.toString(), Word::class.java )
                    showMeaning(wordData)
                },

                // if the word cannot be found... handle error here
                Response.ErrorListener { error ->
                    // Error handling here.
                    Log.d("WordDisplay", error.toString())
                    Toast.makeText(this@WordDisplay, "We don't do such words", Toast.LENGTH_SHORT).show()
                }
        )

        // Access the RequestQueue through the singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    private fun showMeaning(wordData: Word){
        // Phonetic
        when(wordData.phonetic == null){
            true -> phoneticView.text = getString(R.string.useNone)
            else -> phoneticView.append(" " + wordData.phonetic)
        }

        // Adjective
        if(wordData.meaning.adjective != null){
            wordContentView.append(getString(R.string.useAdjective))
                var i =0
                while(i < wordData.meaning.adjective.size){

                    if(wordData.meaning.adjective[i].definition != null){
                        wordContentView.append("Defination: ")
                        wordContentView.append(wordData.meaning.adjective[i].definition)
                        wordContentView.append("\n")
                    }


                    if(wordData.meaning.adjective[i].example != null){
                        wordContentView.append("Example: ")
                        wordContentView.append(wordData.meaning.adjective[i].example)
                        wordContentView.append("\n")
                    }


                    if(wordData.meaning.adjective[i].synonyms != null){
                        wordContentView.append("Synonyms: ")
                        wordContentView.append(wordData.meaning.adjective[i].synonyms.toString())

                    }

                    wordContentView.append("\n\n")


                    i ++
                }
            }

        // Adverb
        if(wordData.meaning.adverb != null){
            wordContentView.append(getString(R.string.useAdverb))
            var i =0
            while(i < wordData.meaning.adverb.size){

                if(wordData.meaning.adverb[i].definition != null){
                    wordContentView.append("Defination: ")
                    wordContentView.append(wordData.meaning.adverb[i].definition)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.adverb[i].example != null){
                    wordContentView.append("Example: ")
                    wordContentView.append(wordData.meaning.adverb[i].example)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.adverb[i].synonyms != null){
                    wordContentView.append("Synonyms: ")
                    wordContentView.append(wordData.meaning.adverb[i].synonyms.toString())

                }

                wordContentView.append("\n\n")
                i ++
            }
        }

        // Verb
        if(wordData.meaning.verb != null){
            wordContentView.append(getString(R.string.useVerb))
            var i =0
            while(i < wordData.meaning.verb.size){

                if(wordData.meaning.verb[i].definition != null){
                    wordContentView.append("Defination: ")
                    wordContentView.append(wordData.meaning.verb[i].definition)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.verb[i].example != null){
                    wordContentView.append("Example: ")
                    wordContentView.append(wordData.meaning.verb[i].example)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.verb[i].synonyms != null){
                    wordContentView.append("Synonyms: ")
                    wordContentView.append(wordData.meaning.verb[i].synonyms.toString())

                }

                wordContentView.append("\n\n")
                i ++
            }
        }

        // Exclamation
        if(wordData.meaning.exclamation != null){
            wordContentView.append(getString(R.string.useExclamation))
            var i =0
            while(i < wordData.meaning.exclamation.size){

                if(wordData.meaning.exclamation[i].definition != null){
                    wordContentView.append("Defination: ")
                    wordContentView.append(wordData.meaning.exclamation[i].definition)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.exclamation[i].example != null){
                    wordContentView.append("Example: ")
                    wordContentView.append(wordData.meaning.exclamation[i].example)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.exclamation[i].synonyms != null){
                    wordContentView.append("Synonyms: ")
                    wordContentView.append(wordData.meaning.exclamation[i].synonyms.toString())
                }

                wordContentView.append("\n\n")
                i ++
            }
        }

        // Pronoun
        if(wordData.meaning.pronoun != null){
            wordContentView.append(getString(R.string.usePronoun))
            var i =0
            while(i < wordData.meaning.pronoun.size){

                if(wordData.meaning.pronoun[i].definition != null){
                    wordContentView.append("Defination: ")
                    wordContentView.append(wordData.meaning.pronoun[i].definition)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.pronoun[i].example != null){
                    wordContentView.append("Example: ")
                    wordContentView.append(wordData.meaning.pronoun[i].example)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.pronoun[i].synonyms != null){
                    wordContentView.append("Synonyms: ")
                    wordContentView.append(wordData.meaning.pronoun[i].synonyms.toString())
                }

                wordContentView.append("\n\n")
                i ++
            }
        }

        //Noun
        if(wordData.meaning.noun != null){
            wordContentView.append(getString(R.string.useNoun))
            var i =0
            while(i < wordData.meaning.noun.size){

                if(wordData.meaning.noun[i].definition != null){
                    wordContentView.append("Defination: ")
                    wordContentView.append(wordData.meaning.noun[i].definition)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.noun[i].example != null){
                    wordContentView.append("Example: ")
                    wordContentView.append(wordData.meaning.noun[i].example)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.noun[i].synonyms != null){
                    wordContentView.append("Synonyms: ")
                    wordContentView.append(wordData.meaning.noun[i].synonyms.toString())
                }

                wordContentView.append("\n\n")
                i ++
            }
        }

        // Conjunction
        if(wordData.meaning.conjunction != null){
            wordContentView.append(getString(R.string.useConjunction))
            var i =0
            while(i < wordData.meaning.conjunction.size){

                if(wordData.meaning.conjunction[i].definition != null){
                    wordContentView.append("Defination: ")
                    wordContentView.append(wordData.meaning.conjunction[i].definition)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.conjunction[i].example != null){
                    wordContentView.append("Example: ")
                    wordContentView.append(wordData.meaning.conjunction[i].example)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.conjunction[i].synonyms != null){
                    wordContentView.append("Synonyms: ")
                    wordContentView.append(wordData.meaning.conjunction[i].synonyms.toString())
                }

                wordContentView.append("\n\n")
                i ++
            }
        }

        //Determiner
        if(wordData.meaning.determiner != null){
            wordContentView.append(getString(R.string.useDeterminer))
            var i =0
            while(i < wordData.meaning.determiner.size){

                if(wordData.meaning.determiner[i].definition != null){
                    wordContentView.append("Defination: ")
                    wordContentView.append(wordData.meaning.determiner[i].definition)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.determiner[i].example != null){
                    wordContentView.append("Example: ")
                    wordContentView.append(wordData.meaning.determiner[i].example)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.determiner[i].synonyms != null){
                    wordContentView.append("Synonyms: ")
                    wordContentView.append(wordData.meaning.determiner[i].synonyms.toString())
                }

                wordContentView.append("\n\n")
                i ++
            }
        }

        // Abbreviation
        if(wordData.meaning.abbreviation != null){
            wordContentView.append(getString(R.string.useAbbreviation))
            var i =0
            while(i < wordData.meaning.abbreviation.size){

                if(wordData.meaning.abbreviation[i].definition != null){
                    wordContentView.append("Defination: ")
                    wordContentView.append(wordData.meaning.abbreviation[i].definition)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.abbreviation[i].example != null){
                    wordContentView.append("Example: ")
                    wordContentView.append(wordData.meaning.abbreviation[i].example)
                    wordContentView.append("\n")
                }


                if(wordData.meaning.abbreviation[i].synonyms != null){
                    wordContentView.append("Synonyms: ")
                    wordContentView.append(wordData.meaning.abbreviation[i].synonyms.toString())
                }
                wordContentView.append("\n")

                i ++
            }
        }


    }


}
