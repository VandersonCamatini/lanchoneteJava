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
import lanchonete.model.Cliente;


public class telaDeClientesController implements Initializable{

    @FXML
    private TextField tfPesquisar;
    @FXML
    private TableColumn<Cliente, Integer> tbcCodigo;
    @FXML
    private TableColumn<Cliente, String> tbcNome;
    @FXML
    private TableColumn<Cliente, String> tbcTipo;
    @FXML
    private TableColumn<Cliente, String> tbcCPF;
    @FXML
    private TableColumn<Cliente, String> tbcMatricula;
    @FXML
    private TableView<Cliente> tbClientes;
    
    Cliente objeto;
    @FXML
    private void btnCriarNovoCliente(ActionEvent event) {
        Lanchonete.mudarTela("cadastrarCliente");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {  
                tbClientes.setItems(FXCollections.observableList(DAOFactory.getClienteDAO().getAll()));
            } catch (SQLException ex) {
                Logger.getLogger(cadastrarFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = Lanchonete.criarAlertaSql(ex);
            }
    }

    @FXML
    private void ENTER(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                tbClientes.setItems(FXCollections.observableList(DAOFactory.getClienteDAO().procurarClientes(tfPesquisar.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(cadastrarClienteController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = Lanchonete.criarAlertaSql(ex);
            }
        }
    }

    @FXML
    private void selectedRowOnAction(MouseEvent event) {
        if (event.getClickCount() == 2) {
            objeto = tbClientes.getSelectionModel().getSelectedItem();
            Lanchonete.setTransferirObjetoCliente(objeto);
            Lanchonete.mudarTela("atualizarCliente");
        }
    }
    
}
