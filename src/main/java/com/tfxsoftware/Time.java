package com.tfxsoftware;


import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class Time {
    
    private String Nome;
    private String Pais;
    private String Titulos;
    private String Tecnico;
    
    
    public Time(String Nome, String Pais, String Titulos, String Tecnico) {
        this.Nome = Nome;
        this.Pais = Pais;
        this.Titulos = Titulos;
        this.Tecnico = Tecnico;
    }
    public String getNome() {
        return Nome;
    }
    public String getPais() {
        return Pais;
    }
    public String getTitulos() {
        return Titulos;
    }
    public String getTecnico() {
        return Tecnico;
    }
    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    public void setPais(String Pais) {
        this.Pais = Pais;
    }
    public void setTitulos(String Titulos) {
        this.Titulos = Titulos;
    }
    public void setTecnico(String Tecnico) {
        this.Tecnico = Tecnico;
    }

    public String toJson() throws JsonProcessingException{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(this);
        return json;
    }
}
