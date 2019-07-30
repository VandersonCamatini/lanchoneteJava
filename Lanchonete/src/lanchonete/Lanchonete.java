/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lanchonete.model.Cliente;
import lanchonete.model.Funcionario;
import lanchonete.model.Produto;


public class Lanchonete extends Application {
    
    private static Stage stage;
    private static Stage outroStage;
    private static Stage outroStage2;
    private static Stage outroStage3;
    private static Stage outroStage4;
    private static Scene login;
    private static Scene telaPrincipal;
    private static Scene cadastrarCliente;
    private static Scene cadastrarFuncionario;
    private static Scene cadastrarProduto;
    private static Scene cadastrarVenda;
    private static Scene telaPrincipalVendedor;
    private static Scene atualizarCliente;
    private static Scene atualizarProduto;
    private static Scene atualizarFuncionario;
    
    public static Funcionario objetoFuncionario = null;
    public static Produto objetoProduto = null;
    public static Cliente objetoCliente = null;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        outroStage = new Stage();
        outroStage.setResizable(false);
        outroStage2 = new Stage();
        outroStage2.setResizable(false);
        outroStage3= new Stage();
        outroStage3.setResizable(false);
        outroStage4= new Stage();
        outroStage4.setResizable(false);
        
        stage = primaryStage;
        stage.setResizable(false);

        Parent fxmlLogin = FXMLLoader.load(Lanchonete.class.getResource("view/login.fxml"));
        login = new Scene(fxmlLogin, 300, 300);
        
        stage.setTitle("Login");
        stage.setScene(login);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Scene mudarTela(String tela) throws IOException{

        Parent fxmlPrincipal = FXMLLoader.load(Lanchonete.class.getResource("view/telaPrincipal.fxml"));
        telaPrincipal = new Scene(fxmlPrincipal, 745, 450);
        
        Parent fxmlCadastrarCliente = FXMLLoader.load(Lanchonete.class.getResource("view/cadastrarCliente.fxml"));
        cadastrarCliente = new Scene(fxmlCadastrarCliente, 800, 450);
        
        Parent fxmlCadastrarFuncionario = FXMLLoader.load(Lanchonete.class.getResource("view/cadastrarFuncionario.fxml"));
        cadastrarFuncionario = new Scene(fxmlCadastrarFuncionario, 800, 450);
        
        Parent fxmlCadastrarProduto = FXMLLoader.load(Lanchonete.class.getResource("view/cadastrarProduto.fxml"));
        cadastrarProduto = new Scene(fxmlCadastrarProduto, 800, 450);
        
        Parent fxmlPrincipalVendedor = FXMLLoader.load(Lanchonete.class.getResource("view/telaPrincipalVendedor.fxml"));
        telaPrincipalVendedor = new Scene(fxmlPrincipalVendedor, 735, 450);

        Parent fxmlAtualizarCliente = FXMLLoader.load(Lanchonete.class.getResource("view/atualizarCliente.fxml"));
        atualizarCliente = new Scene(fxmlAtualizarCliente, 800, 450);
        
        Parent fxmlAtualizarProduto = FXMLLoader.load(Lanchonete.class.getResource("view/atualizarProduto.fxml"));
        atualizarProduto = new Scene(fxmlAtualizarProduto, 800, 450);
        
        Parent fxmlCadastrarVenda = FXMLLoader.load(Lanchonete.class.getResource("view/cadastrarVenda.fxml"));
        cadastrarVenda = new Scene(fxmlCadastrarVenda, 600, 450);
        Scene cenaEscolhida = null;
        switch(tela){
            case "telaPrincipal":
                stage.setTitle("Tela Principal");
                stage.setScene(telaPrincipal);
                cenaEscolhida = telaPrincipal;
                break;
            case "cadastrarCliente":
                outroStage.setTitle("Cadastrar Cliente");
                outroStage.setScene(cadastrarCliente);
                outroStage.show();
                cenaEscolhida = cadastrarCliente;
                break;
            case "cadastrarFuncionario":
                outroStage.setTitle("Cadastrar Funcionário");
                outroStage.setScene(cadastrarFuncionario);
                outroStage.show();
                cenaEscolhida = cadastrarFuncionario;
                break;
            case "cadastrarProduto":
                outroStage.setTitle("Cadastrar Produto");
                outroStage.setScene(cadastrarProduto);
                outroStage.show();
                cenaEscolhida = cadastrarProduto;
                break;
            case "cadastrarVenda":
               
                outroStage4.setTitle("Cadastrar Venda");
                outroStage4.setScene(cadastrarVenda);
                outroStage4.show();
                cenaEscolhida = cadastrarVenda;
                break;
            case "telaPrincipalVendedor":
                stage.close();
                outroStage2.setTitle("Tela Principal");
                outroStage2.setScene(telaPrincipalVendedor);
                outroStage2.show();
                cenaEscolhida = telaPrincipalVendedor;
                break;
            case "atualizarCliente":
                outroStage3.setTitle("Atualizar Cliente");
                outroStage3.setScene(atualizarCliente);
                outroStage3.show();
                cenaEscolhida = atualizarCliente;
                break;
            case "atualizarProduto":
                outroStage3.setTitle("Atualizar Produto");
                outroStage3.setScene(atualizarProduto);
                outroStage3.show();
                cenaEscolhida = atualizarProduto;
                break;
            case "atualizarFuncionario":
                outroStage3.setTitle("Atualizar Funcionario");
                outroStage3.setScene(atualizarFuncionario);
                outroStage3.show();
                cenaEscolhida = atualizarFuncionario;
                break;
        }
        return cenaEscolhida;
    }
    
