package com.example.project2.ui.search

import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.project2.R
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WebFragment : Fragment(){
    private lateinit var webView: WebView
    private lateinit var searchViewModel: SearchViewModel
    var url: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_web, container, false)

        webView = root.findViewById(R.id.webView)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        //webView.loadUrl(uri)

        searchViewModel.url.observe(viewLifecycleOwner, Observer<String>{
            Log.i("url", it)
            url = it
        })

        Log.i("url", searchViewModel.url.value)

        webView.loadUrl(url)
        return root
    }

    fun searchOnWeb(url: String){
        val web = webView
        Log.i("url", url)
        web.loadUrl(url)
    }
}