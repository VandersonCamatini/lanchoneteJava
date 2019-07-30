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
import lanchonete.model.Produto;

/**
 *
 * @author Bileko
 */
public class telaDeProdutosController implements Initializable{

    @FXML
    private TextField tfPesquisar;
    @FXML
    private TableColumn<Produto, Integer> tbcCodigo;
    @FXML
    private TableView<Produto> tbProdutos;
    @FXML
    private TableColumn<Produto, String> tbcProduto;
    @FXML
    private TableColumn<Produto, String> tbcMarca;
    @FXML
    private TableColumn<Produto, Integer> tbcEstoque;
    @FXML
    private TableColumn<Produto, Double> tbcCompra;
    @FXML
    private TableColumn<Produto, Double> tbcVenda;

    Produto objeto = null;
    @FXML
    private void btnCriarNovoProduto(ActionEvent event) {
        Lanchonete.mudarTela("cadastrarProduto");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {  
                tbProdutos.setItems(FXCollections.observableList(DAOFactory.getProdutoDAO().getAll()));
            } catch (SQLException ex) {
                Logger.getLogger(cadastrarFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = Lanchonete.criarAlertaSql(ex);
            }
    }
    @FXML
    private void ENTER(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                System.out.println(tfPesquisar.getText());
                tbProdutos.setItems(FXCollections.observableList(DAOFactory.getProdutoDAO().procurarProdutos(tfPesquisar.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(cadastrarProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = Lanchonete.criarAlertaSql(ex);
            }
        }
    }

    @FXML
    private void selectedRowOnAction(MouseEvent event) {
        if (event.getClickCount() == 2) {
            objeto = tbProdutos.getSelectionModel().getSelectedItem();
            Lanchonete.setTransferirObjetoProduto(objeto);
            Lanchonete.mudarTela("atualizarProduto");
        }
    }
    
}
