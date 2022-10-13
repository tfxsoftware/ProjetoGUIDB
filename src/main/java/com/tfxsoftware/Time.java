package com.tfxsoftware;


import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class Time {
    
    private String nome;
    private String pais;
    private String titulos;
    private int qtdjogadores;
    
    
    public Time(String nome, String pais, String titulos, int qtd_jogadores) {
        this.nome = nome;
        this.pais = pais;
        this.titulos = titulos;
        this.qtdjogadores = qtd_jogadores;
    }
    public String getNome() {
        return nome;
    }
    public String getPais() {
        return pais;
    }
    public String getTitulos() {
        return titulos;
    }
    public int getQtdjogadores() {
        return qtdjogadores;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }
    public void setQtdjogadores(int qtdjogadores) {
        this.qtdjogadores = qtdjogadores;
    }

    public String toJson() throws JsonProcessingException{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(this);
        return json;
    }
}
