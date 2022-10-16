package com.tfxsoftware;

public class Jogador {
    private String Nome;
    private String Idade;
    private String Gols;
    private String Posicao;
    private String Time;
    
    public Jogador(String nome, String idade, String gols, String posicao, String time) {
        this.Nome = nome;
        this.Idade = idade;
        this.Gols = gols;
        this.Posicao = posicao;
        this.Time = time;
    }
    
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public String getIdade() {
        return Idade;
    }
    public void setIdade(String idade) {
        Idade = idade;
    }
    public String getGols() {
        return Gols;
    }
    public void setGols(String gols) {
        Gols = gols;
    }
    public String getPosicao() {
        return Posicao;
    }
    public void setPosicao(String posicao) {
        Posicao = posicao;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

}
