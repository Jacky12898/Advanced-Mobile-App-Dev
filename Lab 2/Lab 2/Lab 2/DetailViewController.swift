//
//  DetailViewController.swift
//  Lab 2
//
//  Created by Jacky Cheung on 2/18/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class DetailViewController: UITableViewController {

    var selectedList = 0
    var groceriesDataController = GroceriesDataController()
    var dateList = [String]()
    
    override func viewWillAppear(_ animated: Bool) {
        dateList = groceriesDataController.getDate(idx: selectedList)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //Comment out for add button instead of edit
        //self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    
    @IBAction func unwindSegue(_ segue: UIStoryboardSegue) {
        
        if segue.identifier == "save" {
            let source = segue.source as! AddDateViewController
            
            if source.addedDate.isEmpty == false {
                
                groceriesDataController.addDate(dataIdx: selectedList, newDate: source.addedDate, dateIdx: dateList.count)
                
                dateList.append(source.addedDate)
                
                tableView.reloadData()
            }
        }
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dateList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "DateCell", for: indexPath)

        cell.textLabel?.text = dateList[indexPath.row]
        return cell
    }

    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    

    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            groceriesDataController.deleteDate(dataIdx: selectedList, dateIdx: indexPath.row)
            dateList.remove(at: indexPath.row)
            
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }


    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        
        let fromRow = fromIndexPath.row
        let toRow = to.row
        
        let moveDate = dateList[fromRow]
        
        dateList.swapAt(fromRow, toRow)
        
        groceriesDataController.deleteDate(dataIdx: selectedList, dateIdx: fromRow)
        groceriesDataController.addDate(dataIdx: selectedList, newDate: moveDate, dateIdx: toRow)
    }

    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        return true
    }

}
