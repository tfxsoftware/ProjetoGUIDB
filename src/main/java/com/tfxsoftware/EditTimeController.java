package com.tfxsoftware;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class EditTimeController{

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
    private Label edittime_label_gettime;
    
    @FXML
    private TextField edittime_textf_pais;

    @FXML
    private TextField edittime_textf_tecnico;

    @FXML
    private TextField edittime_textf_titulos;

    public VerJogadoresController verJogadoresController;

    private Alert alertbox = new Alert(AlertType.NONE);

    
    
    @FXML
    void editarTime(ActionEvent event) {
        Time time = new Time(edittime_label_gettime.getText(), edittime_textf_pais.getText(),edittime_textf_titulos.getText(),edittime_textf_tecnico.getText());
        DbActions.editaTime(time);
        DbActions.timeSelecionado = time;
        alertbox.setAlertType(AlertType.INFORMATION);
        alertbox.setContentText("Time editado com sucesso!");
        alertbox.show();
        setTexts();
        verJogadoresController.setTexts();
    }   

    @FXML
    void voltar(ActionEvent event) throws IOException {
        verJogadoresController.edittime.close();
    }

    void setTexts(){
        edittime_label_gettime.setText(DbActions.timeSelecionado.getNome());
        edittime_textf_pais.setText(DbActions.timeSelecionado.getPais());
        edittime_textf_titulos.setText(DbActions.timeSelecionado.getTitulos());
        edittime_textf_tecnico.setText(DbActions.timeSelecionado.getTecnico());
    }

    void setVerJogadoresController(VerJogadoresController verJogadoresController){
        this.verJogadoresController = verJogadoresController;
    }
    
    @FXML
    void initialize(){
        setTexts();
    }

}
