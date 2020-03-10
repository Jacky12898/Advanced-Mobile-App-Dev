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
    
    let googleShoppingScheme = "http://google.com/search?tbm=shop&q="
    
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
                additionalSearchQuery += keywordsText + "&tbs=vw:g,mr:1"
            }
            
            if let min = minPrice.text{
                additionalSearchQuery += ",ppr_min:" + min
            }
            
            if let max = maxPrice.text{
                additionalSearchQuery += ",ppr_max:" + max
            }
            
            openGoogleShopping(scheme: googleShoppingScheme + additionalSearchQuery)
        }
        
        else{
            
        }
    }
}

