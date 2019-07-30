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
public class Cliente {
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty tipo = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty identificador = new SimpleStringProperty();
    private final StringProperty matricula = new SimpleStringProperty();
    
    
    public Cliente(Integer codigo, String nome, String telefone, String email, String tipo,String identificador){
        this.codigo.set(codigo);
        this.nome.set(nome);
        this.email.set(email);
        this.identificador.set(identificador);
        this.telefone.set(telefone);
        this.tipo.set(tipo);
    }
    
    public Cliente(Integer codigo, String nome, String telefone, String email, String tipo,String identificador, String matricula){
        this.codigo.set(codigo);
        this.nome.set(nome);
        this.email.set(email);
        this.identificador.set(identificador);
        this.telefone.set(telefone);
        this.tipo.set(tipo);
        this.matricula.set(matricula);
    }
    
    public Cliente (){
        
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
    
    public void setMatricula(String matricula){
        this.matricula.set(matricula);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getMatricula(){
        return this.matricula.get();
    }
    public StringProperty matriculaProperty(){
        return this.matricula;
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
    
    public void setTelefone(String telefone){
        this.telefone.set(telefone);
    }
    public String getTelefone(){
        return this.telefone.get();
    }
    public StringProperty telefoneProperty(){
        return this.telefone;
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
 
}
