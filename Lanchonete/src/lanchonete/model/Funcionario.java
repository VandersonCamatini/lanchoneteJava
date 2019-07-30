/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Senai
 */
public class Funcionario {
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private final StringProperty tipo = new SimpleStringProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty email= new SimpleStringProperty();
    private final StringProperty endereco = new SimpleStringProperty();
    private final StringProperty cidade = new SimpleStringProperty();
    private final StringProperty estado = new SimpleStringProperty();
    private final StringProperty pais = new SimpleStringProperty();
    private final StringProperty identificador = new SimpleStringProperty();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty login = new SimpleStringProperty();
    private final StringProperty senha = new SimpleStringProperty();
    
    
    public Funcionario(Integer codigo, String nome, String telefone, String login, String senha, String email, String tipo, String endereco, String cidade, String estado, String pais,String identificador){
        this.codigo.set(codigo);
        this.nome.set(nome);
        this.email.set(email);
        this.identificador.set(identificador);
        this.telefone.set(telefone);
        this.login.set(login);
        this.senha.set(senha);
        this.endereco.set(endereco);
        this.cidade.set(cidade);
        this.estado.set(estado);
        this.pais.set(pais);
        this.tipo.set(tipo);
    }
    
    public Funcionario (){
        
    }
    
    public void setCodigo(Integer codigo){
        this.codigo.set(codigo);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getCodigo(){
        return this.codigo.get();
    }
    public IntegerProperty codigoProperty(){
        return this.codigo;
    }
    
    public void setNome(String nome){
        this.nome.set(nome);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getNome(){
        return this.nome.get();
    }
    public StringProperty nomeProperty(){
        return this.nome;
    }
    
    public void setTipo(String tipo){
        this.tipo.set(tipo);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getTipo(){
        return this.tipo.get();
    }
    public StringProperty tipoProperty(){
        return this.tipo;
    }
    
    public void setEndereco(String endereco){
        this.endereco.set(endereco);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getEndereco(){
        return this.endereco.get();
    }
    public StringProperty enderecoProperty(){
        return this.endereco;
    }
    
    public void setCidade(String cidade){
        this.cidade.set(cidade);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getCidade(){
        return this.cidade.get();
    }
    public StringProperty cidadeProperty(){
        return this.cidade;
    }
    
    public void setEstado(String estado){
        this.estado.set(estado);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getEstado(){
        return this.estado.get();
    }
    public StringProperty estadoProperty(){
        return this.estado;
    }
    
    public void setPais(String pais){
        this.pais.set(pais);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getPais(){
        return this.pais.get();
    }
    public StringProperty paisProperty(){
        return this.pais;
    }
    
    public void setEmail(String email){
        this.email.set(email);
    }
    public String getEmail(){
        return this.email.get();
    }
    public StringProperty emailProperty(){
        return this.email;
    }
    
    public void setIdentificador(String identificador){
        this.identificador.set(identificador);
    }
    public String getIdentificador(){
        return this.identificador.get();
    }
    public StringProperty identificadorProperty(){
        return this.identificador;
    }
    
    public void setTelefone(String telefone){
        this.telefone.set(telefone);
    }
    public String getTelefone(){
        return this.telefone.get();
    }
    public  StringProperty telefoneProperty(){
        return this.telefone;
    }
    
    public void setLogin(String login){
        this.login.set(login);
    }
    public String getLogin(){
        return this.login.get();
    }
    public StringProperty loginProperty(){
        return this.login;
    }
    
    public void setSenha(String senha){
        this.senha.set(senha);
    }
    public String getSenha(){
        return this.senha.get();
    }
    public StringProperty senhaProperty(){
        return this.senha;
    }
    
}
