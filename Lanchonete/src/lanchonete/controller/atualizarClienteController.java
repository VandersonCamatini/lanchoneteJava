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
import lanchonete.model.Cliente;
import lanchonete.Lanchonete;

/**
 *
 * @author Bileko
 */
public class atualizarClienteController {

    @FXML
    private TextField tfEmailCliente;
    @FXML
    private TextField tfIdentificadorCliente;
    @FXML
    private TextField tfTelefoneCliente;
    @FXML
    private TextField tfCodigo;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField tfNomeCliente;
    @FXML
    private Button btnCriarCliente;
    @FXML
    private CheckBox cbAluno = null;
    @FXML
    private CheckBox cbColaborador = null;
    @FXML
    private TextField tfMatricula;

    Cliente cliente = null;
    

    @FXML
    private void btnSalvarOnAction(ActionEvent event) {
        unbindDataObject(cliente);
        if (cliente != null) {
            if (verificaFields() == false) {
                try {
                    System.out.println(cliente.getCodigo());
                    if(cbColaborador.isSelected() == false){
                        cliente.setMatricula("Aluno não tem");
                    }
                    DAOFactory.getClienteDAO().update(cliente);
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Cliente atualizado e salvo, pode fechar a janela.");
                    alerta.showAndWait();
                    apagarCampos();
                    cliente = null;
                } catch (SQLException ex) {
                    Logger.getLogger(atualizarClienteController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void btnAtualizarClienteOnAction(ActionEvent event) {
        cliente = Lanchonete.getTransferirObjetoCliente();
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
            if(cliente.getTipo() == "Aluno"){
                tfMatricula.setEditable(false);
                tfMatricula.setDisable(true);
                cbAluno.setSelected(true);
                cbColaborador.setSelected(false);
            }else{
                tfMatricula.setEditable(true);
                tfMatricula.setDisable(false);
                cbColaborador.setSelected(true);
                cbAluno.setSelected(false);
            }
            tfCodigo.setText(cliente.getCodigo().toString());
            tfNomeCliente.setText(cliente.getNome());
            tfEmailCliente.setText(cliente.getEmail());
            tfTelefoneCliente.setText(cliente.getTelefone());
            tfIdentificadorCliente.setText(cliente.getIdentificador());
            tfMatricula.setText(cliente.getMatricula());
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
    
    private void apagarCampos(){
        tfCodigo.setText(null);
        tfNomeCliente.setText(null);
        tfEmailCliente.setText(null);
        tfTelefoneCliente.setText(null);
        tfIdentificadorCliente.setText(null);
        tfMatricula.setText(null);     
        cbColaborador.setSelected(false);
        cbAluno.setSelected(false);
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
