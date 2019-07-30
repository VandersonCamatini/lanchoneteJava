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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lanchonete.DAO.DAOFactory;
import lanchonete.Lanchonete;
import lanchonete.model.Cliente;



public class cadastrarClienteController {

    @FXML
    private TextField tfCodigo;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCriarCliente;
    @FXML
    private CheckBox cbAluno;
    @FXML
    private CheckBox cbColaborador;
    
    Cliente cliente = null;
    @FXML
    private TextField tfEmailCliente;
    @FXML
    private TextField tfIdentificadorCliente;
    @FXML
    private TextField tfTelefoneCliente;
    @FXML
    private TextField tfNomeCliente;
    @FXML
    private TextField tfMatricula;
    @FXML
    private AnchorPane apCliente;
    
    public void initialize(URL location, ResourceBundle resources) {
       
    }

    @FXML
    private void btnSalvarOnAction(ActionEvent event) {
        unbindDataObject(cliente);
        try {
            if (cliente != null) {
                if (cbAluno.isSelected() == false && cbColaborador.isSelected() == false) {
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro selecione a caixa gerente ou vendedor de acordo com o tipo do seu perfil.");
                    alerta.showAndWait();
                } else {
                    if(verificaFields() == false){
                        System.out.println(tfMatricula.getText());
                        if(cbAluno.isSelected() == true && tfMatricula.getText() == null){
                            DAOFactory.getClienteDAO().saveAluno(cliente);
                            Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Conta criada e salva, pode fechar a janela.");
                            alerta.showAndWait();
                            cliente = null;
                        }else if(cbColaborador.isSelected() == true && tfMatricula.getText() != null){
                            DAOFactory.getClienteDAO().saveColaborador(cliente);
                            Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Conta criada e salva, pode fechar a janela.");
                            alerta.showAndWait();
                            cliente = null;
                        }
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
            Logger.getLogger(cadastrarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alerta.showAndWait();
        }
    }

    @FXML
    private void btncCriarAtendenteOnAction(ActionEvent event) {
        cliente = new Cliente();
        bindDataObject(cliente);
    }

    @FXML
    private void cbAlunoOnAction(ActionEvent event) {
         if (cbAluno.isSelected()) {
            cliente.setTipo("Aluno");
            cbColaborador.setSelected(false);
            cbAluno.setSelected(true);
            tfMatricula.setEditable(false);
            tfMatricula.setDisable(true);
        }
    }

    @FXML
    private void cbColaboradorOnAction(ActionEvent event) {
        if (cbColaborador.isSelected()) {
            cliente.setTipo("Colaborador");
            cbColaborador.setSelected(true);
            cbAluno.setSelected(false);
            tfMatricula.setEditable(true);
            tfMatricula.setDisable(false);
        }
    }
    
    
    private void bindDataObject(Cliente cliente) {
        if (cliente != null) {
            tfCodigo.textProperty().bind(cliente.codigoProperty().asString());
            tfNomeCliente.textProperty().bindBidirectional(cliente.nomeProperty());
            tfEmailCliente.textProperty().bindBidirectional(cliente.emailProperty());
            tfTelefoneCliente.textProperty().bindBidirectional(cliente.telefoneProperty());
            tfIdentificadorCliente.textProperty().bindBidirectional(cliente.identificadorProperty());
            tfMatricula.textProperty().bindBidirectional(cliente.matriculaProperty());
        }
    }

    private void unbindDataObject(Cliente cliente) {
        if (cliente != null) {
            tfCodigo.textProperty().unbind();
            tfNomeCliente.textProperty().unbindBidirectional(cliente.nomeProperty());
            tfEmailCliente.textProperty().unbindBidirectional(cliente.emailProperty());
            tfTelefoneCliente.textProperty().unbindBidirectional(cliente.telefoneProperty());
            tfIdentificadorCliente.textProperty().unbindBidirectional(cliente.identificadorProperty());
            tfMatricula.textProperty().unbindBidirectional(cliente.matriculaProperty());
        }
    }

    private Boolean verificaFields() {
        Boolean invalido = false;

        if(tfMatricula.textProperty().isNull().get() == true ){
            invalido = false;
        }
        
        if (tfNomeCliente.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (Lanchonete.stringContainsNumber(tfNomeCliente.getText()) == true) {
                invalido = true;
            }
        }
        
        if (tfTelefoneCliente.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (tfTelefoneCliente.getText().length() < 9 || tfTelefoneCliente.getText().length() > 11) {
                invalido = true;
            }
            if (Lanchonete.numberContainsString(tfTelefoneCliente.getText()) == true) {

                invalido = true;
            }
        }

        if (tfIdentificadorCliente.textProperty().isNull().get() == true) {
            invalido = true;
        } else {
            if (tfIdentificadorCliente.getText().length() < 11 || tfIdentificadorCliente.getText().length() > 14) {
                invalido = true;
            }
            if (Lanchonete.numberContainsString(tfIdentificadorCliente.getText()) == true) {
                invalido = true;
            }
        }

        if (tfEmailCliente.textProperty().isNull().get() == true) {

            invalido = true;
        }else {
            if (Lanchonete.stringContainsNumber(tfEmailCliente.getText()) == true) {
                invalido = true;
            }
        }
   
        return invalido;
    }
    
}
