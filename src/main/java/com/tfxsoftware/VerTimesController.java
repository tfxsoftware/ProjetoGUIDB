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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VerTimesController extends MainApp{

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

    public Stage novotime = new Stage();

    public Stage abrirtime = new Stage();

    private Time selected = null;

    private Alert alertbox = new Alert(AlertType.NONE);

    @FXML
    void abrirNovoTime(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/novo_time.fxml"));
        Parent root = loader.load();
        NovoTimeController novoTimeController = loader.getController();
        novoTimeController.setVerTimesController(this);
        Scene scene = new Scene(root);
        novotime.setTitle("Novo time");
        novotime.setScene(scene);
        novotime.show();
        
    }

    @FXML
    void abrirTime(ActionEvent event) throws Exception {
        setTimeSelecionado();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/ver_jogadores.fxml"));
        Parent root1 = loader1.load();
        VerJogadoresController verJogadoresController = loader1.getController();
        verJogadoresController.setVerTimesController(this);
        Scene scene1 = new Scene(root1);
        abrirtime.setTitle(DbActions.timeSelecionado.getNome());
        abrirtime.setScene(scene1);
        abrirtime.show();
    }

    @FXML
    void sair(ActionEvent event) {
        MainApp.sair();
    }

    @FXML
    void atualizaTabela(ActionEvent event) {
        popularTimes();
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

    
    public void setTimeSelecionado() throws Exception{
        try{
            TablePosition pos = Tabela_Times.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            this.selected = Tabela_Times.getItems().get(row);
            DbActions.timeSelecionado = selected;
        }
        catch(Exception e){
            alertbox.setAlertType(AlertType.ERROR);
            alertbox.setContentText("Nem um time selecionado!");
            alertbox.show();
        }
    }

    @FXML
    public void initialize() {

        popularTimes();
    }
}
