package com.tfxsoftware;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NovoTimeController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button novotime_button_criar;

    @FXML
    private Button novotime_button_voltar;

    @FXML
    private Label novotime_label_nome;

    @FXML
    private Label novotime_label_novotime;

    @FXML
    private Label novotime_label_pais;

    @FXML
    private Label novotime_label_titulos;

    @FXML
    private TextField novotime_textf_nome;

    @FXML
    private TextField novotime_textf_pais;

    @FXML
    private TextField novotime_textf_titulos;

    @FXML
    void registrar_time(ActionEvent event) {
        if (novotime_textf_nome.getText().equals("") ||
            novotime_textf_pais.getText().equals("") ||
            novotime_textf_titulos.getText().equals("")) {

        }
        else{
            DbActions.addTime(novotime_textf_nome.getText(), novotime_textf_pais.getText(), novotime_textf_titulos.getText());
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        VerTimesController.voltar();
    }

    @FXML
    public void initialize() {
    
    }

}
