/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.model;

import java.util.HashSet;
import java.util.Set;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Senai
 */
public class Produto {

    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private final IntegerProperty qntd = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty marca = new SimpleStringProperty();
    private final DoubleProperty precoCompra = new SimpleDoubleProperty();
    private final DoubleProperty precoVenda = new SimpleDoubleProperty();

    
    public Produto(Integer codigo, Integer qntd, String nome, String marca, Double precoCompra, Double precoVenda){
        this.codigo.set(codigo);
        this.qntd.set(qntd);
        this.nome.set(nome);
        this.marca.set(marca);
        this.precoCompra.set(precoCompra);
        this.precoVenda.set(precoVenda);
    }
    
    public Produto (){
        
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo.set(codigo);
    }

    public Integer getCodigo() {
        return this.codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return this.codigo;
    }
    
    public void setQntd(Integer qntd) {
        this.qntd.set(qntd);
    }

    public Integer getQntd() {
        return this.qntd.get();
    }

    public IntegerProperty qntdProperty() {
        return this.qntd;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getNome() {
        return this.nome.get();
    }

    public StringProperty nomeProperty() {
        return this.nome;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getMarca() {
        return this.marca.get();
    }

    public StringProperty marcaProperty() {
        return this.marca;
    }

    public Double getPrecoCompra() {
        return this.precoCompra.get();
    }

    public DoubleProperty precoCompraProperty() {
        return this.precoCompra;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda.set(precoVenda);
    }

    public Double getPrecoVenda() {
        return this.precoVenda.get();
    }

    public DoubleProperty precoVendaProperty() {
        return this.precoVenda;
    }
}
