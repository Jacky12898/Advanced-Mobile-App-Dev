//
//  AddDateViewController.swift
//  Lab 2
//
//  Created by Jacky Cheung on 2/19/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class AddDateViewController: UIViewController {
    
    @IBOutlet weak var textField: UITextField!
    
    var addedDate = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "save" {
            if textField.text?.isEmpty == false {
                addedDate = textField.text!
            }
        }
    }
}
