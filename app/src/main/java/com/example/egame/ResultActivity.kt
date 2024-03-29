package com.example.egame

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.activity_start.positionImage

class ResultActivity : AppCompatActivity() {
    var playerPosition : String? = ""
    private var playerWinCount: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var tag = "Egame"
        //Log.d(tag, "position:　ResultActivity")
        playerWinCount = intent.getIntExtra("playerWinCount",0)

        PreferenceManager.getDefaultSharedPreferences(this).apply {
            playerPosition = getString("position", "")
        }
        showResult(playerWinCount, playerPosition)
        setContentView(R.layout.activity_result)



        backBtn.setOnClickListener{
            //finish()
            intent =  Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        /*playerWinCount = intent.getIntExtra("playerWinCount",0)

        PreferenceManager.getDefaultSharedPreferences(this).apply {
            playerPosition = getString("position", "")
        }*/

        //showResult(playerWinCount, playerPosition)



    }

    fun showResult(playerWinCount : Int, playerPosition : String?) {
        if (playerWinCount == 2 && playerPosition == "KING") {
            positionText.text = "WINNER"
            resultCardImage.setImageResource(R.drawable.king)
        } else if (playerWinCount == 2 && playerPosition == "JOKER") {
            positionText.text = "WINNER"
            resultCardImage.setImageResource(R.drawable.joker)
        } else if (playerWinCount != 2 && playerPosition == "KING") {
            positionText.text = "LOOSER"
            resultCardImage.setImageResource(R.drawable.king)
        } else {
            positionText.text = "LOOSER"
            resultCardImage.setImageResource(R.drawable.joker)
        }
    }
}