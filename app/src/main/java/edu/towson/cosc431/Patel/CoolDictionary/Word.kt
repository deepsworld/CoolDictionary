package edu.towson.cosc431.Patel.CoolDictionary

import com.google.gson.Gson
import java.util.*

data class Word(
        val word: String,
        val phonetic: String,
        val meaning: Meaning
){
    // uses GSON to serialize the data into JSON(JAVA SCRIPT OBJECT NOTATION)

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

data class Meaning(
        val exclamation: List<Exclamation>,
        val noun: List<Noun>,
        val verb: List<Verb>,
        val adjective: List<Adjective>,
        val adverb: List<Adverb>,
        val conjunction: List<Conjunction>,
        val determiner: List<Determiner>,
        val pronoun: List<Pronoun>,
        val abbreviation: List<Abbreviation>
)

data class Helper(
        val wordType: List<Adjective>
)

data class Abbreviation(
        val definition: String,
        val example: String,
        val synonyms: List<String>
)

data class Pronoun(
        val definition: String,
        val example: String,
        val synonyms: List<String>
)

data class Determiner(
        val definition: String,
        val example: String,
        val synonyms: List<String>
)

data class Conjunction(
        val definition: String,
        val example: String,
        val synonyms: List<String>
)

data class Adverb(
        val definition: String,
        val example: String,
        val synonyms: List<String>
)

data class Adjective(
        val definition: String,
        val example: String,
        val synonyms: List<String>
)

data class Exclamation(
        val definition: String,
        val example: String,
        val synonyms: List<String>

)

data class Noun(
        val definition: String,
        val example: String,
        val synonyms: List<String>
)

data class Verb(
        val definition: String,
        val example: String,
        val synonyms: List<String>
)



