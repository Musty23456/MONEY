package com.example.manajankudi

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.manajankudi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webView: WebView = binding.webView
        val settings: WebSettings = webView.settings

        // Ana bukatar JavaScript domin app din ya yi aiki.
        settings.javaScriptEnabled = true

        // Ana bukatar DOM storage domin localStorage (ajiye bayanan kudi) ya yi aiki.
        settings.domStorageEnabled = true

        // Loda index.html daga cikin assets na app din (babu bukatar intanet).
        webView.loadUrl("file:///android_asset/index.html")

        // Idan ana danna back button, koma baya a cikin WebView maimakon rufe app din kai tsaye.
        onBackPressedCallback()
    }

    private fun onBackPressedCallback() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
}
