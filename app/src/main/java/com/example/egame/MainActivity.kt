package com.example.egame

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start.*

import kotlinx.coroutines.*

import java.lang.Thread.sleep
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var king :String= "KING"
    private var joker :String = "JOKER"
    private var playerPosition: String? = ""
    private var cpuPosition1: String? = ""
    private var cpuPosition2: String? = ""
    private var cpuPosition3: String? = ""
    private var cpuPosition4: String? = ""
    private var cpuPosition5: String? = ""
    private var playerNumber = 0
    private var cpuNumber = 0
    private var selectPlayerCard = ""
    private var selectCpuCard = ""
    private var draw : Boolean = false
    private var answered: Boolean = false
    private var roundLiset: Boolean = false
    private var start : Boolean = true
    private var roundCount = 1
    private var playerWinCount  = 0
    private var cpuWinCount = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        select1Btn.setOnClickListener {
            if(answered || draw) {
                showPlayerCard(1)
                judge(selectPlayerCard, selectCpuCard)
            }
        }

        select2Btn.setOnClickListener {
            if(answered || draw) {
                showPlayerCard(2)
                judge(selectPlayerCard, selectCpuCard)
            }
        }

        select3Btn.setOnClickListener {
            if(answered || draw) {
                showPlayerCard(3)
                judge(selectPlayerCard, selectCpuCard)
            }
        }

        select4Btn.setOnClickListener {
            if(answered || draw) {
                showPlayerCard(4)
                judge(selectPlayerCard, selectCpuCard)
            }
        }

        select5Btn.setOnClickListener {
            if(answered || draw) {
                showPlayerCard(5)
                judge(selectPlayerCard, selectCpuCard)
            }
        }
        nextBtn.setOnClickListener{
            if(roundLiset){
                setNextRound(playerPosition)
            }
        }



    }

    override fun onResume() {
        super.onResume()
        if(start) {

            PreferenceManager.getDefaultSharedPreferences(this).apply {
                val position = getString("position", "")
                var tag = "Egame"
                Log.d(tag, "position:" + position)
                start = intent.getBooleanExtra(EXTRA_DATA, true)
                //start = startString.toBoolean()
                Log.d(tag, "start:" + start)
                if (start) {
                    playerPosition = position
                    start = false
                    setCard()
                }


            }
        }


    }

    fun setCard() {
        playerNumber = (1..5).random()
        cpuNumber = (1..5).random()
        if (playerPosition == king) {
            when (playerNumber) {
                1 -> playerPosition1Image.setImageResource(R.drawable.king)
                2 -> playerPosition2Image.setImageResource(R.drawable.king)
                3 -> playerPosition3Image.setImageResource(R.drawable.king)
                4 -> playerPosition4Image.setImageResource(R.drawable.king)
                5 -> playerPosition5Image.setImageResource(R.drawable.king)
            }

            when (cpuNumber) {
                1 -> cpuPosition1 = "joker"
                2 -> cpuPosition2 = "joker"
                3 -> cpuPosition3 = "joker"
                4 -> cpuPosition4 = "joker"
                5 -> cpuPosition5 = "joker"
            }
        } else {
            when (playerNumber) {
                1 -> playerPosition1Image.setImageResource(R.drawable.joker)
                2 -> playerPosition2Image.setImageResource(R.drawable.joker)
                3 -> playerPosition3Image.setImageResource(R.drawable.joker)
                4 -> playerPosition4Image.setImageResource(R.drawable.joker)
                5 -> playerPosition5Image.setImageResource(R.drawable.joker)
            }

            when (cpuNumber) {
                1 -> cpuPosition1 = "king"
                2 -> cpuPosition2 = "king"
                3 -> cpuPosition3 = "king"
                4 -> cpuPosition4 = "king"
                5 -> cpuPosition5 = "king"
            }
        }
        answered = true

    }

    fun showPlayerCard(selectNumber : Int){
        answered = false
        deletePlayerCard(selectNumber)

        if(selectNumber == playerNumber){
            if(playerPosition == king){
                playerBattleCardImage.setImageResource(R.drawable.king)
                selectPlayerCard = king
            }
            else{
                playerBattleCardImage.setImageResource(R.drawable.joker)
                selectPlayerCard = joker

            }
        }
        else{
            playerBattleCardImage.setImageResource(R.drawable.civil)

        }

        showCpuCard()

    }

    fun showCpuCard(){
        var cpuSelectNumber = 0
        outer@ while(true) {
            cpuSelectNumber = (1..5).random()
            when (cpuSelectNumber) {
                1 -> if (cpuPosition1 != null) break@outer
                2 -> if (cpuPosition2 != null) break@outer
                3 -> if (cpuPosition3 != null) break@outer
                4 -> if (cpuPosition4 != null) break@outer
                5 -> if (cpuPosition5 != null) break@outer
            }
        }
        deleteCPUCard(cpuSelectNumber)

        if(cpuSelectNumber == cpuNumber && playerPosition == king){
            cpuBattleCardImage.setImageResource(R.drawable.joker)
            selectCpuCard = joker
        }
        else if(cpuSelectNumber == cpuNumber && playerPosition == joker){
            cpuBattleCardImage.setImageResource(R.drawable.king)
            selectCpuCard = king
        }
        else{
            cpuBattleCardImage.setImageResource(R.drawable.civil)
        }
    }

    fun deletePlayerCard(selectNumber : Int) {
        when (selectNumber) {
            1 -> playerPosition1Image.setImageDrawable(null)
            2 -> playerPosition2Image.setImageDrawable(null)
            3 -> playerPosition3Image.setImageDrawable(null)
            4 -> playerPosition4Image.setImageDrawable(null)
            5 -> playerPosition5Image.setImageDrawable(null)
        }
    }
    fun deleteCPUCard(cpuSelectNumber : Int){
        when(cpuSelectNumber) {
            1 -> cpuPosition1 = null
            2 -> cpuPosition2 = null
            3 -> cpuPosition3 = null
            4 -> cpuPosition4 = null
            5 -> cpuPosition5 = null
        }
    }


    fun judge(selectPlayerCard : String, selectCpuCard: String){
        if(playerPosition == king && selectPlayerCard == king && selectCpuCard == joker){
            cpuWinCount++
            cpuWinCountText.text = getString(R.string.cpuWinCount_text) + cpuWinCount
            draw = false
        } else if(playerPosition == king && selectPlayerCard == king && selectCpuCard != joker){
            playerWinCount++
            playerWinCountText.text = getString(R.string.playerWinCount_text) + playerWinCount
            draw = false
        } else if(playerPosition == king && selectPlayerCard != king && selectCpuCard == joker){
            playerWinCount++
            playerWinCountText.text = getString(R.string.playerWinCount_text) + playerWinCount
            draw = false
        } else if(playerPosition == joker && selectPlayerCard == joker && selectCpuCard == king){
            playerWinCount++
            playerWinCountText.text = getString(R.string.playerWinCount_text) + playerWinCount
            draw = false
        } else if(playerPosition == joker && selectPlayerCard == joker && selectCpuCard != king){
            cpuWinCount++
            cpuWinCountText.text = getString(R.string.cpuWinCount_text) + cpuWinCount
            draw = false
        } else if(playerPosition == joker && selectPlayerCard != joker && selectCpuCard == king){
            cpuWinCount++
            cpuWinCountText.text = getString(R.string.cpuWinCount_text) + cpuWinCount
            draw = false
        }else{
            draw = true
        }
        if(playerWinCount == 2 || cpuWinCount == 2 ){
            /*intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("playerWinCount", playerWinCount)
            intent.putExtra("playerPosition", playerPosition)
            startActivity(intent)
            var tag = "Egame"
            Log.d(tag, "position:" + 11111111111111111)*/

            GlobalScope.launch() {
               val text = GlobalScope.async {
                    sleep(2000)
                    if(playerWinCount == 2) "WIN"
                    else "LOSE"
                }
                resultShowText.text = text.await().toString()
                sleep(500)
                Log.d("egame", "resultShowText:" + resultShowText.text)
                finish()
            }

/*
            val sp = getPreferences(MODE_PRIVATE)
            sp.edit().clear().commit()

*/
            Log.d("egameeeeeeeeeee", "resultShowText:" + "finish前aaaaaaaaaaaaaaaaaaaaaaa")





        }else if(!draw){
            roundLiset = true
            draw = false
        }

    }
    fun setNextRound(playerPosition : String?){
        //thisつけたらplayerPositionのval can'tのエラー消えた
        if(playerPosition == king) this.playerPosition = joker
        else this.playerPosition = king
        roundCount++
        roundText.text = getString(R.string.round_text) + roundCount
        playerPosition1Image.setImageResource(R.drawable.civil)
        playerPosition2Image.setImageResource(R.drawable.civil)
        playerPosition3Image.setImageResource(R.drawable.civil)
        playerPosition4Image.setImageResource(R.drawable.civil)
        playerPosition5Image.setImageResource(R.drawable.civil)

        playerBattleCardImage.setImageDrawable(null)
        cpuBattleCardImage.setImageDrawable(null)

        setCard()
        roundLiset = false

    }


}