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
    var items: String
    var urls: String
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
            allItems.append(item.items)
        }
        return allItems
    }
    
    func getURL(idx: Int) -> [String]{
        var allURLs = [String]()
        for item in allData{
            allURLs.append(item.urls)
        }
        return allURLs
    }
    
    //func addItem(dataIdx: Int, newItem: String, itemIdx: Int){
    //    allData[dataIdx].urls.insert(newItem, at: itemIdx)
    //}
    
    //func deleteItem(dataIdx: Int, itemIdx: Int){
    //    allData[dataIdx].urls.remove(at: itemIdx)
    //}
}
