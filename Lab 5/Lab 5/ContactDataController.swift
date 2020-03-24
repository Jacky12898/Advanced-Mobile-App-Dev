//
//  ContactDataController.swift
//  Lab 5
//
//  Created by Jacky Cheung on 3/23/20.
//  Copyright © 2020 Jacky Cheung. All rights reserved.
//

import Foundation
import Firebase

struct Contact {
    var name: String
    var number: String
    var id: String
}

class ContactDataController {
    var db: Firestore!
    var contactData = [Contact]()
    var onDataUpdate: (([Contact]) -> Void)!
    
    init() {
        let settings = FirestoreSettings()
        Firestore.firestore().settings = settings
        
        db = Firestore.firestore()
    }
    
    func loadData(){
        db.collection("contacts").addSnapshotListener{QuerySnapshot, error in
            guard let collection = QuerySnapshot else{
                print("Error fetching collection: \(error!)")
                return
            }
            
            let docs = collection.documents
            self.contactData.removeAll()
            
            for doc in docs{
                let data = doc.data()
                let name = data["Name"] as! String
                let number = data["Phone Number"] as! String
                let id = doc.documentID
                let contact = Contact(name: name, number: number, id: id)
                self.contactData.append(contact)
            }
            self.onDataUpdate(self.contactData)
        }
    }
    
    func writeData(name: String, number: String){
        db.collection("contacts").addDocument(data: ["Name": name, "Phone Number": number], completion: { err in
            if let err = err{
                print("Error adding document: \(err)")
            }
            else{
                print("New document added successfully!")
            }
        })
    }
}
