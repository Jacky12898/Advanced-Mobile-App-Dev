//
//  DetailViewController.swift
//  Lab 4
//
//  Created by Jacky Cheung on 3/4/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    var siteDescription = String()
    var siteDirections = String()
    
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var directionLabel: UILabel!
    
    override func viewWillAppear(_ animated: Bool) {
        descriptionLabel.text = siteDescription
        directionLabel.text = siteDirections
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
