//
//  AddItemViewController.swift
//  Project 1
//
//  Created by Jacky Cheung on 2/26/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class AddItemViewController: UIViewController {

    @IBOutlet weak var itemText: UITextField!
    @IBOutlet weak var urlText: UITextField!
    
    var addedItem = String()
    var addedURL = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "save" {
            if itemText.text?.isEmpty == false && urlText.text?.isEmpty == false{
                if urlText.text!.contains("http://") || urlText.text!.contains("https://"){
                    addedItem = itemText.text!
                    addedURL = urlText.text!
                }
            }
        }
    }
}
