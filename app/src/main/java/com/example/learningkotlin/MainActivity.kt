package com.example.learningkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        proccessButton.setOnClickListener {
            val sentence = inputTxt.text.toString()
            separated(sentence)
        }
    }

    private fun separated(sentence:String){
        var response = ""
        for (i in sentence){
            response += "$i "
        }

        resLbl.text = response
    }
}