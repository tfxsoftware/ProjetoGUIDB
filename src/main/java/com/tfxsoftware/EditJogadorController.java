package com.tfxsoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditJogadorController {

    @FXML
    private Button editjogador_button_criar;

    @FXML
    private Button editjogador_button_voltar;

    @FXML
    private Label editjogador_label_gols;

    @FXML
    private Label editjogador_label_idade;

    @FXML
    private Label editjogador_label_nome;

    @FXML
    private TextField editjogador_textf_gols;

    @FXML
    private TextField editjogador_textf_idade;

    @FXML
    private TextField editjogador_textf_nome;

    @FXML
    private TextField editjogador_textf_posicao;

    @FXML
    private Label editogador_label_header;

    @FXML
    private Label editogador_label_posicao;

    @FXML
    private AnchorPane not;

    public VerJogadoresController verJogadoresController;

    @FXML
    void editJogador(ActionEvent event) {
        Jogador jogador = new Jogador(editjogador_textf_nome.getText(), 
                                      editjogador_textf_idade.getText(), 
                                      editjogador_textf_gols.getText(), 
                                      editjogador_textf_posicao.getText(), 
                                      DbActions.jogadorSelecionado.getTime());
        
        DbActions.editaJogador(jogador);
        verJogadoresController.popularJogadores();
    }

    @FXML
    void voltar(ActionEvent event) {
        verJogadoresController.editjogador.close();
    }

    void setVerJogadoresController(VerJogadoresController verJogadoresController){
        this.verJogadoresController = verJogadoresController;
    }

    @FXML
    void iniatialize(){
        editjogador_textf_nome.setText(DbActions.jogadorSelecionado.getNome());
        editjogador_textf_idade.setText(DbActions.jogadorSelecionado.getIdade());
        editjogador_textf_gols.setText(DbActions.jogadorSelecionado.getGols());
        editjogador_textf_posicao.setText(DbActions.jogadorSelecionado.getPosicao());
    }
}
