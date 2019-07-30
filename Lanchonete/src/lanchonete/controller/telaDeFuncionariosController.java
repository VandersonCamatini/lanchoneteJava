/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lanchonete.DAO.DAOFactory;
import lanchonete.Lanchonete;
import lanchonete.model.Funcionario;

/**
 *
 * @author Bileko
 */
public class telaDeFuncionariosController implements Initializable {
    
    @FXML
    private TextField tfPesquisar;
    @FXML
    private TableColumn<Funcionario,Integer> tbcCodigo;
    @FXML
    private TableColumn<Funcionario, String> tbcNome;
    @FXML
    private TableColumn<Funcionario, String> tbcTipo;
    @FXML
    private TableColumn<Funcionario, String> tbcCPF;
    @FXML
    private TableView<Funcionario> tbFuncionarios;
    
    Funcionario objeto;
    @FXML
    private void btnCriarNovoFuncionario(ActionEvent event) {
        Lanchonete.mudarTela("cadastrarFuncionario");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {  
                tbFuncionarios.setItems(FXCollections.observableList(DAOFactory.getFuncionarioDAO().getAll()));
            } catch (SQLException ex) {
                Logger.getLogger(cadastrarFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = Lanchonete.criarAlertaSql(ex);
            }
    }

    @FXML
    private void ENTER(KeyEvent event) { 
        if (event.getCode() == KeyCode.ENTER) {
            try {  
                tbFuncionarios.setItems(FXCollections.observableList(DAOFactory.getFuncionarioDAO().procurarFuncionarios(tfPesquisar.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(cadastrarFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = Lanchonete.criarAlertaSql(ex);
            }
        }
    }

    @FXML
    private void selectedRowOnAction(MouseEvent event) {
         if (event.getClickCount() == 2) {
            objeto = tbFuncionarios.getSelectionModel().getSelectedItem();
            Lanchonete.setTransferirObjetoFuncionario(objeto);
            Lanchonete.mudarTela("atualizarFuncionario");
        }
    }
    
}   
