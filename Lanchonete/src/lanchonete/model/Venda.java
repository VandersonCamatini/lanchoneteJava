/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Senai
 */
public class Venda {
    
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private final ObjectProperty<Integer> codigoFuncionario = new SimpleObjectProperty();
    private final ObjectProperty<Integer> codigoCliente = new SimpleObjectProperty();
    private final DoubleProperty valorCompra = new SimpleDoubleProperty();
    Double total = 0.00;
    private final StringProperty tipo = new SimpleStringProperty();
    private final StringProperty hora = new SimpleStringProperty();
    private final StringProperty dataEmissao = new SimpleStringProperty();
    private List<ItensVenda> itensDaVenda = new ArrayList<>();
    

    public Venda(Integer codigo, Integer codigoFunc, Integer codigoCli, Double valor, String tipo, String hora, String dataEmissao, List<ItensVenda> itensVenda)
    {
        this.codigo.set(codigo);
        this.codigoFuncionario.set(codigoFunc);
        this.codigoCliente.set(codigoCli);
        this.valorCompra.set(valor);
        this.tipo.set(tipo);
        this.hora.set(hora);
        this.dataEmissao.set(dataEmissao);
        this.itensDaVenda = itensVenda;
        
    }
    
    public Venda(){
   
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
    
    public void setCodigoCliente(Integer codigoCliente){
        this.codigoCliente.set(codigoCliente);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getCodigoCliente(){
        return this.codigoCliente.get();
    }
    public ObjectProperty<Integer> codigoClienteProperty(){
        return this.codigoCliente;
    }
    
    public void setCodigoFuncionario(Integer codigoFuncionario){
        this.codigoFuncionario.set(codigoFuncionario);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Integer getCodigoFuncionario(){
        return this.codigoFuncionario.get();
    }
    public ObjectProperty<Integer> codigoFuncionarioProperty(){
        return this.codigoFuncionario;
    }
    
    public void calculaValorTotalCompra(){
        this.itensDaVenda.forEach(item ->{
            total = total + item.getValorTotal();
        });
        valorCompra.set(total);
        total = 0.0;
    }
    
    public void setValorCompra(Double valorCompra){
        this.valorCompra.set(valorCompra);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public Double getValorCompra(){
        return this.valorCompra.get();
    }
    public DoubleProperty valorCompraProperty(){
        return this.valorCompra;
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
    
    public void setHora(String hora){
        this.hora.set(hora);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getHora(){
        return this.hora.get();
    }
    public StringProperty horaProperty(){
        return this.hora;
    }
    
    public void setDataEmissao(String dataEmissao){
        this.dataEmissao.set(dataEmissao);
    }                                                                                                                                                                                                                                                                                                                                                                   
    public String getDataEmissao(){
        return this.dataEmissao.get();
    }
    public StringProperty dataEmissaoProperty(){
        return this.dataEmissao;
    }
    
    public List<ItensVenda> getItens(){
        return this.itensDaVenda;
    }
    
    public void setItens (List<ItensVenda> itens){
        this.itensDaVenda = itens;
    }
}
