//
//  ListViewController.swift
//  Project 1
//
//  Created by Jacky Cheung on 2/25/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class ListViewController: UITableViewController {

    var selectedList = 0
    var shoppingListDataController = ShoppingListDataController()
    var URLList = [String]()
    
    override func viewWillAppear(_ animated: Bool) {
        URLList = shoppingListDataController.getURL(idx: selectedList)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    
    @IBAction func unwindSegue(_ segue: UIStoryboardSegue) {
        
        if segue.identifier == "save" {
            let source = segue.source as! AddItemViewController
            
            if source.addedItem.isEmpty == false {
                
                //shoppingListDataController.addDate(dataIdx: selectedList, newDate: source.addedDate, dateIdx: dateList.count)
                
                URLList.append(source.addedURL)
                
                tableView.reloadData()
            }
        }
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return URLList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        let cell = tableView.dequeueReusableCell(withIdentifier: "ItemCell", for: indexPath)

        cell.textLabel?.text = URLList[indexPath.row]
        return cell
    }

    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }

    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            URLList.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }

    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        let fromRow = fromIndexPath.row
        let toRow = to.row
        //let moveItem = URLList[fromRow]
        
        URLList.swapAt(fromRow, toRow)
    }

    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        return true
    }
}
