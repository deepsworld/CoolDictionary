package edu.towson.cosc431.Patel.CoolDictionary

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

// Database constants
object WordContract { // the whole database
    object WordEntry { // one table
        const val TABLE_NAME = "word_history"
        const val COLUMN_NAME_TITLE = "word_title"
        const val COLUMN_NAME_FAVORITE = "word_favorite"
    }
}

// Interface for methods
interface IDatabase {
    fun addWord(word: WordDataClass)
    fun getWords(): List<WordDataClass>
    fun getFavorite(): List<WordDataClass>
    fun updateWord(word: WordDataClass)
}

// SQLLite create entry
private const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${WordContract.WordEntry.TABLE_NAME} (" +
                "${WordContract.WordEntry.COLUMN_NAME_TITLE} TEXT, " +
                "${WordContract.WordEntry.COLUMN_NAME_FAVORITE} INTEGER DEFAULT 0)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${WordContract.WordEntry.TABLE_NAME}"

class WordDatabase(ctx: Context) : IDatabase {

    override fun getFavorite(): List<WordDataClass> {
        // 1. select * from todos
        val cursor = db
                .rawQuery(
                        "SELECT DISTINCT * FROM ${WordContract.WordEntry.TABLE_NAME} "
                                // where not deleted
                                + "WHERE ${WordContract.WordEntry.COLUMN_NAME_FAVORITE}=1"
                        , null
                )
        val result = mutableListOf<WordDataClass>()

        // 2. process the cursor
        while(cursor.moveToNext()) {

            val isFavoriteAsInt = cursor
                    .getInt(
                            cursor.getColumnIndex(WordContract.WordEntry.COLUMN_NAME_FAVORITE)
                    )

            val word_string = cursor
                    .getString(
                            cursor.getColumnIndex(WordContract.WordEntry.COLUMN_NAME_TITLE)
                    )

            val word = WordDataClass(
                    word = word_string,
                    isFavorite = isFavoriteAsInt == 1
            )
            result.add(word)
        }

        // 2.5 close the cursor
        cursor.close()

        // 3. return the list
        return result

    }

    override fun addWord(word: WordDataClass) {
        //1. Convert the Todos to ContentValues
        val contentValues = toContentValues(word)

        //2. insert into the db
        db.insert(
                WordContract.WordEntry.TABLE_NAME, // table name
                null, // nullColumnHack -- look it up!
                contentValues
        )

    }
    override fun updateWord(word: WordDataClass) {
        // 1. Convert to contentValues
        val contentValues = toContentValues(word)

        // 2. save to the db
        db.update(
               WordContract.WordEntry.TABLE_NAME,
                contentValues,
                "${WordContract.WordEntry.COLUMN_NAME_TITLE} = ?",
                arrayOf(word.word)
        )
    }

    override fun getWords(): List<WordDataClass> {
        // 1. select * from todos
        val cursor = db
                .rawQuery(
                        "SELECT * FROM ${WordContract.WordEntry.TABLE_NAME} "
                        , null
                )
        val result = mutableListOf<WordDataClass>()

        // 2. process the cursor
        while(cursor.moveToNext()) {

            val isFavoriteAsInt = cursor
                    .getInt(
                            cursor.getColumnIndex(WordContract.WordEntry.COLUMN_NAME_FAVORITE)
                    )

            val word_string = cursor
                    .getString(
                            cursor.getColumnIndex(WordContract.WordEntry.COLUMN_NAME_TITLE)
                    )

            var word = WordDataClass(
                    word = word_string,
                    isFavorite = isFavoriteAsInt == 1

            )
            result.add(word)
        }

        // 2.5 close the cursor
        cursor.close()

        // 3. return the list
        return result

    }

    private fun toContentValues(word: WordDataClass): ContentValues {
        val cv = ContentValues()

        cv.put(WordContract.WordEntry.COLUMN_NAME_TITLE, word.word)

        val isFavoriteAsInt = when(word.isFavorite) {
            true -> 1
            false -> 0
        }
        cv.put(WordContract.WordEntry.COLUMN_NAME_FAVORITE, isFavoriteAsInt)
        return cv
    }

    private val db: SQLiteDatabase

    init {
        db = WordDbHelper(ctx).writableDatabase
    }

    class WordDbHelper(ctx: Context) : SQLiteOpenHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION)
    {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(SQL_CREATE_ENTRIES)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL(SQL_DELETE_ENTRIES)
            onCreate(db)
        }

        companion object {
            val DATABASE_NAME = "word.db"
            val DATABASE_VERSION = 1
        }
    }


}