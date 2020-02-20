//
//  SearchResultsControllerTableViewController.swift
//  Lab 2
//
//  Created by Jacky Cheung on 2/19/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class SearchResultsControllerTableViewController: UITableViewController, UISearchResultsUpdating {

    var allDates = [String]()
    var filteredDates = [String]()

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "GroceryCell")
    }

    // MARK: - Table view data source

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return filteredDates.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "GroceryCell", for: indexPath)
        cell.textLabel?.text = filteredDates[indexPath.row]
        return cell
    }
    
    func updateSearchResults(for searchController: UISearchController) {
        let searchString = searchController.searchBar.text
        filteredDates.removeAll()
        
        if searchString?.isEmpty == false
        {
            let searchFilter: (String) -> Bool = {(word) in
                let range = word.range(of: searchString!, options: .caseInsensitive)
                return range != nil
            }
            filteredDates = allDates.filter(searchFilter)
        }
        tableView.reloadData()
    }
}
