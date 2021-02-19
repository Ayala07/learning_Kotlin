package com.example.learningkotlin

import android.app.Dialog
import android.content.*
import android.content.res.Configuration
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.dialog.*
import kotlin.random.Random

open class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences =this.getPreferences(MODE_PRIVATE) ?: return
        val themes = sharedPreferences.getInt("THEME", 2)
        when(themes){
            1 -> {
                //setTheme(R.style.Theme_LearningKotlinNight)
                theme.applyStyle(R.style.Theme_LearningKotlinNight,true)
                println("Noche bro")
            }
            2 -> {
                //setTheme(R.style.Theme_LearningKotlin)
                theme.applyStyle(R.style.Theme_LearningKotlin ,true)
                println("Dia bro")
            }
            else -> {
                //setTheme(R.style.Theme_LearningKotlin)
                theme.applyStyle(R.style.Theme_LearningKotlin,true)
            }
        }


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

        questImg.setOnClickListener {
            showImage()
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

    private fun showImage(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog)
        val webViewButton = dialog.findViewById<ImageView>(R.id.tavoImage)
        webViewButton.setOnClickListener {
            val intent = Intent(this, webView::class.java)
            startActivity(intent)
        }
        dialog.show()

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

        //ue,e,u sentence
        when {
            getWord.contains("ue") -> {
                getWord = getWord.replace("ue", "uem" ,false)
            }
            getWord.contains("e") -> {
                getWord = getWord.replace("e","em",false)
            }
            getWord.contains("u") -> {
                getWord = getWord.replace("u","um",false)
            }
        }

        //ui, i, u sentence
        when {
            getWord.contains("ui") -> {
                getWord = getWord.replace("i", "uim" ,false)
            }
            getWord.contains("i") -> {
                getWord = getWord.replace("i","im",false)
            }
            getWord.contains("u") -> {
                getWord = getWord.replace("u","um",false)
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