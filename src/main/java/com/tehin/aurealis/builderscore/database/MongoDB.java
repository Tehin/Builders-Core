package com.tehin.aurealis.builderscore.database;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.tehin.aurealis.builderscore.utils.Utils;

public class MongoDB {
	private MongoCollection<Document> projectsData;
	private MongoClient client;
	private MongoDatabase db;

	public void connect(String connectionURI) {
		Utils.sendConsole("[BuildersCore] &fConnecting to Mongo Database...");
		
		try {
			MongoClientURI uri = new MongoClientURI(connectionURI);
			this.client = new MongoClient(uri);
			this.db = client.getDatabase("BuildersCore");
			
			this.projectsData = db.getCollection("projects_data");
		} catch (Exception e) {
			Utils.sendConsole("[BuildersCore] &cCould not connect to Mongo Database: ");
			e.printStackTrace();
		}
		
	}
	
	public MongoCollection<Document> getProjects() {
		return this.projectsData;
	}
	
}