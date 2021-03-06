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
    var itemList = [String]()
    var urlList = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        do{
            try shoppingListDataController.loadData()
            itemList = shoppingListDataController.getItems()
            urlList = shoppingListDataController.getURLs()
        }
        
        catch{
            print(error)
        }
        self.navigationItem.leftBarButtonItem = self.editButtonItem
        self.navigationItem.leftBarButtonItem?.tintColor = UIColor.systemYellow
    }
    
    @IBAction func unwindSegue(_ segue: UIStoryboardSegue) {
        if segue.identifier == "save" {
            let source = segue.source as! AddItemViewController
            
            if source.addedItem.isEmpty == false {
                
                shoppingListDataController.addItem(dataIdx: itemList.count, newItem: source.addedItem, newURL: source.addedURL)
                
                itemList.append(source.addedItem)
                urlList.append(source.addedURL)
                
                tableView.reloadData()
            }
            
            else{
                let alert = UIAlertController(title: nil, message: "Not a valid URL", preferredStyle: .alert)
                let okAction = UIAlertAction(
                       title: "Ok", style: .default, handler: nil)
                
                alert.addAction(okAction)
                
                if presentedViewController == nil {
                     self.present(alert, animated: true, completion: nil)
                }
                    
                else{
                    self.dismiss(animated: true) { () -> Void in
                    self.present(alert, animated: true, completion: nil)
                }
            }
        }
    }
}

    // MARK: - Table view data source

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return itemList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ItemCell", for: indexPath)

        cell.textLabel?.text = itemList[indexPath.row]
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let url = URL(string: urlList[indexPath.row]){
            UIApplication.shared.open(url, options: [:])
        }
    }

    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }

    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            shoppingListDataController.deleteItem(dataIdx: indexPath.row)
            
            itemList.remove(at: indexPath.row)
            urlList.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }

    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        let fromRow = fromIndexPath.row
        let toRow = to.row
        
        let moveItem = itemList[fromRow]
        let moveURL = urlList[fromRow]
        
        itemList.swapAt(fromRow, toRow)
        urlList.swapAt(fromRow, toRow)
        
        shoppingListDataController.deleteItem(dataIdx: fromRow)
        shoppingListDataController.addItem(dataIdx: toRow, newItem: moveItem, newURL: moveURL)
    }

    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        return true
    }
}
