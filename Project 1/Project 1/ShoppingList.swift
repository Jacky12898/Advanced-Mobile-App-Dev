//
//  ShoppingList.swift
//  Project 1
//
//  Created by Jacky Cheung on 2/26/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import Foundation
import UIKit

struct ShoppingListDataModel: Codable{
    var item: String
    var url: String
}

enum DataError: Error {
    case NoDataFile
    case CouldNotDecode
    case CouldNotEncode
}

class ShoppingListDataController{
    var allData = [ShoppingListDataModel]()
    let fileName = "DefaultData"
    
    func loadData() throws{
        if let dataURL = Bundle.main.url(forResource: fileName, withExtension: "plist"){
            let decoder = PropertyListDecoder()
            do{
                let data = try Data(contentsOf: dataURL)
                allData = try decoder.decode([ShoppingListDataModel].self, from: data)
            }
            catch{
                throw DataError.CouldNotDecode
            }
        }
        
        else{
            throw DataError.NoDataFile
        }
    }
    
    func getItems() -> [String]{
        var allItems = [String]()
        for item in allData{
            allItems.append(item.item)
        }
        return allItems
    }
    
    func getURLs() -> [String]{
        var allURLs = [String]()
        for url in allData{
            allURLs.append(url.url)
        }
        return allURLs
    }
    
    func addItem(newItem: String, newURL: String){
        allData[allData.count/2].item.append(newItem)
        allData[allData.count/2].url.append(newURL)
    }
    
    //func deleteItem(dataIdx: Int, itemIdx: Int){
    //    allData[dataIdx].url.remove(at: itemIdx)
    //}
}
