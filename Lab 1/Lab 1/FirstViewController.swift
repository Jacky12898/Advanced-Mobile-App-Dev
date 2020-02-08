//
//  FirstViewController.swift
//  Lab 1
//
//  Created by Jacky Cheung on 2/7/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    var foodsController = FoodsController()
    var food = [String]()

    override func viewDidLoad() {
        super.viewDidLoad()
        do {
            try foodsController.loadData()

            food = try foodsController.getFoods(idx: 0)
        } catch {
            print(error)
        }
    }
    
    //let food = ["Meat", "Vegetable", "Grain", "Dairy", "Fruit", "Sweets"]

    @IBOutlet weak var picker: UIPickerView!
    
    @IBOutlet weak var pickerLabel: UILabel!
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return food.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return food[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        pickerLabel.text = "Eat some \(food[row])"
    }
}
