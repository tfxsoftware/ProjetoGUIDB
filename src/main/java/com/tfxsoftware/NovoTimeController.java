package com.tfxsoftware;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class NovoTimeController extends MainApp{



    @FXML
    private TextField novotime_textf_tecnico;

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
    private Label novotime_label_tecnico;

    @FXML
    private Label novotime_label_titulos;

    @FXML
    private TextField novotime_textf_nome;

    @FXML
    private TextField novotime_textf_pais;

    @FXML
    private TextField novotime_textf_titulos;

    private Alert alertbox = new Alert(AlertType.NONE);

    @FXML
    void registrar_time(ActionEvent event) {
        if (novotime_textf_nome.getText().equals("") ||
            novotime_textf_pais.getText().equals("") ||
            novotime_textf_titulos.getText().equals("") ||
            novotime_textf_tecnico.getText().equals("")) {
            alertbox.setAlertType(AlertType.ERROR);
            alertbox.setContentText("Por favor, preencha todos os campos!");
            alertbox.show();
        }
        else{
            if(DbActions.addTime(novotime_textf_nome.getText(), novotime_textf_pais.getText(), novotime_textf_titulos.getText(), novotime_textf_tecnico.getText())){
                novotime_textf_nome.setText("");
                novotime_textf_pais.setText("");
                novotime_textf_titulos.setText("");
                novotime_textf_tecnico.setText("");
                alertbox.setAlertType(AlertType.INFORMATION);
                alertbox.setContentText("Time registrado com sucesso!");
                alertbox.show();
            }
            else{
                alertbox.setAlertType(AlertType.ERROR);
                alertbox.setContentText("Time com o mesmo nome já registrado!");
                alertbox.show();
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        VerTimesController.voltarNovoTime();
    }

    @FXML
    public void initialize() {
    
    }

}
