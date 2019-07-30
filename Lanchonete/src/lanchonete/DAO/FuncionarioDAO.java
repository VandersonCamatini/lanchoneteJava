/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.DAO;

import java.sql.SQLException;
import java.util.List;
import lanchonete.model.Funcionario;

/**
 *
 * @author Senai
 */
public interface FuncionarioDAO {
    
    public void save(Funcionario funcionario)throws SQLException;
    
    public void update(Funcionario funcionario)throws SQLException;
    
    public void delete(Funcionario funcionario)throws SQLException;
    
    public List<Funcionario> getAll()throws SQLException;
    
    public List<Funcionario> procurarFuncionarios(String texto)throws SQLException;
    
    public Boolean procurarLoginGerente(String login) throws SQLException;
    
    public Boolean procurarLoginVendedor(String login) throws SQLException;
    
    public String procurarSenhaGerente (String login) throws SQLException;
    
    public String procurarSenhaVendedor (String login) throws SQLException; 
    
    public List<Funcionario>  pegarObjeto(String login, String senha) throws SQLException;
    
    public Funcionario  pegarFuncionario(Integer codigoFunc) throws SQLException;
}