    public static Alert criarAlerta(String tipoDoErro , String texto){
        
        tipoDoErro = tipoDoErro.toUpperCase();
        texto = texto.toUpperCase();
        
        Alert alerta = null;
        if(tipoDoErro == "NONE"){
           alerta = new Alert(Alert.AlertType.NONE);
           alerta.setContentText(texto);
        }
        if(tipoDoErro == "INFORMATION"){
           alerta = new Alert(Alert.AlertType.INFORMATION);
           alerta.setContentText(texto);
        }
        if(tipoDoErro == "CONFIRMATION"){
            alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setContentText(texto);
            alerta.getButtonTypes().clear();
            alerta.getButtonTypes().add(ButtonType.NO);
            alerta.getButtonTypes().add(ButtonType.YES);
        }
        return alerta;
    }
     
    public static Alert criarAlertaSql(SQLException ex){
        Alert alerta = null;
        alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
        alerta.showAndWait();
        return alerta;
    }
    
    public static Alert criarAlertaIOEx(IOException ex){
        Alert alerta = null;
        alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
        alerta.showAndWait();
        return alerta;
    }
   
    public static String getDateTime() { 
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	Date date = new Date(); 
        String diaMesAno = dateFormat.format(date); 
	return diaMesAno;
    }
    
    public  static Funcionario getTransferirObjetoFuncionario(){
        return objetoFuncionario;
    }
    
    public  static Funcionario setTransferirObjetoFuncionario(Funcionario funcionario){
        return objetoFuncionario = funcionario;
    }
    
    public  static Produto getTransferirObjetoProduto(){
        return objetoProduto;
    }
    
    public  static Produto setTransferirObjetoProduto(Produto produto){
        return objetoProduto = produto;
    }
    
    public  static Cliente getTransferirObjetoCliente(){
        return objetoCliente;
    }
    
    public  static Cliente setTransferirObjetoCliente(Cliente cliente){
        return objetoCliente = cliente;
    }
    
    public static boolean stringContainsNumber( String s ){
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }
    
    public static boolean numberContainsString( String s ){
        return Pattern.compile( "[a-zA-Z]+" ).matcher( s ).find();
    }
    
    public static String getHora(){
        Date date = new Date();
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        String horaMinuto = hourFormat.format(date);
        return horaMinuto;
    }
}
