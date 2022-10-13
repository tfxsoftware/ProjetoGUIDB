package com.tfxsoftware;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;




import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VerTimesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Time> Tabela_Times;

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
    private TableColumn<Time, String> c_times_titulos;

    @FXML
    private TableColumn<Time, String> c_times_tecnico;

    @FXML
    private Label label_times;

    @FXML
    private Button times_button_atualizar;

    private static Stage novo;

    private Time selected = null;

    @FXML
    void novo_time(ActionEvent event) throws IOException {
        Parent fxmlLoader = MainApp.loadFXML("novo_time");
        this.novo = new Stage();
        Scene scene = new Scene(fxmlLoader);
        novo.setTitle("Novo time");
        novo.setScene(scene);
        novo.show();
        
    }

    @FXML
    void abrirJogadores(ActionEvent event) {
        getSelecionado();
        this.selected = null;
    }

    @FXML
    void sair(ActionEvent event) {
        MainApp.sair();
    }

    
    public void popularTimes(){
        ObservableList<Time> lista = FXCollections.observableArrayList();
        MongoCollection<Document> collection = DbActions.getCollection("ProjetoAndre", "Times"); 
        MongoCursor<Document> cursor = collection.find().iterator();
        while(cursor.hasNext()){
            Document object = cursor.next();
            String nome = (String) object.get("Nome");
            String pais = (String) object.get("Pais");
            String titulos = (String) object.get("Titulos");
            String tecnico = (String) object.get("Tecnico");
            Time time = new Time(nome, pais, titulos, tecnico);
            lista.add(time);
        }    
        
        
        c_times_nome.setCellValueFactory(new PropertyValueFactory<Time, String>("Nome"));
        c_times_pais.setCellValueFactory(new PropertyValueFactory<Time, String>("Pais"));
        c_times_titulos.setCellValueFactory(new PropertyValueFactory<Time, String>("Titulos"));
        c_times_tecnico.setCellValueFactory(new PropertyValueFactory<Time, String>("Tecnico"));
        
        Tabela_Times.setItems(lista);
        
         
    }
    
    void voltar(){
        novo.close();
    }


    @FXML
    void atualizaTabela(ActionEvent event) {
        popularTimes();
    }
    
    @FXML
    public void initialize() {

        popularTimes();
    }
    
    public Time getSelecionado(){
        TablePosition pos = Tabela_Times.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();

        // Item here is the table view type:
        this.selected = Tabela_Times.getItems().get(row);
        return selected;
    }
}
