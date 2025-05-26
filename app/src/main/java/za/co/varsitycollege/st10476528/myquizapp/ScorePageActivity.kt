package za.co.varsitycollege.st10476528.myquizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScorePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_page)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)
        val questions = intent.getStringArrayExtra("Questions")
        val answers = intent.getBooleanArrayExtra("Answer")
        val userAnswers = intent.getBooleanArrayExtra("userActivity") // Corrected key name

        val reviewTextView = findViewById<TextView>(R.id.ReviewTextView)
        val scoreView = findViewById<TextView>(R.id.ScoreView)
        val feedbackView = findViewById<TextView>(R.id.FeedView)
        val exitButton = findViewById<Button>(R.id.exitButton) // Make sure this ID exists
        val reviewButton = findViewById<Button>(R.id.RevButton)
        val scoreButton = findViewById<Button>(R.id.ScoreButton)

        scoreView.text = "Score: $score out of $total"

        feedbackView.text = if (score >= 3) {
            "Great Job!"
        } else {
            "Keep practicing"
        }

        exitButton.setOnClickListener {
            finish()
        }

        reviewButton.setOnClickListener {
            if (questions != null && answers != null && userAnswers != null) {
                val reviewText = StringBuilder()
                for (i in questions.indices) {
                    val q = questions[i]
                    val correct = answers[i]
                    val user = userAnswers[i]
                    val result = if (user == correct) "Correct" else "Incorrect"
                    reviewText.append("$q\nYour Answer: $user | Correct Answer: $correct -> $result\n\n")
                }
                reviewTextView.text = reviewText.toString()
            } else {
                reviewTextView.text = "No review data available."
            }
        }

        // Recommended: Handle insets properly in onCreate
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top,systemBars.right, systemBars.bottom)
            insets
        }
    }
}

