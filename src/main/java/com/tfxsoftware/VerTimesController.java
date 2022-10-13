package com.tfxsoftware;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;




import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class VerTimesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> Tabela_Times;

    @FXML
    private Button button_novotime;

    @FXML
    private Button button_sair;

    @FXML
    private Button button_vertime;

    @FXML
    private TableColumn<Time, String> c_times_nome;

    @FXML
    private TableColumn<Time, String> c_times_pais;

    @FXML
    private TableColumn<Time, Integer> c_times_qtd;

    @FXML
    private TableColumn<Time, String> c_times_titulos;

    @FXML
    private Label label_times;

    private static Stage novo;

    @FXML
    void novo_time(ActionEvent event) throws IOException {
        Parent fxmlLoader = MainApp.loadFXML("novo_time");
        this.novo = new Stage();
        Scene scene = new Scene(fxmlLoader);
        novo.setTitle("Novo time");
        novo.setScene(scene);
        novo.show();
        popular();
    }

    @FXML
    void open_times(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
        MainApp.sair();
    }

    
    void popular(){
        MongoCollection<Document> collection = DbActions.getCollection("ProjetoAndre", "Times"); 
        MongoCursor<Document> cursor = collection.find().iterator();
        while(cursor.hasNext()){
            
            System.out.println(cursor.next().get("Nome"));
        }

            
    }

    static void voltar(){
        novo.close();
    }
    
    @FXML
    public void initialize() {

        popular();
    }
    

}
