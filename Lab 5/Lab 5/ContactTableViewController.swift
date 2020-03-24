//
//  ContactTableViewController.swift
//  Lab 5
//
//  Created by Jacky Cheung on 3/23/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class ContactTableViewController: UITableViewController {

    var contactDC = ContactDataController()
    var contactData = [Contact]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        contactDC.onDataUpdate = {[weak self] (data: [Contact]) -> Void in self?.newData(data: data)}
        contactDC.loadData()
    }

    // MARK: - Table view data source

    @IBAction func unwindSegue(segue:UIStoryboardSegue){
        if segue.identifier == "SaveSegue" {
            let sourceVC = segue.source as! AddContactController
            if let phoneNumber = sourceVC.number, let contactName = sourceVC.name {
                contactDC.writeData(name: contactName, number: phoneNumber)
            }
            else {
                print("Error writing data")
            }
        }
    }
    
    func newData(data: [Contact]){
        contactData = data
        tableView.reloadData()
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return contactData.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ContactCell", for: indexPath)
        let contact = contactData[indexPath.row]
        cell.textLabel?.text = contact.name
        cell.detailTextLabel?.text = contact.number
        return cell
    }
}
