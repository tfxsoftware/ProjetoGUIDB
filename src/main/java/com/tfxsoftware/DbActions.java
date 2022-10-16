package com.tfxsoftware;


import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

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

    public static void deletaJogador(String nome){
        System.out.println("teste");
        MongoCollection<Document> collection = getCollection("ProjetoAndre", "Jogadores");
        Bson query = eq(new Document().append("Nome", nome).append("Time", timeSelecionado.getNome()));
        collection.deleteOne(query);
    }


}
