package com.example.personalrepl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Web : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawer: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_web)
        this.setNavigationViewListener()

        drawer = this.findViewById(R.id.drawer2)
        toggle = ActionBarDrawerToggle(this, drawer, R.string.nav_open, R.string.nav_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val asset: String = "file:///android_asset/web.html"
        val webView: WebView = this.findViewById(R.id.webview2)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(asset)
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.webChromeClient = WebChromeClient()
    }

    fun setNavigationViewListener() {
        val navigationView: NavigationView = findViewById(R.id.web_nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.programming -> {
                this.startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.web -> {
                this.startActivity(Intent(this, Web::class.java))
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}