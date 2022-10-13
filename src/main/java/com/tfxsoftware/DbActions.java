package com.tfxsoftware;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public abstract class DbActions{
    
    static final MongoClientURI Uri = new MongoClientURI("mongodb+srv://<user>:<password>@cluster0.bs5t1p9.mongodb.net/?retryWrites=true&w=majority");
    static final MongoClient client = new MongoClient(Uri);
    
    static MongoCollection<Document> getCollection(String d, String c){
        MongoDatabase dataBase = client.getDatabase(d);
        MongoCollection<Document> collection = dataBase.getCollection(c);
        return collection;
    }

    public static void addTime(String nome, String pais, String titulos){

        MongoCollection<Document> collection = getCollection("ProjetoAndre", "Times");
        collection.insertOne(new Document("Nome",nome)
        .append("País", pais)
        .append("Títulos", titulos)
        .append("Qtd Jogadores", 0));
    }




}
