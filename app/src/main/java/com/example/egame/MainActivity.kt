package com.example.egame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var king :String= "KING"
    private var joker :String = "JOKER"
    private var playerPosition: String = king
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
    private var roundCount = 1
    private var playerWinCount  = 0
    private var cpuWinCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        select1Btn.setOnClickListener {
            if(answered) {
                showPlayerCard(1)
                judge(selectPlayerCard, selectCpuCard)
            }
        }

        select2Btn.setOnClickListener {
            if(answered) {
                showPlayerCard(2)
                judge(selectPlayerCard, selectCpuCard)
            }
        }

        select3Btn.setOnClickListener {
            if(answered) {
                showPlayerCard(3)
                judge(selectPlayerCard, selectCpuCard)
            }
        }

        select4Btn.setOnClickListener {
            if(answered) {
                showPlayerCard(4)
                judge(selectPlayerCard, selectCpuCard)
            }
        }

        select5Btn.setOnClickListener {
            if(answered) {
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
        setCard()

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
        } else if(playerPosition == king && selectPlayerCard == king && selectCpuCard != joker){
            playerWinCount++
            playerWinCountText.text = getString(R.string.playerWinCount_text) + playerWinCount
        } else if(playerPosition == king && selectPlayerCard != king && selectCpuCard == joker){
            playerWinCount++
            playerWinCountText.text = getString(R.string.playerWinCount_text) + playerWinCount
        } else if(playerPosition == joker && selectPlayerCard == joker && selectCpuCard == king){
            playerWinCount++
            playerWinCountText.text = getString(R.string.playerWinCount_text) + playerWinCount
        } else if(playerPosition == joker && selectPlayerCard == joker && selectCpuCard != king){
            cpuWinCount++
            cpuWinCountText.text = getString(R.string.cpuWinCount_text) + cpuWinCount
        } else if(playerPosition == joker && selectPlayerCard != joker && selectCpuCard == king){
            cpuWinCount++
            cpuWinCountText.text = getString(R.string.cpuWinCount_text) + cpuWinCount
        }else{
            draw = true
        }
        if(playerWinCount ==2 || cpuWinCount == 2 ){
            //画面遷移
        }else{
            roundLiset = true
            draw = false
        }

    }
    fun setNextRound(playerPosition : String){
        //thisつけたらplayerPositionのval can'tのエラー消えた
        if(playerPosition == king) this.playerPosition = joker
        else this.playerPosition = king
        roundCount++
        roundText.text = getString(R.string.round_text) + roundCount
        setCard()
        roundLiset = false

    }


}