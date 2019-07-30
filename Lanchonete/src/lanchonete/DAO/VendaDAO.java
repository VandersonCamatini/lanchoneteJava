/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.DAO;

import java.sql.SQLException;
import java.util.List;
import lanchonete.model.Venda;

/**
 *
 * @author Bileko
 */
public interface VendaDAO {
    
    public void save(Venda venda)throws SQLException;
    
    public void update(Venda venda)throws SQLException;
    
    public void delete(Venda venda)throws SQLException;
    
    public List<Venda> getAll()throws SQLException;
    
    public List<Venda> procurarVendas(String texto)throws SQLException;
    
    public List<Venda> procurarReservas(String texto)throws SQLException;
    
    public List<Venda> pegarReservas()throws SQLException;
    
    public String pegarNumeroCliente(Integer codigoCliente) throws SQLException; 
    
    public String pegarNomeCliente(Integer codigoCliente) throws SQLException;
}
