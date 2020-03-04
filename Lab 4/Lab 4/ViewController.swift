//
//  ViewController.swift
//  Lab 4
//
//  Created by Jacky Cheung on 2/27/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    let stateOptions = ["CO", "CA", "FL", "ME", "UT", "NM", "OH", "MI"]
    var selectedState = String()
    var parkDC = ParkDataController()
    var data = [Park]()

    override func viewDidLoad() {
        super.viewDidLoad()
        selectedState = stateOptions[0]
        
        parkDC.onDataUpdate = {[weak self](data: [Park]) in self?.searchDone(park: data)}
    }

    @IBAction func searchParks(_ sender: Any) {
        parkDC.loadJson(stateCode: selectedState)
        
        let alert = UIAlertController(title: nil, message: "Searching in \(selectedState)", preferredStyle: .alert)
        
        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 0, y: 5, width: 50, height: 50))
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.style = UIActivityIndicatorView.Style.medium
        loadingIndicator.startAnimating()
        
        alert.view.addSubview(loadingIndicator)
        present(alert, animated: true, completion: nil)
    }
    
    func searchDone(park: [Park]) {
        dismiss(animated: true, completion: nil)
        data = park
        performSegue(withIdentifier: "SearchResults", sender: nil)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SearchResults" {
            let resultsVC = segue.destination as! ResultsViewController
            resultsVC.title = "\(selectedState) Parks"
            resultsVC.results = data
        }
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return stateOptions.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return stateOptions[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        selectedState = stateOptions[row]
    }
}

