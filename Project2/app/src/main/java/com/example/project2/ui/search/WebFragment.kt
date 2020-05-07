package com.example.project2.ui.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.project2.R
import com.example.project2.ui.list.ShoppingListViewModel

class WebFragment : Fragment() {
    private lateinit var webView: WebView
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var shoppingListViewModel: ShoppingListViewModel
    var url: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        shoppingListViewModel = ViewModelProvider(requireActivity()).get(ShoppingListViewModel::class.java)

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

        url = searchViewModel.url

        Log.i("url", url)

        webView.loadUrl(url)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_item){
            shoppingListViewModel.replace = true
            shoppingListViewModel.replaceLabel = ""
            shoppingListViewModel.replaceUrl = webView.url

            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.action_navigation_web_to_navigation_add_item)
        }

        return super.onOptionsItemSelected(item)
    }
}