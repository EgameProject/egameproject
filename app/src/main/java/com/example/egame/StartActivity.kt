package com.example.egame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    private var positionNumber = 0
    var ready : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        nextMainBtn.setOnClickListener {
            determinePosition()
            if (ready) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
    fun determinePosition(){
        positionNumber = (1..2).random()
        imageView.setImageDrawable(null)
        imageView2.setImageDrawable(null)
        imageView3.setImageDrawable(null)
        if(positionNumber == 1) {
            positionImage.setImageResource(R.drawable.king)
            positionText.text = "KING"
        } else{
            positionImage.setImageResource(R.drawable.joker)
            positionText.text = "JOKER"
        }

        ready = true
    }

}