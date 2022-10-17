package com.tfxsoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditTimeController {

    @FXML
    private Button edittime_button_editar;

    @FXML
    private Button edittime_button_voltar;

    @FXML
    private Label edittime_label_header;

    @FXML
    private Label edittime_label_nome;

    @FXML
    private Label edittime_label_pais;

    @FXML
    private Label edittime_label_tecnico;

    @FXML
    private Label edittime_label_titulos;

    @FXML
    private AnchorPane not;

    @FXML
    private TextField edittime_textf_nome;
    
    @FXML
    private TextField edittime_textf_pais;

    @FXML
    private TextField edittime_textf_tecnico;

    @FXML
    private TextField edittime_textf_titulos;

    @FXML
    void editarTime(ActionEvent event) {

    }   

    @FXML
    void voltar(ActionEvent event) {
        VerJogadoresController.voltarEditTime();
    }

    @FXML
    void initialize(){
        edittime_textf_nome.setText(DbActions.timeSelecionado.getNome());
        edittime_textf_pais.setText(DbActions.timeSelecionado.getPais());
        edittime_textf_titulos.setText(DbActions.timeSelecionado.getTitulos());
        edittime_textf_tecnico.setText(DbActions.timeSelecionado.getTecnico());
    }

}
