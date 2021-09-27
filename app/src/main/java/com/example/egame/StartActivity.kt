package com.example.egame

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.coroutines.*


class StartActivity : AppCompatActivity() {
    private var positionNumber = 0
    var ready : Boolean = false
    var start : Boolean = false
    var startString : String = ""
    var flag : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        nextMainBtn.setOnClickListener {
            determinePosition()
            if (ready) {
                ready = false

                /*val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                 */
                /*
                PreferenceManager.getDefaultSharedPreferences(this).apply{
                     start = getBoolean("start", true)


                }

                 */
                /*
                if(start == true){
                    startString = "true"
                }else{
                    startString = "false"
                }
                */

                start = true
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("EXTRA_DATA", start)

                //Thread.sleep(3000)

                start = false
                val job = GlobalScope.launch() {
                    withContext(Dispatchers.Default){
                        Thread.sleep(1000)
                        startActivity(intent)
                    }

                }


/*
                PreferenceManager.getDefaultSharedPreferences(this).apply {
                    val editor = this.edit()
                    editor.putBoolean("start", false)
                        .apply()
                }
*/
            }

            //finish()
        }
    }

    override fun onResume(){
        super.onResume()
        if(!start){
            reset()
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
        saveData()


        ready = true
    }

    fun saveData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        GlobalScope.launch(){
            editor.putString("position", positionText.text.toString())
                .apply()
        }

    }

    fun reset(){
        positionImage.setImageDrawable(null)
        positionText.text = ""
        imageView.setImageResource(R.drawable.king)
        imageView2.setImageResource(R.drawable.joker)
        imageView3.setImageResource(R.drawable.civil)

    }

}