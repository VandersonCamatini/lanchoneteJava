/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lanchonete.DAO.DAOFactory;
import lanchonete.Lanchonete;
import lanchonete.model.Venda;

/**
 *
 * @author Bileko
 */
public class telaInicialController implements Initializable {

    @FXML
    private TableColumn<Venda, Integer> tbcCliente;
    @FXML
    private TableColumn<Venda, String> tbcData;
    @FXML
    private TableColumn<Venda, Double> tbcValor;
    @FXML
    private TableColumn<Venda, String> tbcHora;
    @FXML
    private TableColumn<Venda, Integer> tbcNumeroDoCliente;

    @FXML
    private TabPane tbpJanelas = null;
    
    @FXML
    private Menu btnVendas;
    @FXML
    private TextField tfPesquisar;
    @FXML
    private TableView<Venda> tbReservas;

    private FXMLLoader cargaDoSceneFuncionario = null;

    private AnchorPane painelSceneFuncionario = null;

    Tab abaFuncionario = null;
    
    private FXMLLoader cargaDoSceneCliente = null;

    private AnchorPane painelSceneCliente = null;

    Tab abaCliente = null;
    
    private FXMLLoader cargaDoSceneProduto = null;

    private AnchorPane painelSceneProduto = null;

    Tab abaProduto = null;
    
    private FXMLLoader cargaDoSceneVendas = null;

    private AnchorPane painelSceneVendas = null;

    Tab abaVendas = null;
    
    private FXMLLoader cargaDoSceneVenda = null;

    private AnchorPane painelSceneVenda = null;

    Tab abaVenda = null;
    
    
    
    @FXML
    private void funcionarioOnAction(ActionEvent event) {
        
        cargaDoSceneFuncionario = new FXMLLoader(getClass().getResource("/lanchonete/view/telaDeFuncionarios.fxml"));
        try {
            if (abaFuncionario == null) {
                abaFuncionario = new Tab("Tela De Funcionários");
            }
            if (painelSceneFuncionario == null) {
                painelSceneFuncionario = cargaDoSceneFuncionario.load();
            }
            abaFuncionario.setContent(painelSceneFuncionario);
            abaFuncionario.setOnCloseRequest((eventClose) -> {
                Alert alerta = Lanchonete.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                if (alerta.showAndWait().get() != ButtonType.YES) {
                    eventClose.consume();
                } else {
                    tbpJanelas.getTabs().remove(abaFuncionario);
                    painelSceneFuncionario = null;
                }
            });
            if (tbpJanelas.getTabs().contains(abaFuncionario) == false) {
                tbpJanelas.getTabs().add(abaFuncionario);
            }
            tbpJanelas.getSelectionModel().select(abaFuncionario);
        } catch (IOException ex) {
            Logger.getLogger(telaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = Lanchonete.criarAlertaIOEx(ex);
        }
    }

    @FXML
    private void clienteOnAction(ActionEvent event) {
        cargaDoSceneCliente = new FXMLLoader(getClass().getResource("/lanchonete/view/telaDeClientes.fxml"));
        try {
            if (abaCliente == null) {
                abaCliente = new Tab("Tela De Clientes");
            }
            if (painelSceneCliente == null) {
                painelSceneCliente = cargaDoSceneCliente.load();
            }
            abaCliente.setContent(painelSceneCliente);
            abaCliente.setOnCloseRequest((eventClose) -> {
                Alert alerta = Lanchonete.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                if (alerta.showAndWait().get() != ButtonType.YES) {
                    eventClose.consume();
                } else {
                    tbpJanelas.getTabs().remove(abaCliente);
                    painelSceneCliente = null;
                }
            });
            if (tbpJanelas.getTabs().contains(abaCliente) == false) {
                tbpJanelas.getTabs().add(abaCliente);
            }
            tbpJanelas.getSelectionModel().select(abaCliente);
        } catch (IOException ex) {
            Logger.getLogger(telaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = Lanchonete.criarAlertaIOEx(ex);
        }
    }

    @FXML
    private void produtoOnAction(ActionEvent event) {
        cargaDoSceneProduto = new FXMLLoader(getClass().getResource("/lanchonete/view/telaDeProdutos.fxml"));
        try {
            if (abaProduto == null) {
                abaProduto = new Tab("Tela De Produtos");
            }
            if (painelSceneProduto == null) {
                painelSceneProduto = cargaDoSceneProduto.load();
            }
            abaProduto.setContent(painelSceneProduto);
            abaProduto.setOnCloseRequest((eventClose) -> {
                Alert alerta = Lanchonete.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                if (alerta.showAndWait().get() != ButtonType.YES) {
                    eventClose.consume();
                } else {
                    tbpJanelas.getTabs().remove(abaProduto);
                    painelSceneProduto = null;
                }
            });
            if (tbpJanelas.getTabs().contains(abaProduto) == false) {
                tbpJanelas.getTabs().add(abaProduto);
            }
            tbpJanelas.getSelectionModel().select(abaProduto);
        } catch (IOException ex) {
            Logger.getLogger(telaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = Lanchonete.criarAlertaIOEx(ex);
        }
    }

    @FXML
    private void vendasOnAction(ActionEvent event) {
        cargaDoSceneVendas = new FXMLLoader(getClass().getResource("/lanchonete/view/telaDeVendas.fxml"));
        try {
            if (abaVendas == null) {
                abaVendas = new Tab("Tela De Vendas"); 
            }
            if (painelSceneVendas == null) {
                painelSceneVendas = cargaDoSceneVendas.load();
            }
            abaVendas.setContent(painelSceneVendas);
            abaVendas.setOnCloseRequest((eventClose) -> {
                Alert alerta = Lanchonete.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
                if (alerta.showAndWait().get() != ButtonType.YES) {
                    eventClose.consume();
                } else {
                    tbpJanelas.getTabs().remove(abaVendas);
                    painelSceneVendas = null;
                }
            });
            if (tbpJanelas.getTabs().contains(abaVendas) == false) {
                tbpJanelas.getTabs().add(abaVendas);
            }
            tbpJanelas.getSelectionModel().select(abaVendas);
        } catch (IOException ex) {
            Logger.getLogger(telaDeVendasController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = Lanchonete.criarAlertaIOEx(ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {  
                tbReservas.setItems(FXCollections.observableList(DAOFactory.getVendaDAO().pegarReservas()));
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
        
       
       tbcNumeroDoCliente.setCellFactory((TableColumn<Venda, Integer> param) -> {
            TableCell cell = new TableCell<Venda, Integer>() {

                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    setGraphic(null);
                    if (!empty) {
                        if (item == null || item == 0) {
                            Lanchonete.criarAlerta("INFORMATION", "Sem número do cliente");
                        } else {
                            try {
                                setText(DAOFactory.getVendaDAO().pegarNumeroCliente(item));
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
                tbReservas.setItems(FXCollections.observableList(DAOFactory.getVendaDAO().procurarReservas(tfPesquisar.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(telaInicialController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = Lanchonete.criarAlertaSql(ex);
            }
        }
    }

    
}
