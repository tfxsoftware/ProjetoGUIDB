package com.tfxsoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class NovoJogadorController extends MainApp{

    @FXML
    private AnchorPane not;

    @FXML
    private Button novojogador_button_criar;

    @FXML
    private Button novojogador_button_voltar;

    @FXML
    private Label novojogador_label_gols;

    @FXML
    private Label novojogador_label_idade;

    @FXML
    private Label novojogador_label_nome;

    @FXML
    private Label novojogador_label_novojogador;

    @FXML
    private Label novojogador_label_posicao;

    @FXML
    private TextField novojogador_textf_gols;

    @FXML
    private TextField novojogador_textf_idade;

    @FXML
    private TextField novojogador_textf_nome;

    @FXML
    private TextField novojogador_textf_posicao;

    private Alert alertbox = new Alert(AlertType.INFORMATION);

    @FXML
    void registrarJogador (ActionEvent event) {
        if (novojogador_textf_nome.getText().equals("") ||
            novojogador_textf_idade.getText().equals("") ||
            novojogador_textf_gols.getText().equals("") ||
            novojogador_textf_posicao.getText().equals("")) {
                alertbox.setAlertType(AlertType.ERROR);
                alertbox.setContentText("Por favor, preencha todos os campos!");
                alertbox.show();
        }   
        else{
            if(DbActions.addJogador(novojogador_textf_nome.getText(), novojogador_textf_idade.getText(), novojogador_textf_gols.getText(), novojogador_textf_posicao.getText(), DbActions.timeSelecionado.getNome())){
                novojogador_textf_gols.setText("");
                novojogador_textf_idade.setText("");
                novojogador_textf_nome.setText("");
                novojogador_textf_posicao.setText("");

                alertbox.setAlertType(AlertType.INFORMATION);
                alertbox.setContentText("Jogador registrado com sucesso!");
                alertbox.show();
            }
            else{
                alertbox.setAlertType(AlertType.ERROR);
                alertbox.setContentText("Jogador com o mesmo nome já registrado!");
                alertbox.show();
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        VerJogadoresController.voltarNovoJogador();
    }

}
