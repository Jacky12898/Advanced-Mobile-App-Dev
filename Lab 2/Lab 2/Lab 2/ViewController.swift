//
//  ViewController.swift
//  Lab 2
//
//  Created by Jacky Cheung on 2/18/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class ViewController: UITableViewController {
    
    var groceriesList = [String]()
    var groceriesDataController = GroceriesDataController()
    
    var searchController = UISearchController()

    override func viewDidLoad() {
        super.viewDidLoad()
        do{
            try groceriesDataController.loadData()
            groceriesList = groceriesDataController.getGroceries()
        }
        
        catch{
            print(error)
        }
        
        do {
            try groceriesDataController.loadData()
            groceriesList = groceriesDataController.getGroceries()
            
            let resultsController = SearchResultsControllerTableViewController()
            resultsController.allDates = groceriesList
            
            searchController = UISearchController(searchResultsController: resultsController)
            searchController.searchBar.placeholder = "Filter"
            searchController.searchBar.sizeToFit()
            
            tableView.tableHeaderView = searchController.searchBar
            searchController.searchResultsUpdater = resultsController
            
        } catch {
            print(error)
        }
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
           return groceriesList.count
    }
       
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
           let cell = tableView.dequeueReusableCell(withIdentifier: "GroceryCell", for: indexPath)
           
           cell.textLabel?.text = groceriesList[indexPath.row]
           
           return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "DateSegue" {
            
            let detailVC = segue.destination as! DetailViewController
            let indexPath = tableView.indexPath(for: sender as! UITableViewCell)
            
            if let selection = indexPath?.row {
                detailVC.selectedList = selection
                detailVC.title = groceriesList[selection]
                detailVC.groceriesDataController = groceriesDataController
            }
        }
    }
}

