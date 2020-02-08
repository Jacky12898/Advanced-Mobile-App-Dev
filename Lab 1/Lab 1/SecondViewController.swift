//
//  SecondViewController.swift
//  Lab 1
//
//  Created by Jacky Cheung on 2/7/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    let safariScheme = "http://www.google.com"

    //open an app that we have access to
    func openApp(scheme: String) {
      if let url = URL(string: scheme) {
        UIApplication.shared.open(url, options: [:], completionHandler: {
          (success) in
          print("Open \(scheme): \(success)")
        })
      }
    }

    func schemeAvailable(scheme: String) -> Bool {
        if let url = URL(string: scheme) {
            return UIApplication.shared.canOpenURL(url)
        }
        return false
    }

    //open spotify if installed, apple music if installed, or navigate to iTunes on safari as last resort
    @IBAction func goToSafari(_ sender: Any) {
        
        //check for access
        let safariInstalled = schemeAvailable(scheme: safariScheme)
        
        if safariInstalled {
            openApp(scheme: safariScheme)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
