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
    let dataFileName = "DefaultData"
    
    init() {
        let app = UIApplication.shared
        
        NotificationCenter.default.addObserver(self, selector: #selector(ShoppingListDataController.writeData(_:)), name: UIApplication.willResignActiveNotification, object: app)
    }
    
    func getDataFile(dataFile: String) -> URL {
        //let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        
        //let docDir = dirPath[0]
        //print(docDir.appendingPathComponent(dataFile))
        return Bundle.main.url(forResource: dataFileName, withExtension: "plist")!
        
        //return docDir.appendingPathComponent(dataFile)
    }
    
    @objc func writeData(_ notification: NSNotification) throws {
        let dataFileURL = getDataFile(dataFile: dataFileName)
        let encoder = PropertyListEncoder()
        encoder.outputFormat = .xml
        
        do {
            let data = try encoder.encode(allData.self)
            try data.write(to: dataFileURL)
            
        } catch {
            throw DataError.CouldNotEncode
        }
    }
    
    func loadData() throws{
        let pathURL: URL?
        let dataFileURL = getDataFile(dataFile: dataFileName)
        print(dataFileURL.path)
        //if FileManager.default.fileExists(atPath: dataFileURL.path){
        //    pathURL = dataFileURL
        //}
        
        //else{
            pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist")
        //}
        
        print(pathURL!)
        
        if let dataURL = pathURL{
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
    
    func addItem(dataIdx: Int, newItem: String, newURL: String){
        allData[allData.count/2].item.append(newItem)
        allData[allData.count/2].url.append(newURL)

    }
    
    func deleteItem(dataIdx: Int){
        allData[dataIdx].item.removeAll()
        allData[dataIdx].url.removeAll()
    }
}
