package com.example.project2.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.R
import com.example.project2.data.database.ShoppingList
import com.example.project2.ui.search.SearchViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingListFragment : Fragment(), ShoppingListRecyclerAdapter.ShoppingListItemListener {
    private lateinit var shoppingListViewModel: ShoppingListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private lateinit var actionButton: FloatingActionButton
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoppingListViewModel = ViewModelProvider(requireActivity()).get(ShoppingListViewModel::class.java)
        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val root = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)

        shoppingListViewModel.shoppingList.observe(viewLifecycleOwner, Observer {
            val adapter = ShoppingListRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
        })

        actionButton = root.findViewById(R.id.fab)
        actionButton.setOnClickListener{
            navController.navigate(R.id.action_navigation_list_to_navigation_add_item)
        }

        return root
    }

    override fun onShoppingListItemClick(shoppingListItem: ShoppingList) {
        //shoppingListViewModel.deleteShoppingListItem(shoppingListItem)
    }

    override fun visit(shoppingListItem: ShoppingList){
        searchViewModel.url = shoppingListItem.url
        navController.navigate(R.id.action_navigation_list_to_navigation_web)
    }

    override fun edit(shoppingListItem: ShoppingList){
        navController.navigate(R.id.action_navigation_list_to_navigation_add_item)
        shoppingListViewModel.replace = true
        shoppingListViewModel.replaceLabel = shoppingListItem.label
        shoppingListViewModel.replaceUrl = shoppingListItem.url
        shoppingListViewModel.deleteShoppingListItem(shoppingListItem)
    }

    override fun delete(shoppingListItem: ShoppingList){
        shoppingListViewModel.deleteShoppingListItem(shoppingListItem)
    }
}