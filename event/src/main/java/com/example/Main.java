// package com.example;


// import com.mongodb.client.*;
// import org.bson.Document;

// public class Main {
//     public static void main(String[] args) {
//         // Create a MongoDB client
//         MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        
//         // Get the database
//         MongoDatabase database = mongoClient.getDatabase("eventregistration");
        
//         // Get the collection
//         MongoCollection<Document> collection = database.getCollection("event");
        
//         // Create documents to insert
//         Document student1 = new Document("name", "Mahesh")
//             .append("eventname", "Tech Fusion")
//             .append("registration_date", "2024-11-20");

//         Document student2 = new Document("name", "Ashi")
//             .append("eventname", "Hackathon")
//             .append("registration_date", "2024-11-10");
        
//         // Insert documents
//         collection.insertOne(student1);
//         collection.insertOne(student2);
        
//         System.out.println("Documents inserted successfully!");

//         // List the documents
//         FindIterable<Document> documents = collection.find();
//         for (Document doc : documents) {
//             System.out.println(doc.toJson());
//         }

//         // Close the connection
//         mongoClient.close();
//     }
// }
// package com.example;

// import com.mongodb.client.*;
// import com.mongodb.client.model.Filters;
// import org.bson.Document;

// public class Main {

//     private static MongoClient mongoClient;
//     private static MongoDatabase database;
//     private static MongoCollection<Document> collection;

//     public static void main(String[] args) {
//         // Initialize MongoDB client and access the collection
//         mongoClient = MongoClients.create("mongodb://localhost:27017");
//         database = mongoClient.getDatabase("eventregistration");
//         collection = database.getCollection("event");

//         // Test Case 1: Register a user for an event and verify it’s saved
//         registerUser("Mahesh", "Tech Fusion", "2024-11-10");

//         // Test Case 2: Attempt to register the same user for the same event twice
//         registerUser("Ashi", "Hackathon", "2024-11-20");

//         // List all documents in the collection
//         listDocuments();

//         // Close the connection
//         mongoClient.close();
//     }

//     // Method to register a user for an event
//     private static void registerUser(String name, String eventName, String registrationDate) {
//         // Check if the user is already registered for the event
//         Document existingRegistration = collection.find(
//             Filters.and(
//                 Filters.eq("name", name),
//                 Filters.eq("eventname", eventName)
//             )
//         ).first();

//         if (existingRegistration != null) {
//             System.out.println("User " + name + " is already registered for event: " + eventName);
//             return;
//         }

//         // Create and insert the document
//         Document registration = new Document("name", name)
//             .append("eventname", eventName)
//             .append("registration_date", registrationDate);

//         collection.insertOne(registration);
//         System.out.println("User " + name + " registered for event: " + eventName);
//     }

//     // Method to list all documents in the collection
//     private static void listDocuments() {
//         FindIterable<Document> documents = collection.find();
//         for (Document doc : documents) {
//             System.out.println(doc.toJson());
//         }
//     }
// }


package com.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Scanner;

public class Main {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    public static void main(String[] args) {
        // Initialize MongoDB client and access the collection
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("eventregistration");
        collection = database.getCollection("event");

        Scanner scanner = new Scanner(System.in);

        // Ask for user input
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        System.out.print("Enter registration date (yyyy-MM-dd): ");
        String registrationDate = scanner.nextLine();

        // Register the user for the event
        registerUser(name, eventName, registrationDate);

        // List all documents in the collection
        listDocuments();

        // Close the scanner and MongoDB connection
        scanner.close();
        mongoClient.close();
    }

    // Method to register a user for an event
    private static void registerUser(String name, String eventName, String registrationDate) {
        // Check if the user is already registered for the event
        Document existingRegistration = collection.find(
            Filters.and(
                Filters.eq("name", name),
                Filters.eq("eventname", eventName)
            )
        ).first();

        if (existingRegistration != null) {
            System.out.println("User " + name + " is already registered for event: " + eventName);
            return;
        }

        // Create and insert the document
        Document registration = new Document("name", name)
            .append("eventname", eventName)
            .append("registration_date", registrationDate);

        collection.insertOne(registration);
        System.out.println("User " + name + " registered for event: " + eventName);
    }

    // Method to list all documents in the collection
    private static void listDocuments() {
        System.out.println("Current registrations:");
        FindIterable<Document> documents = collection.find();
        for (Document doc : documents) {
            System.out.println(doc.toJson());
        }
    }
}
