package com.example.egame

import android.nfc.NfcAdapter.EXTRA_DATA

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    private var positionNumber = 0
    var ready : Boolean = false
    var start : Boolean = false
    var startString : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        nextMainBtn.setOnClickListener {
            determinePosition()
            if (ready) {
                /*val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                 */
                PreferenceManager.getDefaultSharedPreferences(this).apply{
                     start = getBoolean("start", true)


                }
                /*
                if(start == true){
                    startString = "true"
                }else{
                    startString = "false"
                }
                */

                intent = Intent(this, MainActivity::class.java)
                intent.putExtra("EXTRA_DATA", start)
                startActivity(intent)
                PreferenceManager.getDefaultSharedPreferences(this).apply {
                    val editor = this.edit()
                    editor.putBoolean("start", false)
                        .apply()
                }

            }
            finish()
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
        editor.putString("position", positionText.text.toString())
            .putBoolean("start",true)
            .apply()



    }

}