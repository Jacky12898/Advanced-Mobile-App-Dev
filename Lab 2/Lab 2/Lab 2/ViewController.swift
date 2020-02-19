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

    override func viewDidLoad() {
        super.viewDidLoad()
        do{
            try groceriesDataController.loadData()
            groceriesList = groceriesDataController.getGroceries()
        }
        
        catch{
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
}

