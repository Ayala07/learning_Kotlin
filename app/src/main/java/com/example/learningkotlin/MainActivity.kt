package com.example.learningkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        proccessButton.setOnClickListener {
            val sentence = inputTxt.text.toString()
            //separated(sentence)
            upperAndLowerCasse(sentence)
        }
    }

    private fun separated(sentence:String){
        var response = ""
        for (i in sentence){
            response += "$i "
        }

        resLbl.text = response
    }

    private fun upperAndLowerCasse(sentence: String){
        var response = ""
        for (i in sentence){
            val random = Random.nextBoolean()
            response += if (random){
                i.toUpperCase()
            } else {
                i.toLowerCase()
            }
        }

        resLbl.text = response
    }
}