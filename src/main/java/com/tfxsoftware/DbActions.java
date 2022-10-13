package com.tfxsoftware;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public abstract class DbActions{
    
    static final MongoClientURI Uri = new MongoClientURI("mongodb+srv://<username>:<password>@cluster0.bs5t1p9.mongodb.net/?retryWrites=true&w=majority");
    static final MongoClient client = new MongoClient(Uri);
    
    static MongoCollection<Document> getCollection(String d, String c){
        MongoDatabase dataBase = client.getDatabase(d);
        MongoCollection<Document> collection = dataBase.getCollection(c);
        return collection;
    }

    public static boolean addTime(String nome, String pais, String titulos, String tecnico){
        if (verificaExistencia("Times", nome)){
        MongoCollection<Document> collection = getCollection("ProjetoAndre", "Times");
        collection.insertOne(new Document("Nome", nome)
        .append("Pais", pais)
        .append("Titulos", titulos)
        .append("Tecnico", tecnico));
        return true;
        }
        else {
            return false;
        }
    }

    public static boolean verificaExistencia(String c, String nome){
        MongoCollection<Document> collection = getCollection("ProjetoAndre", c);
        MongoCursor<Document> cursor = collection.find().iterator();
        while(cursor.hasNext()){
            String v = (String) cursor.next().get("Nome");
            if (v.equals(nome)){
                return false;
            }
        }
        return true;
    }




}
