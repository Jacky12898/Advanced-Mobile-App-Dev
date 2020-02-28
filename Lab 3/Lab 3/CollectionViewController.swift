//
//  CollectionViewController.swift
//  Lab 3
//
//  Created by Jacky Cheung on 2/27/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import UIKit

private let reuseIdentifier = "Cell"

class CollectionViewController: UICollectionViewController, UICollectionViewDelegateFlowLayout {
    
    var images = [String]()
    
    let spacing : CGFloat = 8
    let numberItemsPerRow : CGFloat = 3
    let spacingBetweenCells : CGFloat = 8

    override func viewDidLoad() {
        super.viewDidLoad()
        
        for i in 1...9 {
            images.append("image" + String(i))
        }
        
        let layout = UICollectionViewFlowLayout()
        layout.sectionInset = UIEdgeInsets(top: spacing, left: spacing, bottom: spacing, right: spacing)
        layout.minimumLineSpacing = spacing
        layout.minimumInteritemSpacing = spacing
        collectionView.collectionViewLayout = layout
    }

    // MARK: - Navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetail"{
            let detailVC = segue.destination as! DetailViewController
            let indexPath = collectionView.indexPath(for: sender as! CollectionViewCell)
            detailVC.title = "Pokedex Entry #\(indexPath!.row)"
            detailVC.imageName = images[indexPath!.row]
        }
    }

    // MARK: UICollectionViewDataSource
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return images.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! CollectionViewCell
        let image = UIImage(named: images[indexPath.row])
        cell.imageView.image = image
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let totalSpacing = (2 * spacing) + ((numberItemsPerRow - 1) * spacingBetweenCells)
        let width = (collectionView.bounds.width - totalSpacing) / numberItemsPerRow
        return CGSize(width: width, height: width)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForHeaderInSection section: Int) -> CGSize {
        return CGSize.init(width: 50, height: 40)
    }
    
    override func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        var header : HeaderSupplementaryView?
        if kind == UICollectionView.elementKindSectionHeader {
            header = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "Header", for: indexPath) as? HeaderSupplementaryView
            header?.headerLabel.text = "Pokemon Starters"
        }
        
        var footer : FooterSupplementaryView?
        if kind == UICollectionView.elementKindSectionFooter {
            footer = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "Footer", for: indexPath) as? FooterSupplementaryView
            footer?.footerLabel.text = String(images.count) + " Pokemon Entries"
        }
        
        return header!
    }
}
