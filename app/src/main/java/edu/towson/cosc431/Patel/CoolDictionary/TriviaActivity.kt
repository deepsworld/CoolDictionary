package edu.towson.cosc431.Patel.CoolDictionary

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_trivia.*
import java.util.*

class TriviaActivity : AppCompatActivity() {

    val random = Random() // Generate random number
    var quesList: MutableList<Trivia> = mutableListOf() // List of object Trivia
    var randQues: Int = random.nextInt(5) // Randomly choose list object

    fun populateQuesList() { // Populate list
        quesList.add(Trivia("Which word in the English language means all of the following: An institution for the care of people, a shelter, and protection from extradition.",
                "Thought",
                "Asylum",
                "Behavior",
                "Refugee",
                "Asylum"))
        quesList.add(Trivia(" This word is defined as 'having little protection'.",
                "Endurance",
                "Charismatic",
                "Defenseless",
                "Deprive",
                "Defenseless"))

        quesList.add(Trivia("Which of the following is a palindrome?",
                "Police",
                "London",
                "Tomato",
                "Noon",
                "Noon"))

        quesList.add(Trivia("Words that express excitement or emotion like Hey!, Oh!, Yay!, or Ouch! are what part of speech?",
                "Interjection",
                "Conjunction",
                "Onomatopoeia",
                "Metaphor",
                "Interjection"))

        quesList.add(Trivia("It's an eight letter word: Good students have it and nice banks give it. What is it?",
                "Quantize",
                "Interest",
                "Maximize",
                "Equalize",
                "Interest"))


    }

    fun displayTrivia(trivia: Trivia) { // Display trivia on screen
        triviaQ.text = trivia.question
        option1.text = trivia.option1
        option2.text = trivia.option2
        option3.text = trivia.option3
        option4.text = trivia.option4

    }


    fun onRadioButtonClicked(view: View) {

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("You are damn right")
                .setItems(arrayOf("Back"), object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, idx: Int) {
                        dialog?.dismiss()
                    }

                })
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            // Check which radio button was clicked
            print("hello")
            when (view.getId()) {
                R.id.option1 ->
                    if (option1.text.equals(quesList[randQues].correctAns)) {
                        output.append("   You are damn right")
                        dialogBuilder.create().show()
                    }
                    else{
                        output.append("   Incorrect")
                    }


                R.id.option2 ->
                    if (option2.text.equals(quesList[randQues].correctAns)) {
                        output.append("   You are damn right")
                        dialogBuilder.create().show()
                    }
                    else{
                        output.append("   Incorrect")
                    }

                R.id.option3 ->
                    if (option3.text.equals(quesList[randQues].correctAns)) {
                        output.append("   You are damn right")
                        dialogBuilder.create().show()
                    }
                    else{
                        output.append("   Incorrect")
                    }

                R.id.option4 ->
                    if (option4.text.equals(quesList[randQues].correctAns)) {
                        output.append("   You are damn right")  // Ninjas rule
                        dialogBuilder.create().show()
                    }
                    else{
                        output.append("   Incorrect")
                    }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)

        onRadioButtonClicked(options)

        // Populate list
        populateQuesList()

        // Display trivia
        displayTrivia(quesList[randQues])

    }
}