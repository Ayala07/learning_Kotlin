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
            //upperAndLowerCasse(sentence)
            chemsificator(sentence)
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

    private fun chemsificator(sentence: String){

        var getWord  = sentence.replace("a","am",true)
        getWord  = getWord .replace("o","om",true)

        //ue,ui,e,i,u sentence
        when {
            getWord.contains("ue") -> {
                getWord  = getWord.replace("ue","uem", true)
            }
            getWord.contains("ui") -> {
                getWord = getWord.replace("ui", "uim", true)
            }
            getWord.contains("e") -> {
                getWord = getWord.replace("e", "em", true)
            }
            getWord.contains("i") -> {
                getWord = getWord.replace("i", "im", true)
            }
            getWord.contains("u") -> {
                getWord = getWord.replace("u", "um", true)
            }
        }

        println(getWord)
        resLbl.text = getWord
    }
}