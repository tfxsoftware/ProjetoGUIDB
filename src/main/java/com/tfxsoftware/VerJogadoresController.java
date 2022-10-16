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

public class VerJogadoresController {

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

    static Stage novojogador;
    
    private Alert alertbox = new Alert(AlertType.ERROR);

    private Jogador selected;

    @FXML
    void abrirEditarJogador(ActionEvent event) {

    }

    @FXML
    void abrirEditarTime(ActionEvent event) {

    }

    @FXML
    void abrirNovoJogador(ActionEvent event) throws IOException {
        Parent fxmlLoader = MainApp.loadFXML("novo_jogador");
        Stage novojogador = new Stage();
        Scene scene = new Scene(fxmlLoader);
        novojogador.setTitle("Novo time");
        novojogador.setScene(scene);
        novojogador.show();
    }

    @FXML
    void atualizaTabelaj(ActionEvent event) {
        popularJogadores();
    }

    @FXML
    void deletarJogador(ActionEvent event) throws Exception {
        setJogadorSelecionado();
        alertbox.setAlertType(AlertType.CONFIRMATION);
        alertbox.setContentText("Tem certeza que deseja deletar jogador?");
        Optional<ButtonType> result = alertbox.showAndWait();
        if(result.isPresent() || result.get() == ButtonType.OK) {
            DbActions.deletaJogador(DbActions.jogadorSelecionado.getNome());
        }
        
    }

    @FXML
    void deletarTime(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
        VerTimesController.voltarVerTime();
    }

    private void popularJogadores(){
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

    static void voltarNovoJogador(){
        novojogador.close();
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
    
    
    @FXML
    void initialize(){
        popularJogadores();
        label_nometime_header.setText(DbActions.timeSelecionado.getNome().toUpperCase());
        label_getnome.setText(DbActions.timeSelecionado.getNome());
        label_getpais.setText(DbActions.timeSelecionado.getPais());
        label_gettitulos.setText(DbActions.timeSelecionado.getTitulos());
        label_gettecnico.setText(DbActions.timeSelecionado.getTecnico());
    }

}
