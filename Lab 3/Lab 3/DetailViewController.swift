//
//  DetailViewController.swift
//  Lab 3
//
//  Created by Jacky Cheung on 2/27/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var imageView: UIImageView!
    
    @IBAction func share(_ sender: Any) {
        var imageArray = [UIImage]()
        imageArray.append(imageView.image!)
        let shareScreen = UIActivityViewController(activityItems: imageArray, applicationActivities: nil)
        shareScreen.modalPresentationStyle = .popover
        present(shareScreen, animated: true, completion: nil)
    }
    
    var imageName: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let name = imageName{
            imageView.image = UIImage(named: name)
        }
    }
}
