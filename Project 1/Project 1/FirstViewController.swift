//
//  FirstViewController.swift
//  Project 1
//
//  Created by Jacky Cheung on 2/25/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let tap = UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:)))
        view.addGestureRecognizer(tap)
    }
    
    let googleShoppingScheme = "https://google.com/search?tbm=shop&q="
    
    func openGoogleShopping(scheme: String){
        if let url = URL(string: scheme){
            UIApplication.shared.open(url, options: [:])
        }
    }

    func schemeAvailable(scheme: String) -> Bool{
        if let url = URL(string: scheme){
            return UIApplication.shared.canOpenURL(url)
        }
        return false;
    }
    
    @IBOutlet weak var keywords: UITextField!
    @IBOutlet weak var minPrice: UITextField!
    @IBOutlet weak var maxPrice: UITextField!
    
    @IBAction func searchButton(_ sender: Any) {
        let searchAvailable = schemeAvailable(scheme: googleShoppingScheme)
        
        if searchAvailable{
            var additionalSearchQuery: String = ""
            
            if let keywordsText = keywords.text{
                additionalSearchQuery += keywordsText + "&tbs=vw%3Al%2Cmr%3A1%2Cprice%3A1%2" //"&tbs=vw:g,mr:1"
            }
            
            if let min = minPrice.text{
                additionalSearchQuery += "Cppr_min%3A" + min + "%2" //",ppr_min:"
            }
            
            if let max = maxPrice.text{
                additionalSearchQuery += "Cppr_max%3A" + max
            }
            
            openGoogleShopping(scheme: googleShoppingScheme + additionalSearchQuery)
        }
    }
}

