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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import lanchonete.DAO.DAOFactory;
import lanchonete.Lanchonete;
import lanchonete.controller.cadastrarFuncionarioController;
import lanchonete.model.ItensVenda;
import lanchonete.model.Produto;
import lanchonete.model.Venda;

/**
 * FXML Controller class
 *
 * @author Bratva
 */
public class cadastrarVendaController implements Initializable {

    @FXML
    private AnchorPane paneVenda;
    @FXML
    private ComboBox<Integer> comboCliente;
    @FXML
    private ComboBox<Integer> comboProduto;
    @FXML
    private Button btnSalvarItem;
    @FXML
    private TextField txtQtdProduto;
    @FXML
    private Button btnFinalizarCompra;
    @FXML
    private TableView<ItensVenda> tableItems;
    @FXML
    private TableColumn<ItensVenda, Integer> tblColumnProduto;
    @FXML
    private Button btnAdicionarItem;
    @FXML
    private Button btnCancelarAcao;
    @FXML
    private TextField txtValorTotal;
    @FXML
    private Button btnCadastrarVenda;
    @FXML
    private CheckBox cbPedido;
    @FXML
    private CheckBox cbReserva;

    /**
     * Initializes the controller class.
     */
    Venda novaVenda = null;
    ItensVenda novoItem = null;

