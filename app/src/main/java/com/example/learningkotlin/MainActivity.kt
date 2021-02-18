package com.example.learningkotlin

import android.content.*
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences =this.getPreferences(MODE_PRIVATE) ?: return
        val themes = sharedPreferences.getInt("THEME", 2)
        when(themes){
            1 -> {
                setTheme(R.style.Theme_LearningKotlinNight)
                println("Noche bro")
            }
            2 -> {
                setTheme(R.style.myLigthTheme)
                println("Dia bro")
            }
            else -> setTheme(R.style.myLigthTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        inputTxt.addTextChangedListener{
            val sentence = inputTxt.text.toString()
            when{
                rbdSeparated.isChecked -> separated(sentence)
                rbdCapitalLowerLetter.isChecked -> upperAndLowerCasse(sentence)
                rbdChems.isChecked -> chemsificator(sentence)
            }
        }

        rbdSeparated.setOnClickListener{
            separated(inputTxt.text.toString())
        }

        rbdCapitalLowerLetter.setOnClickListener{
            upperAndLowerCasse(inputTxt.text.toString())
        }

        rbdChems.setOnClickListener {
            chemsificator(inputTxt.text.toString())
        }

        copyTextBtn.setOnClickListener {
            copyText()
        }

        themeSwitch.setOnClickListener {
            if (themeSwitch.isChecked){
                sharedPreferences.edit().putInt("THEME",1).apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()

            } else {
                sharedPreferences.edit().putInt("THEME",2).apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            }
        }

        themeVerification(themes)

    }

    private fun themeVerification(dataVerification:Int){
        when(dataVerification){
            1 -> {
                themeSwitch.isChecked = true
                val imageSource = resources.getIdentifier("@drawable/ic_baseline_wb_sunny_24", null,packageName)
                val  image = ContextCompat.getDrawable(this, imageSource)
                themeSwitch.thumbDrawable = image
            }
            2 -> {
                themeSwitch.isChecked = false
                val imageSource = resources.getIdentifier("@drawable/ic_baseline_moon_2_24", null,packageName)
                val  image = ContextCompat.getDrawable(this, imageSource)
                themeSwitch.thumbDrawable = image
            }
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

    private fun copyText(){
        val myClipManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myCLipData = ClipData.newPlainText("CliP TavO", resLbl.text)

        myClipManager.setPrimaryClip(myCLipData)

    }


}