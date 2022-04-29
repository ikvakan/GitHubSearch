package hr.ikvakan.git_hub_search

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

const val ITEM_URL=" hr.algebra.hacked.item_url"
class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        setupWebView()
        setupWebSettings()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun setupWebSettings() {
        val webSettings=webView.settings
        webSettings.javaScriptEnabled=true
    }

    private fun setupWebView() {
        webView=findViewById(R.id.webView )
        webView.webViewClient= WebViewClient()
        val url = intent.getStringExtra(ITEM_URL)

        if (!url.isNullOrBlank() ) {
            webView.loadUrl(url.toString())
        }else{
            webView.loadUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
        }


    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}