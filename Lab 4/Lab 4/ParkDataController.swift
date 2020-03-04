//
//  ParkDataController.swift
//  Lab 4
//
//  Created by Jacky Cheung on 3/4/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import Foundation

enum JsonError : Error{
    case BadUrl
    case BadResponse
    case CouldNotParse
}

class ParkDataController {
    var currentParks = ParkData()
    var onDataUpdate: ((_ data: [Park]) -> Void)?

    func loadJson(stateCode: String) {
        let urlPath = "https://developer.nps.gov/api/v1/parks?stateCode=\(stateCode)&api_key=yzJGwRXZQUNk9hwWhpD0lxwSldkyZLV3bqaa5oQW"
        
        guard let url = URL(string: urlPath) else {
            print("Bad url")
            return
        }
        let session = URLSession.shared.dataTask(with: url, completionHandler: {(data, response, error) in
            let httpResponse = response as! HTTPURLResponse
            
            let statusCode = httpResponse.statusCode
            
            guard statusCode == 200 else {
                print("File download error: \(statusCode)")
                return
            }
            
            print("Download complete!")
            
            DispatchQueue.main.async {
                self.parseJson(rawData: data!)
            }
        })
        session.resume()
    }
    
    func parseJson(rawData: Data) {
        do {
            let parsedData = try JSONDecoder().decode(ParkData.self, from: rawData)
            currentParks.data.removeAll()
            
            for park in parsedData.data {
                currentParks.data.append(park)
            }
        } catch {
            print("Json could not be parsed!")
        }
        print("Json parsed successfully!")
        
        onDataUpdate?(currentParks.data)
    }
}
