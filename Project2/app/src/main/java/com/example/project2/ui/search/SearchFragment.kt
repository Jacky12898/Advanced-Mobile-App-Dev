package com.example.project2.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.project2.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchButton: Button
    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        searchButton = root.findViewById(R.id.searchButton)

        searchButton.setOnClickListener {
            search()
        }
        return root
    }

    private fun search(){
        val keywords = keywordsText.text.toString();
        val minPrice = minPriceText.text.toString();
        val maxPrice = maxPriceText.text.toString();
        val site = sitesSpinner.selectedItem.toString();

        var url = ""
        when(site){
            "Amazon" -> url = parseAmazonUrl(keywords, minPrice, maxPrice)
            "Google Shopping" -> url = parseGoogleShoppingUrl(keywords, minPrice, maxPrice)
            "Ebay" -> url = "https://www.ebay.com"
        }

        Log.i("Data", url);

        if(keywords != null && keywords != ""){
            navController.navigate(R.id.action_navigation_search_to_navigation_web)
            searchViewModel.url.value = url
        }
    }

    private fun parseAmazonUrl(keywords: String, minPrice: String, maxPrice: String) : String{
        var url = "https://www.amazon.com/s?k=$keywords"
        if((minPrice != null && minPrice != "") || (maxPrice != null && maxPrice != ""))
            url += "&rh=p_36%3A$minPrice" + "00-$maxPrice" + "00"

        return url
    }

    private fun parseGoogleShoppingUrl(keywords: String, minPrice: String, maxPrice: String) : String{
        var url = "https://www.ebay.com/sch/i.html?_from=R40&_nkw=$keywords"
        if(minPrice != null && minPrice != "")
            url += "&_udlo=$minPrice"

        if(maxPrice != null && maxPrice != "")
            url += "&_udhi=$maxPrice"

        return url
    }
}
