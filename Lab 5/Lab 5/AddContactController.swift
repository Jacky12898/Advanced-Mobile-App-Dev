//
//  AddContactController.swift
//  Lab 5
//
//  Created by Jacky Cheung on 3/23/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class AddContactController: UIViewController, UITextFieldDelegate, UITextViewDelegate {

    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var numberTextField: UITextField!
    
    var name: String?
    var number: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        let tapRecognizer = UITapGestureRecognizer()
        tapRecognizer.addTarget(self, action: #selector(AddContactController.didTapView))
        self.view.addGestureRecognizer(tapRecognizer)
    }
    
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool{
        if(text == "\n"){
            textView.resignFirstResponder()
            return false
        }
        return true
    }
    
    @objc func didTapView(){
        self.view.endEditing(true)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SaveSegue" {
            if let phoneNumber = numberTextField.text {
                number = phoneNumber
                if nameTextField.text!.isEmpty == false {
                    name = nameTextField.text
                }
            }
            else {
                print("Not a valid phone number: \(numberTextField.text!)")
            }
        }
    }
}
