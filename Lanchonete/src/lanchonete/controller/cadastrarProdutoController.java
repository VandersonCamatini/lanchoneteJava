/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import lanchonete.DAO.DAOFactory;
import lanchonete.Lanchonete;
import lanchonete.model.Cliente;
import lanchonete.model.Produto;

/**
 *
 * @author Bileko
 */
public class cadastrarProdutoController {

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
    private TextField tfPrecoVenda;
    @FXML
    private TextField tfQuantidadeProduto;
    @FXML
    private Button btnCriarProduto;

    Produto produto = null;

    @FXML
    private void btnSalvarOnAction(ActionEvent event) {
        unbindDataObject(produto);
        try {
            if (produto != null) {
                if (verificaFields() == false) {
                    DAOFactory.getProdutoDAO().save(produto);
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Produto criado e salva, pode fechar a janela.");
                    alerta.showAndWait();
                    produto = null;
                    
                } else {
                    System.out.println(produto.getCodigo());
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Erro, para poder salvar preencha todos os campos corretamente.");
                    alerta.showAndWait();
                }

            } else {
                Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar novo produto e preencha todos os campos.");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(cadastrarProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alerta.showAndWait();
        }
    }

    @FXML
    private void btncCriarProdutoOnAction(ActionEvent event) {
        produto = new Produto();
        bindDataObject(produto);
    }

    private void bindDataObject(Produto produto) {
        if (produto != null) {
            tfCodigo.textProperty().bind(produto.codigoProperty().asString());
            tfNomeProduto.textProperty().bindBidirectional(produto.nomeProperty());
            tfMarcaProduto.textProperty().bindBidirectional(produto.marcaProperty());
            tfPrecoCompra.textProperty().bindBidirectional(produto.precoCompraProperty(), new NumberStringConverter());
            tfPrecoVenda.textProperty().bindBidirectional(produto.precoVendaProperty(), new NumberStringConverter());
            tfQuantidadeProduto.textProperty().bindBidirectional(produto.qntdProperty(), new NumberStringConverter());
            tfPrecoCompra.setText(null);
            tfPrecoVenda.setText(null);
            tfQuantidadeProduto.setText(null);      
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
