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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import lanchonete.DAO.DAOFactory;
import lanchonete.Lanchonete;
import lanchonete.model.Produto;

/**
 * FXML Controller class
 *
 * @author Bileko
 */
public class atualizarProdutoController implements Initializable {

    @FXML
    private TextField tfMarcaProduto;
    @FXML
    private TextField tfPrecoCompra;
    @FXML
    private TextField tfCodigo;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField tfNomeProduto;
    @FXML
    private Button btnCriarProduto;
    @FXML
    private TextField tfPrecoVenda;
    @FXML
    private TextField tfQuantidadeProduto;

    Produto produto = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSalvarAlteraçõesOnAction(ActionEvent event) {
        unbindDataObject(produto);
        if (produto != null) {
            if (verificaFields() == false) {
                try {
                    DAOFactory.getProdutoDAO().update(produto);
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Produto atualizado e salvo, pode fechar a janela.");
                    alerta.showAndWait();
                    apagarCampos();
                    produto = null;
                } catch (SQLException ex) {
                    Logger.getLogger(atualizarProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Erro, para poder salvar preencha todos os campos.");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão Iniciar alterações e preencha todos os campos.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnAtualizarProdutoOnAction(ActionEvent event) {
        produto = Lanchonete.getTransferirObjetoProduto();
        bindDataObject(produto);
    }

    private void bindDataObject(Produto produto) {
        if (produto != null) {
            tfCodigo.setText(produto.getCodigo().toString());
            tfNomeProduto.setText(produto.getNome());
            tfMarcaProduto.setText(produto.getMarca());
            tfPrecoCompra.setText(produto.getPrecoCompra().toString());
            tfPrecoVenda.setText(produto.getPrecoVenda().toString());
            tfQuantidadeProduto.setText(produto.qntdProperty().toString());
            tfCodigo.textProperty().bind(produto.codigoProperty().asString());
            tfNomeProduto.textProperty().bindBidirectional(produto.nomeProperty());
            tfMarcaProduto.textProperty().bindBidirectional(produto.marcaProperty());
            tfPrecoCompra.textProperty().bindBidirectional(produto.precoCompraProperty(), new NumberStringConverter());
            tfPrecoVenda.textProperty().bindBidirectional(produto.precoVendaProperty(), new NumberStringConverter());
            tfQuantidadeProduto.textProperty().bindBidirectional(produto.qntdProperty(), new NumberStringConverter());
        }
    }

    private void unbindDataObject(Produto produto) {
        if (produto != null) {
            tfCodigo.textProperty().unbind();
            tfNomeProduto.textProperty().unbindBidirectional(produto.nomeProperty());
            tfMarcaProduto.textProperty().unbindBidirectional(produto.marcaProperty());
            tfPrecoCompra.textProperty().unbindBidirectional(produto.precoCompraProperty());
            tfPrecoVenda.textProperty().unbindBidirectional(produto.precoVendaProperty());
            tfQuantidadeProduto.textProperty().unbindBidirectional(produto.qntdProperty());
        }
    }

    private void apagarCampos() {
        tfCodigo.setText(null);
        tfNomeProduto.setText(null);
        tfMarcaProduto.setText(null);
        tfPrecoCompra.setText(null);
        tfPrecoVenda.setText(null);
        tfQuantidadeProduto.setText(null);
    }

    private Boolean verificaFields() {
        Boolean invalido = false;

        if (tfNomeProduto.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (Lanchonete.stringContainsNumber(tfNomeProduto.getText()) == true) {
                invalido = true;
            }
        }

        if (tfMarcaProduto.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (Lanchonete.stringContainsNumber(tfMarcaProduto.getText()) == true) {
                invalido = true;
            }
        }

        if (tfPrecoCompra.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (Lanchonete.numberContainsString(tfPrecoCompra.getText()) == true) {
                invalido = true;
            }
        }

        if (tfPrecoVenda.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (Lanchonete.numberContainsString(tfPrecoVenda.getText()) == true) {
                invalido = true;
            }
        }

        if (tfQuantidadeProduto.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (Lanchonete.numberContainsString(tfQuantidadeProduto.getText()) == true) {
                invalido = true;
            }
        }

        return invalido;
    }
}
