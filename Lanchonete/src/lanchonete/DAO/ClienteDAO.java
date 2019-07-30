/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.DAO;

import java.sql.SQLException;
import java.util.List;
import lanchonete.model.Cliente;

/**
 *
 * @author Senai
 */
public interface ClienteDAO {
    
    public void saveAluno(Cliente cliente)throws SQLException;
    
    public void saveColaborador(Cliente cliente)throws SQLException;
    
    public void update(Cliente cliente)throws SQLException;
    
    public void delete(Cliente cliente)throws SQLException;
    
    public List<Cliente> getAll()throws SQLException;
    
    public List<Cliente> procurarClientes(String texto)throws SQLException;
    
    public Cliente pegarCliente(Integer codigoCli) throws SQLException;
    
    public Cliente pegarClientePeloIdentificador(String cpf) throws SQLException;
    
}
