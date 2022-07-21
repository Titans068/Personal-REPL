package com.example.personalrepl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        val asset: String = "file:///android_asset/index.html"
        val webView: WebView = this.findViewById(R.id.webview)
        webView.settings.javaScriptEnabled = true;
        webView.loadUrl(asset)
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = true
        webView.webChromeClient = WebChromeClient()
    }
}