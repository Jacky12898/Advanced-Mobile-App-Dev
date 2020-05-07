package com.example.project2.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.project2.R
import com.example.project2.data.database.ShoppingList
import com.google.android.material.snackbar.Snackbar

class AddListItemFragment: Fragment(){
    private lateinit var navController: NavController
    private lateinit var saveButton: Button
    private lateinit var shoppingListViewModel: ShoppingListViewModel
    private lateinit var label: EditText
    private lateinit var url: EditText
    private lateinit var newListItem: ShoppingList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoppingListViewModel = ViewModelProvider(requireActivity()).get(ShoppingListViewModel::class.java)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val root = inflater.inflate(R.layout.fragment_add_item, container, false)

        label = root.findViewById(R.id.labelEditText)
        url = root.findViewById(R.id.urlEditText)
        saveButton = root.findViewById(R.id.saveButton)

        if(shoppingListViewModel.replace){
            label.setText(shoppingListViewModel.replaceLabel)
            url.setText(shoppingListViewModel.replaceUrl)
            shoppingListViewModel.replace = false
        }

        saveButton.setOnClickListener{

            if(label.text.toString() == "" || url.text.toString() == "") {
                val snack = Snackbar.make(it,"Fill out all fields", Snackbar.LENGTH_LONG)
                snack.show()
            }

            else if(!url.text.contains("https://")){
                val snack = Snackbar.make(it,"Enter valid url with https://", Snackbar.LENGTH_LONG)
                snack.show()
            }

            else{
                newListItem = ShoppingList((0..1000).random(), label.text.toString(), url.text.toString())
                shoppingListViewModel.addShoppingListItem(newListItem)
                navController.navigate(R.id.action_navigation_add_item_to_navigation_list)
            }
        }

        return root
    }
}