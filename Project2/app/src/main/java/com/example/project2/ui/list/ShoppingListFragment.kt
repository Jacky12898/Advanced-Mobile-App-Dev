package com.example.project2.ui.list

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.R
import com.example.project2.data.database.ShoppingList
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.shopping_list_item.view.*

class ShoppingListFragment : Fragment(), ShoppingListRecyclerAdapter.ShoppingListItemListener {
    private lateinit var shoppingListViewModel: ShoppingListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private lateinit var actionButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoppingListViewModel =
            ViewModelProvider(requireActivity()).get(ShoppingListViewModel::class.java)

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
        //registerForContextMenu(recyclerView)

        //shoppingListViewModel.deleteShoppingListItem(shoppingListItem)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menu.add(0, v.id, 0, "Visit")
        menu.add(0, v.id, 1, "Edit")
        menu.add(0, v.id, 2, "Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.title){
            //"Visit" -> shoppingListViewModel.deleteShoppingListItem()
            //"Edit" -> url = parseGoogleShoppingUrl(keywords, minPrice, maxPrice)
            //"Delete" -> url = "https://www.ebay.com"
        }

        return super.onContextItemSelected(item)
    }

}
