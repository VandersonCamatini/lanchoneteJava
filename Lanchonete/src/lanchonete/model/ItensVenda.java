/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.model;

import java.sql.SQLException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import lanchonete.DAO.DAOFactory;


public class ItensVenda {
    private final IntegerProperty codigoVenda = new SimpleIntegerProperty();
    private final DoubleProperty valorTotal = new SimpleDoubleProperty();
    private final ObjectProperty<Integer> codigoProduto = new SimpleObjectProperty();
    private final IntegerProperty qntdComprada = new SimpleIntegerProperty();
    
    public ItensVenda(Integer codigoVenda, Integer codigoProduto, Integer qntdComprada, Double valorTotal)
    {
        this.codigoVenda.set(codigoVenda);
        this.codigoProduto.set(codigoProduto);
        this.qntdComprada.set(qntdComprada);
        this.valorTotal.set(valorTotal);
    }
    
    public ItensVenda()
    {
        
    }
    
    public void setCodigoVenda(Integer codigoVenda){
        this.codigoVenda.set(codigoVenda);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getCodigoVenda(){
        return this.codigoVenda.get();
    }
    public IntegerProperty codigoVendaProperty(){
        return this.codigoVenda;
    }
    
    public void setCodigoProduto(Integer codigoCliente){
        this.codigoProduto.set(codigoCliente);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getCodigoProduto(){
        return this.codigoProduto.get();
    }
    public ObjectProperty<Integer> codigoProdutoProperty(){
        return this.codigoProduto;
    }
    
    public void setQntdComprada(Integer qntdComprada){
        this.qntdComprada.set(qntdComprada);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getQntdComprada(){
        return this.qntdComprada.get();
    }
    public IntegerProperty qntdCompradaProperty(){
        return this.qntdComprada;
    }
    
    public void calculaValorItem() throws SQLException{
        this.valorTotal.set(DAOFactory.getProdutoDAO().pegarProduto(codigoProduto.get()).getPrecoVenda() * qntdComprada.get());
    }
    
    public void setValorTotal(Double valorTotal){
        this.valorTotal.set(valorTotal);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Double getValorTotal(){
        return this.valorTotal.get();
    }
    public DoubleProperty valorTotalProperty(){
        return this.valorTotal;
    }
}
