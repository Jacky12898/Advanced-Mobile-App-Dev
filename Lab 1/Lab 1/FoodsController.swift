//
//  FoodsController.swift
//  Lab 1
//
//  Created by Jacky Cheung on 2/7/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import Foundation

enum DataError: Error {
    case BadFilePath
    case CouldNotDecodeData
    case NoData
}

class FoodsController {
    //class properties
    var foodsData: [Foods]?
    let filename = "foods"
    
    func loadData() throws {
        print("Loading data....")

        if let pathURL = Bundle.main.url(forResource: filename, withExtension: "plist") {
            let decoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                foodsData = try decoder.decode([Foods].self, from: data)
                print("Data loaded")
            } catch {
                throw DataError.CouldNotDecodeData
            }
        }

        else {
            throw DataError.BadFilePath
        }
    }

    func getFoods(idx: Int) throws -> [String] {
        if let data = foodsData {
            return data[idx].foods
        }
        else {
            throw DataError.NoData
        }
    }
}
