package com.tfxsoftware;



import java.io.IOException;
import java.util.Optional;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VerJogadoresController extends MainApp{

    @FXML
    private TableView<Jogador> Tabela_Jogadores;

    @FXML
    private Button button_deletarjogador;

    @FXML
    private Button button_editarjogador;

    @FXML
    private Button button_editartime;

    @FXML
    private Button button_novojogador;

    @FXML
    private TableColumn<Jogador, String> c_jogadores_gols;

    @FXML
    private TableColumn<Jogador, String> c_jogadores_idade;

    @FXML
    private TableColumn<Jogador, String> c_jogadores_nome;

    @FXML
    private TableColumn<Jogador, String> c_jogadores_posicao;

    @FXML
    private Button jogadores_button_atualizar;

    @FXML
    private Button jogadores_button_voltar;

    @FXML
    private Label label_getnome;

    @FXML
    private Label label_getpais;

    @FXML
    private Label label_gettecnico;

    @FXML
    private Label label_gettitulos;

    @FXML
    private Label label_infotime;

    @FXML
    private Label label_jogadores;

    @FXML
    private Label label_nometime;

    @FXML
    private Label label_nometime_header;

    @FXML
    private Label label_paistime;

    @FXML
    private Label label_tecnicotime;

    @FXML
    private Label label_titulostime;

    public Stage novojogador = new Stage();

    public Stage editjogador;

    public Stage edittime = new Stage();
    
    private Alert alertbox = new Alert(AlertType.NONE);

    private Jogador selected;

    private VerTimesController verTimesController;

    @FXML
    void abrirEditarJogador(ActionEvent event) {

    }

    @FXML
    void abrirEditarTime(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editar_time.fxml"));
        Parent root = loader.load();
        EditTimeController editTimeController = loader.getController();
        editTimeController.setVerJogadoresController(this);
        Scene scene = new Scene(root);
        verTimesController.popularTimes();
        edittime.setTitle("Editar time");
        edittime.setScene(scene);
        edittime.show();
    }

    @FXML
    void abrirNovoJogador(ActionEvent event) throws IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/novo_jogador.fxml"));
        Parent root1 = loader1.load();
        NovoJogadorController NovoJogadorController = loader1.getController();
        NovoJogadorController.setVerJogadoresController(this);
        Scene scene = new Scene(root1);
        novojogador.setTitle("Novo jogador");
        novojogador.setScene(scene);
        novojogador.show();
    }

    @FXML
    void atualizaTabelaj(ActionEvent event) {
        popularJogadores();
        setTexts();
    }

    @FXML
    void deletarJogador(ActionEvent event) throws Exception {
        setJogadorSelecionado();
        alertbox.setAlertType(AlertType.CONFIRMATION);
        alertbox.setContentText("Tem certeza que deseja deletar jogador?");
        Optional<ButtonType> result = alertbox.showAndWait();
        if(result.get() == ButtonType.OK) {
            DbActions.deletaJogador(DbActions.jogadorSelecionado.toDocument());
            popularJogadores();
        }
        
    }

    @FXML
    void deletarTime(ActionEvent event) throws Exception {
        if (DbActions.timeSelecionado != null){
            alertbox.setAlertType(AlertType.CONFIRMATION);
            alertbox.setContentText("Tem certeza que deseja deletar o time " + DbActions.timeSelecionado.getNome() + "?");
            Optional<ButtonType> result = alertbox.showAndWait();
            if(result.get() == ButtonType.OK) {
                DbActions.deletaTimeESeusJogadores();
                DbActions.timeSelecionado = null;
                verTimesController.popularTimes();
                alertbox.setAlertType(AlertType.INFORMATION);
                alertbox.setContentText("Time deletado com sucesso!");
                alertbox.show();
                verTimesController.abrirtime.close();
            }
        }
        else {
            alertbox.setAlertType(AlertType.INFORMATION);
            alertbox.setContentText("Nem um time selecionado!");
            alertbox.show();
        }
    }

    @FXML
    void sair(ActionEvent event) {
        DbActions.jogadorSelecionado = null;
        verTimesController.popularTimes();
        verTimesController.abrirtime.close();

    }

    public void popularJogadores(){
        ObservableList<Jogador> lista = FXCollections.observableArrayList();
        MongoCollection<Document> collection = DbActions.getCollection("ProjetoAndre", "Jogadores"); 
        MongoCursor<Document> cursor = collection.find().iterator();
        while(cursor.hasNext()){
            Document object = cursor.next();
            String time = (String) object.get("Time");
            if (time.equals(DbActions.timeSelecionado.getNome())){
                String nome = (String) object.get("Nome");
                String idade = (String) object.get("Idade");
                String gols = (String) object.get("Gols");
                String posicao = (String) object.get("Posicao");
                Jogador jogador = new Jogador(nome, idade, gols, posicao, time);
                lista.add(jogador);
            }    
        }
        
        c_jogadores_nome.setCellValueFactory(new PropertyValueFactory<Jogador, String>("Nome"));
        c_jogadores_idade.setCellValueFactory(new PropertyValueFactory<Jogador, String>("Idade"));
        c_jogadores_gols.setCellValueFactory(new PropertyValueFactory<Jogador, String>("Gols"));
        c_jogadores_posicao.setCellValueFactory(new PropertyValueFactory<Jogador, String>("Posicao"));
        
        Tabela_Jogadores.setItems(lista);
    }


    public void setJogadorSelecionado() throws Exception{
        try{
            TablePosition pos = Tabela_Jogadores.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            this.selected = Tabela_Jogadores.getItems().get(row);
            DbActions.jogadorSelecionado = selected;
        }
        catch(Exception e){
            alertbox.setAlertType(AlertType.ERROR);
            alertbox.setContentText("Nem um jogador selecionado!");
            alertbox.show();
        }
    }
    
    public void setTexts(){
        label_nometime_header.setText(DbActions.timeSelecionado.getNome().toUpperCase());
        label_getnome.setText(DbActions.timeSelecionado.getNome());
        label_getpais.setText(DbActions.timeSelecionado.getPais());
        label_gettitulos.setText(DbActions.timeSelecionado.getTitulos());
        label_gettecnico.setText(DbActions.timeSelecionado.getTecnico());
    }

    public void setVerTimesController(VerTimesController verTimesController){
        this.verTimesController = verTimesController;
    }
    
    @FXML
    void initialize(){
        popularJogadores();
        setTexts();
    }

}
