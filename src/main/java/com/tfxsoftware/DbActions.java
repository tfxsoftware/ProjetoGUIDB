package com.tfxsoftware;


import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public abstract class DbActions{
    
    static final MongoClientURI Uri = new MongoClientURI("mongodb+srv://<>@cluster0.bs5t1p9.mongodb.net/?retryWrites=true&w=majority");
    static final MongoClient client = new MongoClient(Uri);
    public static Time timeSelecionado;
    public static Jogador jogadorSelecionado;
    
    static MongoCollection<Document> getCollection(String d, String c){
        MongoDatabase dataBase = client.getDatabase(d);
        MongoCollection<Document> collection = dataBase.getCollection(c);
        return collection;
    }

    public static boolean addTime(String nome, String pais, String titulos, String tecnico){
        if (verificaExistenciaTime("Times", nome)){
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

    public static boolean addJogador(String nome, String idade, String gols, String posicao, String time){
        if (verificaExistenciaJogador("Jogadores", nome)){
            MongoCollection<Document> collection = getCollection("ProjetoAndre", "Jogadores");
            collection.insertOne(new Document("Nome", nome)
            .append("Idade", idade)
            .append("Gols", gols)
            .append("Posicao", posicao)
            .append("Time", time));
            return true;
            }
            else {
                return false;
            }
    }
     
    public static boolean verificaExistenciaTime(String c, String nome){
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

    public static boolean verificaExistenciaJogador(String c, String nome){
        MongoCollection<Document> collection = getCollection("ProjetoAndre", c);
        MongoCursor<Document> cursor = collection.find().iterator();
        while(cursor.hasNext()){
            Document jogador = cursor.next();
            String n = (String) jogador.get("Nome");
            String t = (String) jogador.get("Time");
            if (n.equals(nome) && t.equals(timeSelecionado.getNome())){
                return false;
            }
        }
        return true;
    }

    public static void deletaTime(){
        MongoCollection<Document> collectionj = getCollection("ProjetoAndre", "Jogadores");
        MongoCursor<Document> cursorj = collectionj.find(eq("Time", timeSelecionado.getNome())).iterator();
        while (cursorj.hasNext()){
            Document jogador = cursorj.next();
            collectionj.deleteOne(jogador);
        }
        
        MongoCollection<Document> collection = getCollection("ProjetoAndre", "Times");
        MongoCursor<Document> cursor = collection.find().iterator();
            while(cursor.hasNext()){
                Document time = cursor.next();
                String n = (String) time.get("Nome");
                if (n.equals(timeSelecionado.getNome())){
                    collection.deleteOne(time);
                }
        }
    }
    
    public static void deletaJogador(Document j){
        MongoCollection<Document> collection = getCollection("ProjetoAndre", "Jogadores");
        MongoCursor<Document> cursor = collection.find().iterator();
            while(cursor.hasNext()){
                Document jogador = cursor.next();
                String n = (String) jogador.get("Nome");
                String t = (String) jogador.get("Time");
                if (n.equals(j.get("Nome")) && t.equals(timeSelecionado.getNome())){
                    collection.deleteOne(jogador);
                }
        }
    }

    public static void editaTime(){
        MongoCollection<Document> collection = getCollection("ProjetoAndre", "Times");
        MongoCursor<Document> cursor = collection.find().iterator();
        
    }


}
