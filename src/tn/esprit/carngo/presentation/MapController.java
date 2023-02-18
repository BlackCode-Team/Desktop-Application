/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.carngo.presentation;


import tn.esprit.carngo.entities.itineraire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author mhcab
 */
public class MapController implements Initializable {

    @FXML
    private TextField depart;
    @FXML
    private TextField arriver;
    @FXML
    private TextField temps;
    @FXML
    private Button map;
    @FXML
    private Button ajouter;
    @FXML
    private Button supp;
    @FXML
    private Button mod;
    private TableView<itineraire> tabitin;
    @FXML
    private TableColumn<itineraire, String> dep;
    @FXML
    private TableColumn<itineraire, String> ariv;
    @FXML
    private TableColumn<itineraire, Integer> dis;
    @FXML
    private TableColumn<itineraire, Integer> time;
      @FXML
    private TableColumn<itineraire, Integer> id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       
      
    }
       
    
}
