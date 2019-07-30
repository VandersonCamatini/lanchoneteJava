/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lanchonete.DAO.DAOFactory;
import lanchonete.Lanchonete;
import lanchonete.model.Funcionario;

/**
 *
 * @author Senai
 */
public class loginController {

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private CheckBox cbGerente;
    @FXML
    private Button btnEnter;
    @FXML
    private Button btnExit;
    @FXML
    private CheckBox cbVendedor;

    static Funcionario objeto = null;

    @FXML
    private void cbGerenteOnAction(ActionEvent event) {
        if (cbGerente.isSelected()) {
            cbGerente.setSelected(true);
            cbVendedor.setSelected(false);
        }
    }


    @FXML
    private void btnEnterOnAction(ActionEvent event) {
        if (cbGerente.isSelected() == false && cbVendedor.isSelected() == false) {
            Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro selecione a caixa gerente ou vendedor de acordo com o tipo do seu perfil.");
            alerta.showAndWait();
        }
        if (cbGerente.isSelected() == true) {
            Boolean resultadoGerenteLogin = null;
            String resultadoGerenteSenha = null;
            try {
                resultadoGerenteLogin = DAOFactory.getFuncionarioDAO().procurarLoginGerente(tfUsername.getText());
                if (resultadoGerenteLogin == true) {
                    resultadoGerenteSenha = DAOFactory.getFuncionarioDAO().procurarSenhaGerente(tfUsername.getText());
                    if (resultadoGerenteSenha.equals(pfPassword.getText()) == true) {
                        List<Funcionario> gerente = DAOFactory.getFuncionarioDAO().pegarObjeto(tfUsername.getText(), pfPassword.getText());
                        for (Funcionario g : gerente) {
                            Integer codigo = g.getCodigo();
                            String nome = g.getNome();
                            String telefone = g.getTelefone();
                            String login = g.getLogin();
                            String senha = g.getSenha();
                            String email = g.getEmail();
                            String tipo = g.getTipo();
                            String endereco = g.getEndereco();
                            String cidade = g.getCidade();
                            String estado = g.getEstado();
                            String pais = g.getPais();
                            String identificador = g.getIdentificador();
                            objeto = new Funcionario(codigo, nome, telefone, login, senha, email, tipo, endereco, cidade, estado, pais, identificador);
                        }
                        Lanchonete.setTransferirObjetoFuncionario(objeto);
                        Lanchonete.mudarTela("telaPrincipal");
                    } else {
                        Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Sua senha está incorreta.");
                        alerta.showAndWait();
                    }
                } else {
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Seu usuário está incorreto.");
                    alerta.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cbVendedor.isSelected() == true) {
            Boolean resultadoVendedorLogin = null;
            String resultadoVendedorSenha = null;
            try {
                resultadoVendedorLogin = DAOFactory.getFuncionarioDAO().procurarLoginVendedor(tfUsername.getText());
                if (resultadoVendedorLogin == true) {
                    resultadoVendedorSenha = DAOFactory.getFuncionarioDAO().procurarSenhaVendedor(tfUsername.getText());
                    if (resultadoVendedorSenha.equals(pfPassword.getText()) == true) {
                       List<Funcionario> vendedor = DAOFactory.getFuncionarioDAO().pegarObjeto(tfUsername.getText(), pfPassword.getText());
                        for (Funcionario v : vendedor) {
                            Integer codigo = v.getCodigo();
                            String nome = v.getNome();
                            String telefone = v.getTelefone();
                            String login = v.getLogin();
                            String senha = v.getSenha();
                            String email = v.getEmail();
                            String tipo = v.getTipo();
                            String endereco = v.getEndereco();
                            String cidade = v.getCidade();
                            String estado = v.getEstado();
                            String pais = v.getPais();
                            String identificador = v.getIdentificador();
                            objeto = new Funcionario(codigo, nome, telefone, login, senha, email, tipo, endereco, cidade, estado, pais, identificador);
                        }
                        Lanchonete.setTransferirObjetoFuncionario(objeto);
                        Lanchonete.mudarTela("telaPrincipalVendedor");
                    } else {
                        Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Sua senha está incorreta.");
                        alerta.showAndWait();
                    }
                } else {
                    Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Seu usuário está incorreto.");
                    alerta.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = Lanchonete.criarAlerta("INFORMATION", "Primeiro selecione a caixa gerente ou vendedor de acordo com o tipo do seu perfil.");
            alerta.showAndWait();
        }
        System.out.println("deu");
    }

    @FXML
    private void btnExitOnAction(ActionEvent event) {
        Alert alerta = Lanchonete.criarAlerta("CONFIRMATION", "Deseja realmente fechar a janela?");
        if (alerta.showAndWait().get() == ButtonType.YES) {
            System.exit(0);
        }
    }

    @FXML
    private void cbVendedorOnAction(ActionEvent event) {
        if (cbVendedor.isSelected()) {
            cbVendedor.setSelected(true);
            cbGerente.setSelected(false);

        }
    }

}
