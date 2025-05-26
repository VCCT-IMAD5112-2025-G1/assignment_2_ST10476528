package za.co.varsitycollege.st10476528.myquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class FlashCardActivity : AppCompatActivity() {

    // Array of questions
    private val questions = arrayOf(
        "Question 1:" +" The world cup was hosted in Qatar in 2022",
        "Question 2:\" +\" The US dollar is more valuable than the South African ZAR",
        "Question 3: A person has 4 lungs",
        "Question 4: A person can breathe under water",
        "Question 5: There are more ants than there are humans"
    )

    private val answers = booleanArrayOf(true, true, false, false, true)
    private val userAnswers = BooleanArray(questions.size)

    private var score = 0
    private var currentIndex = 0

    private lateinit var questionView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flash_card)

        // Initialize UI components
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        questionView = findViewById(R.id.questionView)

        showQuestion()

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                showQuestion()
            } else {
                // End of quiz, go to score page
                val intent = Intent(this, ScorePageActivity::class.java).apply {
                    putExtra("score", score)
                    putExtra("total", questions.size)
                    putExtra("Questions", questions)
                    putExtra("Answer", answers)
                    putExtra("userActivity", userAnswers)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showQuestion() {
        questionView.text = questions[currentIndex]
        trueButton.isEnabled = true
        falseButton.isEnabled = true
        nextButton.isEnabled = false
    }

    private fun checkAnswer(studentAnswer: Boolean) {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        nextButton.isEnabled = true

        userAnswers[currentIndex] = studentAnswer

        if (studentAnswer == answers[currentIndex]) {
            Toast.makeText(this, "The answer is correct", Toast.LENGTH_SHORT).show()
            score++
        } else {
            Toast.makeText(this, "The answer is incorrect", Toast.LENGTH_SHORT).show()
        }
    }
}
