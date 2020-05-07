package com.example.project2.ui.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.project2.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchButton: Button
    private lateinit var navController: NavController
    lateinit var url: String

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

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

        when(site){
            "Amazon" -> url = parseAmazonUrl(keywords, minPrice, maxPrice)
            "Google Shopping" -> url = parseGoogleShoppingUrl(keywords, minPrice, maxPrice)
            "Ebay" -> url = parseEbayUrl(keywords, minPrice, maxPrice)
        }

        Log.i("Data", url);

        if(keywords != null && keywords != ""){
            searchViewModel.url = url
            navController.navigate(R.id.action_navigation_search_to_navigation_web)
        }
    }

    private fun parseAmazonUrl(keywords: String, minPrice: String, maxPrice: String) : String{
        var url = "https://www.amazon.com/s?k=$keywords&rh=p_36%3A"
        if(minPrice != null && minPrice != "")
            url += "$minPrice" + "00"

        if(maxPrice != null && maxPrice != "")
            url += "-" + maxPrice + "00"

        return url
    }

    private fun parseGoogleShoppingUrl(keywords: String, minPrice: String, maxPrice: String) : String{
        var url = "https://www.google.com/search?q=$keywords&tbm=shop&tbs=price:1,"
        if(minPrice != null && minPrice != "")
            url += ",ppr_min:$minPrice"

        if(maxPrice != null && maxPrice != "")
            url += ",ppr_max:$maxPrice"

        return url
    }

    private fun parseEbayUrl(keywords: String, minPrice: String, maxPrice: String) : String{
        var url = "https://www.ebay.com/sch/i.html?_from=R40&_nkw=$keywords"
        if(minPrice != null && minPrice != "")
            url += "&_udlo=$minPrice"

        if(maxPrice != null && maxPrice != "")
            url += "&_udhi=$maxPrice"

        return url
    }
}
