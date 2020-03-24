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
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(DetailViewController.tapFunction))

        directionLabel.isUserInteractionEnabled = true
        directionLabel.addGestureRecognizer(tap)
    }
    
    @objc func tapFunction(){
        if let url = URL(string: siteDirections){
            UIApplication.shared.open(url, options: [:])
        }
        print ("Navigating to: " + siteDirections)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
