//
//  Park.swift
//  Lab 4
//
//  Created by Jacky Cheung on 3/4/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import Foundation

struct Park : Decodable{
    let name: String
    let directionsUrl: String
    let description: String
}

struct ParkData : Decodable {
    var data = [Park]()
}
