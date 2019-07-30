/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.DAO;

import java.sql.SQLException;
import java.util.List;
import lanchonete.model.Produto;

/**
 *
 * @author Bileko
 */
public interface ProdutoDAO {
      public void save(Produto produto)throws SQLException;
    
    public void update(Produto produto )throws SQLException;
    
    public void updateQtd(Integer codigo, Integer quantidade )throws SQLException;
    
    public void delete(Produto produto)throws SQLException;
    
    public List<Produto> getAll()throws SQLException;
    
    public List<Produto> procurarProdutos(String texto)throws SQLException;
    
    public String pegarNomeProduto(Integer codigoProduto) throws SQLException; 
    
    public String pegarPrecoProduto(Integer codigoProduto) throws SQLException;
    
    public Produto pegarProduto(Integer codigoProduto) throws SQLException;
}
