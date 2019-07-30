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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lanchonete.DAO.DAOFactory;
import lanchonete.Lanchonete;
import lanchonete.model.Venda;

/**
 *
 * @author Bileko
 */
public class telaDeVendasController implements Initializable{

    @FXML
    private TextField tfPesquisar;
    @FXML
    private TableView<Venda> tbVendas;
    @FXML
    private TableColumn<Venda, Integer> tbcCodigo;
    @FXML
    private TableColumn<Venda, String> tbcData;
    @FXML
    private TableColumn<Venda, String> tbcHora;
    @FXML
    private TableColumn<Venda, Integer> tbcCliente;
    @FXML
    private TableColumn<Venda, String> tbcTipo;
    @FXML
    private TableColumn<Venda, Double> tbcValor;

    @FXML
    private void btnCriarNovaVenda(ActionEvent event) {
        Lanchonete.mudarTela("cadastrarVenda");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {  
                tbVendas.setItems(FXCollections.observableList(DAOFactory.getVendaDAO().getAll()));
            } catch (SQLException ex) {
                Logger.getLogger(cadastrarFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = Lanchonete.criarAlertaSql(ex);
            }
       
       tbcCliente.setCellFactory((TableColumn<Venda, Integer> param) -> {
            TableCell cell = new TableCell<Venda, Integer>() {

                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    setGraphic(null);
                    if (!empty) {
                        if (item == null || item == 0) {
                            Lanchonete.criarAlerta("INFORMATION", "Sem cliente");
                        } else {
                            try {
                                setText(DAOFactory.getVendaDAO().pegarNomeCliente(item));
                            } catch (SQLException ex) {
                                Logger.getLogger(telaInicialController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    
                }

                @Override
                public void updateSelected(boolean upd) {
                    super.updateSelected(upd);
                }

                private String getString() {
                    return getItem() == null ? "" : getItem().toString();
                }
            };
            return cell;
        });
    }
    
    @FXML
    private void ENTER(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                
                tbVendas.setItems(FXCollections.observableList(DAOFactory.getVendaDAO().procurarVendas(tfPesquisar.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(telaDeVendasController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = Lanchonete.criarAlertaSql(ex);
            }
        }
    }
    
}
