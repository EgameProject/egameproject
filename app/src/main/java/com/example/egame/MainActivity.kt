package com.example.egame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val king = "KING"
    private val joker = "JOKER"
    private var playerPosition: String = king
    private var cpuPosition1: String = ""
    private var cpuPosition2: String = ""
    private var cpuPosition3: String = ""
    private var cpuPosition4: String = ""
    private var cpuPosition5: String = ""
    private var playerNumber = 0
    private var cpuNumber = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        select1Btn.setOnClickListener {
            show(1)
            //judge()
        }

        select2Btn.setOnClickListener {
            show(2)
            //judge()
        }

        select3Btn.setOnClickListener {
            show(3)
            //judge()
        }

        select4Btn.setOnClickListener {
            show(4)
            //judge()
        }

        select5Btn.setOnClickListener {
            show(5)
            //judge()
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
    }

    fun show(selectNumber : Int){

        deletePlayerCard(selectNumber)

        if(selectNumber == playerNumber){
            if(playerPosition == king){
                playerBattleCardImage.setImageResource(R.drawable.king)
            }
            else{
                playerBattleCardImage.setImageResource(R.drawable.joker)
            }
        }
        else{
            playerBattleCardImage.setImageResource(R.drawable.civil)
        }

        showCpuCard()

    }

    fun showCpuCard(){
        var cpuSelectNumber = 0
        cpuSelectNumber = (1..5).random()

        if(cpuSelectNumber == cpuNumber && playerPosition == king){
            cpuBattleCardImage.setImageResource(R.drawable.joker)
        }
        else if(cpuSelectNumber == cpuNumber && playerPosition == joker){
            cpuBattleCardImage.setImageResource(R.drawable.king)
        }
        else{
            cpuBattleCardImage.setImageResource(R.drawable.civil)
        }
    }

    fun deletePlayerCard(selectNumber : Int){
        when(selectNumber) {
            1 -> playerPosition1Image.setImageDrawable(null)
            2 -> playerPosition2Image.setImageDrawable(null)
            3 -> playerPosition3Image.setImageDrawable(null)
            4 -> playerPosition4Image.setImageDrawable(null)
            5 -> playerPosition5Image.setImageDrawable(null)
        }
    }






}