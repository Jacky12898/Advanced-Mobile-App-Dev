//
//  ResultsViewController.swift
//  Lab 4
//
//  Created by Jacky Cheung on 3/4/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class ResultsViewController: UITableViewController {

    var results = [Park]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return results.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ParkCell", for: indexPath)
        cell.textLabel!.text = results[indexPath.row].name
        return cell
    }

    // MARK: - Navigation
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "DetailSegue" {
            let idx = tableView.indexPath(for: sender as! UITableViewCell)
            let selectedPark = results[idx!.row]
            
            let detailVC = segue.destination as! DetailViewController
            
            detailVC.title = selectedPark.name
            detailVC.siteDirections = selectedPark.directionsUrl
            detailVC.siteDescription = selectedPark.description
        }
    }
}