    Boolean estoqueInsuficiente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("entrou");
        try {
            fillComboBoxes("all");
        } catch (SQLException ex){
            Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova venda e preencha todos os campos.");
            alerta.showAndWait();
        }
        mascararComboBox();
        mascararTableView();
    }

    @FXML
    private void btnSalvarItemOnAction(ActionEvent event) {
        try {
            qtdProdutoEmEstoque(novoItem);
        } catch (RuntimeException ex) {
            Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Quantidade em estoque do produto insuficiente");
            alerta.showAndWait();
        }
        if (estoqueInsuficiente) {
            return;
        }

        unbindFieldsItem_Venda(novoItem);

        try {
            novoItem.calculaValorItem();
        } catch (SQLException ex) {
            Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Erro ao calcular o valor.");
            alerta.showAndWait();
        }
        tableItems.getItems().add(novoItem);
        novaVenda.getItens().add(novoItem);
        novaVenda.calculaValorTotalCompra();
        novoItem = null;
        limparFields("itemvenda");
    }

    @FXML
    private void btnFinalizarCompraOnAction(ActionEvent event) {
        unbindFieldsVenda(novaVenda);
        unbindFieldsItem_Venda(novoItem);

        try {
            if (novaVenda != null) {

                if (cbPedido.isSelected() == false && cbReserva.isSelected() == false) {
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro selecione a caixa pedido ou reserva de acordo com o tipo do seu perfil.");
                    alerta.showAndWait();
                } else {
                    if (!verificarFields()) {
                        novaVenda.setCodigoFuncionario(loginController.objeto.getCodigo());
                        novaVenda.setDataEmissao(Lanchonete.getDateTime());
                        novaVenda.setHora(Lanchonete.getHora());
                        novaVenda.calculaValorTotalCompra();
                        DAOFactory.getVendaDAO().save(novaVenda);

                        Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Venda criada e salva, pode fechar a janela.");
                        alerta.showAndWait();
                        cbPedido.setSelected(false);
                        cbReserva.setSelected(false);
                        
                        btnCancelarAcaoOnAction(null);
                        fillComboBoxes("produto");
                    } else {
                        Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Erro, para poder salvar preencha todos os campos corretamente.");
                        alerta.showAndWait();
                    }
                }
            } else {
                Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova venda e preencha todos os campos.");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnAdicionarItemOnAction(ActionEvent event) {
        if (novoItem == null) {
            novoItem = new ItensVenda();
        }
        bindFieldsItem_Venda(novoItem);
    }

    @FXML
    private void btnCancelarAcaoOnAction(ActionEvent event) {
        limparFields("venda");
        limparFields("itemvenda");
        novoItem = null;
        novaVenda = null;
        limpaTableItems();
        cbPedido.setSelected(false);
        cbReserva.setSelected(false);
    }

    @FXML
    private void btnCadastrarVendaOnAction(ActionEvent event) {
        if (novaVenda == null && novoItem == null) {
            novaVenda = new Venda();
            novoItem = new ItensVenda();
        }
        bindFieldsVenda(novaVenda);
        bindFieldsItem_Venda(novoItem);
    }

    @FXML
    private void cbPedidoOnaction(ActionEvent event) {
        if (cbPedido.isSelected()) {
            novaVenda.setTipo("Pedido");
            cbReserva.setSelected(false);
            cbPedido.setSelected(true);
        }
    }

    @FXML
    private void cbReservaOnAction(ActionEvent event) {
        if (cbReserva.isSelected()) {
            novaVenda.setTipo("Reserva");
            cbReserva.setSelected(true);
            cbPedido.setSelected(false);
        }
    }

    public void qtdProdutoEmEstoque(ItensVenda item) throws RuntimeException {

        Produto oUmProduto;
        try {
            oUmProduto = DAOFactory.getProdutoDAO().pegarProduto(item.getCodigoProduto());
            if (item.getQntdComprada() > oUmProduto.getQntd()) {
                estoqueInsuficiente = true;
                throw new RuntimeException("Quantidade em estoque do produto insuficiente" + " (" + oUmProduto.getQntd() + ")");
            } else {
                estoqueInsuficiente = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = Lanchonete.criarAlerta("INFORMATION", ex.getMessage());
            alerta.showAndWait();
        }
    }

    private void bindFieldsVenda(Venda venda) {
        if (venda != null) {
            comboCliente.valueProperty().bindBidirectional(venda.codigoClienteProperty());
            txtValorTotal.textProperty().bindBidirectional(venda.valorCompraProperty(), new NumberStringConverter());
        }

    }

    private void unbindFieldsVenda(Venda venda) {
        if (venda != null) {
            comboCliente.valueProperty().unbindBidirectional(venda.codigoClienteProperty());
            txtValorTotal.textProperty().unbindBidirectional(venda.valorCompraProperty());
        }
    }

    private void bindFieldsItem_Venda(ItensVenda itemvenda) {
        if (itemvenda != null) {
            txtQtdProduto.textProperty().bindBidirectional(itemvenda.qntdCompradaProperty(), new NumberStringConverter());
            comboProduto.valueProperty().bindBidirectional(itemvenda.codigoProdutoProperty());
            txtQtdProduto.setText(null);
        }

    }

    private void unbindFieldsItem_Venda(ItensVenda itemvenda) {
        if (itemvenda != null) {
            txtQtdProduto.textProperty().unbindBidirectional(itemvenda.qntdCompradaProperty());
            comboProduto.valueProperty().unbindBidirectional(itemvenda.codigoProdutoProperty());
        }
    }

    private void fillComboBoxes(String combo) throws SQLException {
        switch (combo) {
            case "produto":
                comboProduto.getItems().clear();
                FXCollections.observableArrayList(DAOFactory.getProdutoDAO().getAll()).forEach(produto -> {

                    comboProduto.getItems().add(produto.getCodigo());
                });
                break;

         
            case "cliente":
                comboCliente.getItems().clear();
                FXCollections.observableArrayList(DAOFactory.getClienteDAO().getAll()).forEach(cliente -> {
                    comboCliente.getItems().add(cliente.getCodigo());
                });
                break;

            case "all":
                comboProduto.getItems().clear();
                
                comboCliente.getItems().clear();
                FXCollections.observableArrayList(DAOFactory.getProdutoDAO().getAll()).forEach(produto -> {
                    comboProduto.getItems().add(produto.getCodigo());
                });
                FXCollections.observableArrayList(DAOFactory.getClienteDAO().getAll()).forEach(cliente -> {
                    comboCliente.getItems().add(cliente.getCodigo());
                });

                
                break;
        }
    }

    private void mascararComboBox() {
        comboCliente.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
            @Override
            public ListCell<Integer> call(ListView<Integer> l) {
                return new ListCell<Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            try {
                                setText(DAOFactory.getClienteDAO().pegarCliente(item).getIdentificador() + "-" + DAOFactory.getClienteDAO().pegarCliente(item).getNome());
                            } catch (SQLException ex) {
                                Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
                                Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova venda e preencha todos os campos.");
                                alerta.showAndWait();
                            }
                        }
                    }
                };
            }
        });

        comboCliente.setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                if (object != null && object != 0) {
                    try {
                        
                        return DAOFactory.getClienteDAO().pegarCliente(object).getIdentificador() + "-" + DAOFactory.getClienteDAO().pegarCliente(object).getNome();
                    } catch (SQLException ex) {
                        Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
                        Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova venda e preencha todos os campos.");
                        alerta.showAndWait();
                    }
                }

                return null;
            }

            @Override
            public Integer fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    try {
                        Integer c;
                        
                        c = DAOFactory.getClienteDAO().pegarClientePeloIdentificador(string.substring(0, 11)).getCodigo();
                        
                        return c;
                    } catch (SQLException ex) {
                        Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
                        Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova venda e preencha todos os campos.");
                        alerta.showAndWait();
                    }
                    
                }
                return null;
            }
        });

        comboProduto.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
            @Override
            public ListCell<Integer> call(ListView<Integer> l) {
                return new ListCell<Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        getStyleClass().remove("sem-estoque");
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Produto selectedProduct;
                            try {
                                selectedProduct = DAOFactory.getProdutoDAO().pegarProduto(item);
                                if (selectedProduct.getQntd() <= 0) {
                                    getStyleClass().add("sem-estoque");
                                    setText(item.toString() + "-" + selectedProduct.getNome() + "[SEM ESTOQUE]");
                                } else {
                                    setText(item.toString() + "-" + selectedProduct.getNome());
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
                                Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova venda e preencha todos os campos.");
                                alerta.showAndWait();
                            }

                        }
                    }
                };
            }
        });

        comboProduto.setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                if (object != null && object != 0) {
                    try {
                        
                        return object.toString() + "-" + DAOFactory.getProdutoDAO().pegarProduto(object).getNome();
                    } catch (SQLException ex) {
                        Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
                        Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova venda e preencha todos os campos.");
                        alerta.showAndWait();
                    }
                }

                return null;
            }

            @Override
            public Integer fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    Integer p;
                    
                    p = Integer.parseInt(string.substring(0, 1));
                    
                    return p;
                }
                return null;
            }
        });

        
    }

    private void mascararTableView() {
        tblColumnProduto.setCellFactory((TableColumn<ItensVenda, Integer> param) -> {
            TableCell cell = new TableCell<ItensVenda, Integer>() {
                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    setGraphic(null);
                    if (!empty) {
                        if (item == null || item == 0) {
                            setText("Esperando");
                        } else {
                            try {
                                setText(DAOFactory.getProdutoDAO().pegarProduto(item).getNome());
                            } catch (SQLException ex) {
                                Logger.getLogger(cadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
                                Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova venda e preencha todos os campos.");
                                alerta.showAndWait();
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

    public Boolean verificarFields() {
        Boolean invalido = false;

        if (comboCliente.valueProperty().isNull().get()) {

            invalido = true;
        } else {

        }
        
        if(tableItems.getItems() == null || tableItems.getItems().isEmpty()){
            invalido = true;
        }

        return invalido;
    }

    private void limparFields(String tipo) {
        switch (tipo) {
            case "venda":
                comboCliente.setValue(null);
                comboProduto.setValue(null);
                txtQtdProduto.clear();
                txtValorTotal.clear();
                break;

            case "itemvenda":
                comboProduto.setValue(null);
                txtQtdProduto.clear();
                break;
        }

    }

    private void limpaTableItems() {
        if (!tableItems.getItems().isEmpty()) {
            tableItems.getItems().clear();
        }

    }

}
