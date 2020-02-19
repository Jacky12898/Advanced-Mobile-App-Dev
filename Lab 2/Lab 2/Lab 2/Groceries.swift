//
//  Groceries.swift
//  Lab 2
//
//  Created by Jacky Cheung on 2/18/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import Foundation

struct GroceriesDataModel: Codable{
    var groceries: String
    var date: [String]
}

enum DataError: Error {
    case NoDataFile
    case CouldNotDecode
    case CouldNotEncode
}

class GroceriesDataController{
    var allData = [GroceriesDataModel]()
    let fileName = "Groceries"
    
    func loadData() throws{
        if let dataURL = Bundle.main.url(forResource: fileName, withExtension: "plist"){
            let decoder = PropertyListDecoder()
            do{
                let data = try Data(contentsOf: dataURL)
                allData = try decoder.decode([GroceriesDataModel].self, from: data)
            }
            catch{
                throw DataError.CouldNotDecode
            }
        }
        
        else{
            throw DataError.NoDataFile
        }
    }
    
    func getGroceries() -> [String]{
        var allGroceries = [String]()
        for item in allData{
            allGroceries.append(item.groceries)
        }
        return allGroceries
    }
    
    func getDate(idx: Int) -> [String]{
        return allData[idx].date
    }
    
    func addDate(dataIdx: Int, newDate: String, dateIdx: Int){
        allData[dataIdx].date.insert(newDate, at: dateIdx)
    }
    
    func deleteDate(dataIdx: Int, dateIdx: Int){
        allData[dataIdx].date.remove(at: dateIdx)
    }
}
