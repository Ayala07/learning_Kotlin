package com.example.learningkotlin

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_web_view.*

class webView : AppCompatActivity() {

    //Constantes
    private val BASE_URI = "https://www.mindat.org/feature-3583912.html"
    lateinit var TITLE:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        //Refresh

        swipeRefresh.setOnRefreshListener {
            myWebView.reload()
        }

        //webView

        myWebView.webChromeClient = object : WebChromeClient() {

        }

        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                swipeRefresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                TITLE = view?.title.toString()
                println("Titulo $TITLE")
                customActionBar(TITLE)

                swipeRefresh.isRefreshing = false
            }
        }

        val settings = myWebView.settings
        settings.javaScriptEnabled = true

        myWebView.loadUrl(BASE_URI)
    }

    private fun customActionBar(title:String){
        val actionbar = supportActionBar
        actionbar?.title = title
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        if (myWebView.canGoBack()){
            myWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}