/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.DAO;

/**
 *
 * @author Senai
 */
public class DAOFactory {
    
    public static FuncionarioDAO getFuncionarioDAO(){
        return new FuncionarioSqlDAO();
    }
    
    public static ClienteDAO getClienteDAO(){
        return new ClienteSqlDAO();
    }
    
    public static ProdutoDAO getProdutoDAO(){
        return new ProdutoSqlDAO();
    }
    
    public static VendaDAO getVendaDAO(){
        return new VendaSqlDAO();
    }
    
    public static ItensVendaDAO getItensVendaDAO(){
        return new ItensVendaSqlDAO();
    }
}
