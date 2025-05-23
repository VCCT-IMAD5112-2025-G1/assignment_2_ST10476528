package za.co.varsitycollege.st10476528.myquizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.utilities.Score
import org.w3c.dom.Text

class ScorePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_page)

        val score = intent.getIntExtra("score",0)
        val total = intent.getIntExtra("total",0)
        val questions = intent.getStringArrayExtra("Questions")
        val answers = intent.getBooleanArrayExtra("Answer")
        val userAnswer = intent.getBooleanArrayExtra("userAnswer")

        val ReviewTextView = findViewById<TextView>(R.id.ReviewTextView)
        val ScoreView = findViewById<TextView>(R.id.ScoreView)
        val FeedView = findViewById<TextView>(R.id.FeedView)
        val exitButton = findViewById<Button>(R.id.exitButton)
        val RevButton = findViewById<Button>(R.id.RevButton)
        val ScoreButton = findViewById<Button>(R.id.ScoreButton)

        ScoreView.text = "Score: $score out of $total"

        if (score >= 3) {
            FeedView.text = "Great Job!"
        }else{
            FeedView.text = "Keep practising"
        }

        exitButton.setOnClickListener {
            finishActivity()
        }

        RevButton.setOnClickListener {
            if (questions!= null && answers!= null && userAnswer!= null){
                val ReviewTextView=StringBuilder()
                for (i in questions.indices)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}