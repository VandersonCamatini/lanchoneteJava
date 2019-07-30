/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import lanchonete.DAO.DAOFactory;
import lanchonete.Lanchonete;
import lanchonete.model.Funcionario;

/**
 *
 * @author Senai
 */
public class cadastrarFuncionarioController {

    @FXML
    private TextField tfSenhaFuncionario;
    @FXML
    private TextField tfEmailFuncionario;
    @FXML
    private TextField tfIdentificadorFuncionario;
    @FXML
    private TextField tfTelefoneFuncionario;
    @FXML
    private TextField tfUsuarioFuncionario;
    @FXML
    private TextField tfCodigo;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField tfNomeFuncionario;
    @FXML
    private Button btnCriarAtendente;
    @FXML
    private CheckBox cbGerente;
    @FXML
    private CheckBox cbVendedor;
    @FXML
    private TextField tfEnderecoFuncionario;
    @FXML
    private TextField tfCidadeFuncionario;
    @FXML
    private TextField tfEstadoFuncionario;
    @FXML
    private TextField tfPaisFuncionario;

    Funcionario funcionario = null;

    @FXML
    private void btnSalvarOnAction(ActionEvent event) {
        unbindDataObject(funcionario);
        try {
            if (funcionario != null) {    
                if (cbGerente.isSelected() == false && cbVendedor.isSelected() == false) {
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro selecione a caixa gerente ou vendedor de acordo com o tipo do seu perfil.");
                    alerta.showAndWait();
                } else {
                    if(verificaFields() == false){
                        DAOFactory.getFuncionarioDAO().save(funcionario);
                        Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Conta criada e salva, pode fechar a janela.");
                        alerta.showAndWait();
                        funcionario = null;
                    } else {
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Erro, para poder salvar preencha todos os campos corretamente.");
                    alerta.showAndWait();
                    }
                }
            } else {
                Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro clique no botão de Criar nova conta e preencha todos os campos.");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(cadastrarFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alerta.showAndWait();
        }
    }

    @FXML
    private void btncCriarAtendenteOnAction(ActionEvent event) {
        funcionario = new Funcionario();
        bindDataObject(funcionario);
    }

    @FXML
    private void cbGerenteOnAction(ActionEvent event) {
        if (cbGerente.isSelected()) {
            funcionario.setTipo("Gerente");
            cbVendedor.setSelected(false);
            cbGerente.setSelected(true);
        }
    }

    @FXML
    private void cbVendedorOnAction(ActionEvent event) {
        if (cbVendedor.isSelected()) {
            funcionario.setTipo("Vendedor");
            cbGerente.setSelected(false);
            cbVendedor.setSelected(true);
        }
    }

    private void bindDataObject(Funcionario funcionario) {
        if (funcionario != null) {
            
            tfCodigo.textProperty().bind(funcionario.codigoProperty().asString());
            tfNomeFuncionario.textProperty().bindBidirectional(funcionario.nomeProperty());
            tfEmailFuncionario.textProperty().bindBidirectional(funcionario.emailProperty());
            tfEnderecoFuncionario.textProperty().bindBidirectional(funcionario.enderecoProperty());
            tfTelefoneFuncionario.textProperty().bindBidirectional(funcionario.telefoneProperty());
            tfIdentificadorFuncionario.textProperty().bindBidirectional(funcionario.identificadorProperty());
            tfCidadeFuncionario.textProperty().bindBidirectional(funcionario.cidadeProperty());
            tfEstadoFuncionario.textProperty().bindBidirectional(funcionario.estadoProperty());
            tfPaisFuncionario.textProperty().bindBidirectional(funcionario.paisProperty());
            tfUsuarioFuncionario.textProperty().bindBidirectional(funcionario.loginProperty());
            tfSenhaFuncionario.textProperty().bindBidirectional(funcionario.senhaProperty());
        }
    }

    private void unbindDataObject(Funcionario funcionario) {
        if (funcionario != null) {
            tfCodigo.textProperty().unbind();
            tfNomeFuncionario.textProperty().unbindBidirectional(funcionario.nomeProperty());
            tfEmailFuncionario.textProperty().unbindBidirectional(funcionario.emailProperty());
            tfTelefoneFuncionario.textProperty().unbindBidirectional(funcionario.telefoneProperty());
            tfIdentificadorFuncionario.textProperty().unbindBidirectional(funcionario.identificadorProperty());
            tfUsuarioFuncionario.textProperty().unbindBidirectional(funcionario.loginProperty());
            tfSenhaFuncionario.textProperty().unbindBidirectional(funcionario.senhaProperty());
            tfEnderecoFuncionario.textProperty().unbindBidirectional(funcionario.enderecoProperty());
            tfCidadeFuncionario.textProperty().unbindBidirectional(funcionario.cidadeProperty());
            tfEstadoFuncionario.textProperty().unbindBidirectional(funcionario.estadoProperty());
            tfPaisFuncionario.textProperty().unbindBidirectional(funcionario.paisProperty());
        }
    }

    private Boolean verificaFields() {
        Boolean invalido = false;

        if (tfNomeFuncionario.textProperty().isNull().get() == true) {
            System.out.println(tfNomeFuncionario.textProperty().isNull().get());

            invalido = true;
        } else {
            if (Lanchonete.stringContainsNumber(tfNomeFuncionario.getText()) == true) {
                invalido = true;
            }
        }

        if (tfUsuarioFuncionario.textProperty().isNull().get() == true) {
            invalido = true;
        }
        
        if (tfSenhaFuncionario.textProperty().isNull().get() == true) {

            invalido = true;
        }
        
        if (tfTelefoneFuncionario.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (tfTelefoneFuncionario.getText().length() < 9 || tfTelefoneFuncionario.getText().length() > 11) {
                invalido = true;
            }
            if (Lanchonete.numberContainsString(tfTelefoneFuncionario.getText()) == true) {

                invalido = true;
            }
        }

        if (tfIdentificadorFuncionario.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (tfIdentificadorFuncionario.getText().length() < 11 || tfIdentificadorFuncionario.getText().length() > 14) {
                invalido = true;
            }
            if (Lanchonete.numberContainsString(tfIdentificadorFuncionario.getText()) == true) {
                invalido = true;
            }
        }

        if (tfEmailFuncionario.textProperty().isNull().get() == true) {

            invalido = true;
        }else {
            if (Lanchonete.stringContainsNumber(tfEmailFuncionario.getText()) == true) {
                invalido = true;
            }
        }

        
        
        if (tfEnderecoFuncionario.textProperty().isNull().get() == true) {

            invalido = true;
        }else {
            if (Lanchonete.stringContainsNumber(tfEnderecoFuncionario.getText()) == true) {
                invalido = true;
            }
        }
        
        if (tfCidadeFuncionario.textProperty().isNull().get() == true) {

            invalido = true;
        }else {
            if (Lanchonete.stringContainsNumber(tfCidadeFuncionario.getText()) == true) {
                invalido = true;
            }
        }
        
        if (tfEstadoFuncionario.textProperty().isNull().get() == true) {

            invalido = true;
        }else {
            if (Lanchonete.stringContainsNumber(tfEstadoFuncionario.getText()) == true) {
                invalido = true;
            }
        }
        
        if (tfPaisFuncionario.textProperty().isNull().get() == true) {

            invalido = true;
        }else {
            if (Lanchonete.stringContainsNumber(tfPaisFuncionario.getText()) == true) {
                invalido = true;
            }
        }

        return invalido;
    }
}
