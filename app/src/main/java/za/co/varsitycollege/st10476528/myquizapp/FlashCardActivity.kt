package za.co.varsitycollege.st10476528.myquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FlashCardActivity : AppCompatActivity() {

    //Defining the array which will store the questions
    var questions = arrayOf(
        "Question 1:"+"The world cup was hosted in Qatar in 2022",
        "Question 2:"+"The US dollar is more valuable than the South Africa Zar",
        "Question 3:"+"A person has 4 lungs",
        "Quesstion 4:"+"A person can breathe under water",
        "Question 5:"+"There is more ants than there is human"
    )

    var answers = booleanArrayOf(true, true, false, false, true)
    val userAnswer = BooleanArray(questions.size) {false}

    var score = 0
    var total = 0
    var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flash_card)





        val TrueButton = findViewById<Button>(R.id.TrueButton)
        val FalseButton = findViewById<Button>(R.id.FalseButton)
        val QuestionView = findViewById<TextView>(R.id.QuestionView)
        val NextButton = findViewById<Button>(R.id.NextButton)

         showQuestion()

        TrueButton.setOnClickListener() {
            checkAnswer(true)
        }

        FalseButton.setOnClickListener() {
            checkAnswer(false)
        }

        NextButton.setOnClickListener() {
            currentIndex++
        }

        if (currentIndex < questions.size) {
            showQuestion()
        } else{

        val intent = Intent(this, ScorePageActivity::class.java)
            intent.putExtra("score",score)
            intent.putExtra("total", questions.size)
            intent.putExtra("Questions",questions)
            intent.putExtra("Answer", answers)
            intent.putExtra("userActivity",userAnswer)
            startActivity(intent)

            finish()
        }


        }

     private fun showQuestion () {
        val NextButton = findViewById<Button>(R.id.NextButton)
        val TrueButton = findViewById<Button>(R.id.TrueButton)
        val FalseButton = findViewById<Button>(R.id.FalseButton)
        val QuestionView = findViewById<TextView>(R.id.QuestionView)

        QuestionView.text = questions[currentIndex]

        TrueButton.isEnabled = true
        FalseButton.isEnabled = false

        NextButton.isEnabled = false
    }

     private fun checkAnswer(studentAnswer:Boolean) {
        val TrueButton = findViewById<Button>(R.id.TrueButton)
        val FalseButton = findViewById<Button>(R.id.FalseButton)
        val NextButton = findViewById<Button>(R.id.NextButton)

        userAnswer[currentIndex] = studentAnswer
        if (studentAnswer==answers[currentIndex]) {

            Toast.makeText(this@FlashCardActivity,"The answer is correct", Toast.LENGTH_LONG).show()
            score++
        }
        else{
            Toast.makeText(this@FlashCardActivity, "The answer is incorrect", Toast.LENGTH_LONG).show()
        }
        TrueButton.isEnabled = true
        FalseButton.isEnabled = false
        NextButton.isEnabled = false
    }


    }
