/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lanchonete.model.Produto;

/**
 *
 * @author Bileko
 */
public class ProdutoSqlDAO extends ConnectionFactory implements ProdutoDAO{
    
    public void save(Produto produto) throws SQLException {
        String[] codigoGerado = {"codpro"};
        super.preparedStatementInitialize("insert into produto(nompro, marpro, qntd,  precom, preven) values (?,?,?,?,?)",codigoGerado);
        super.prepared.setString(1, produto.getNome());
        super.prepared.setString(2, produto.getMarca());
        super.prepared.setInt(3, produto.getQntd());
        super.prepared.setDouble(4, produto.getPrecoCompra());
        super.prepared.setDouble(5, produto.getPrecoVenda());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível criar o nova produto");
        }
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            produto.setCodigo(resultSetRows.getInt("codpro"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Produto produto) throws SQLException {
        super.preparedStatementInitialize(
                "update produto set nompro = ?, marpro = ?, qntd = ?,  precom = ?, preven = ? WHERE codpro = ?");
        super.prepared.setString(1, produto.getNome());
        super.prepared.setString(2, produto.getMarca());
        super.prepared.setInt(3, produto.getQntd());
        super.prepared.setDouble(4, produto.getPrecoCompra());
        super.prepared.setDouble(5, produto.getPrecoVenda());
        super.prepared.setInt(6, produto.getCodigo());
        
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível alterar o produto.");
        }
        
        super.closePreparedStatement();
    }
    
    @Override
    public void updateQtd(Integer codigo, Integer quantidade) throws SQLException {
        super.preparedStatementInitialize(
                "update produto set qntd = ? where codpro = ?");
        super.prepared.setInt(1, (DAOFactory.getProdutoDAO().pegarProduto(codigo).getQntd() - quantidade));
        super.prepared.setInt(2, codigo);
        
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível alterar o produto.");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public void delete(Produto produto) throws SQLException {
        super.preparedStatementInitialize(
                "delete from produto where codpro = ?");
        super.prepared.setInt(1, produto.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar o produto");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Produto> getAll() throws SQLException {
        
        List<Produto> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from produto");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Produto(resultSetRows.getInt("codpro"),
                    resultSetRows.getInt("qntd"),
                    resultSetRows.getString("nompro"),
                    resultSetRows.getString("marpro"),
                    resultSetRows.getDouble("precom"),
                    resultSetRows.getDouble("preven")));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    @Override
    public List<Produto> procurarProdutos(String texto) throws SQLException {
        List<Produto> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from produto where nompro = ? OR marpro = ?");
        super.prepared.setString(1, texto);
        super.prepared.setString(2, texto);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Produto(resultSetRows.getInt("codpro"),
                    resultSetRows.getInt("qntd"),
                    resultSetRows.getString("nompro"),
                    resultSetRows.getString("marpro"),
                    resultSetRows.getDouble("precom"),
                    resultSetRows.getDouble("preven")));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    
    public String pegarNomeProduto(Integer codigoCliente) throws SQLException {
        
        String resultado = null;
        super.preparedStatementInitialize("SELECT nompro FROM produto WHERE codpro = ? ");
        super.prepared.setInt(1,codigoCliente);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getString(1);
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return resultado;
    }
    
    public String pegarPrecoProduto(Integer codigoProduto) throws SQLException {
        
        String resultado = null;
        super.preparedStatementInitialize("SELECT preven FROM produto WHERE codpro = ? ");
        super.prepared.setInt(1,codigoProduto);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getString(1);
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return resultado;
    }

    @Override
    public Produto pegarProduto(Integer codigoProduto) throws SQLException {
        Produto prod = new Produto();
        super.preparedStatementInitialize("select * from produto where codpro = ?");
        super.prepared.setInt(1, codigoProduto);
        
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            prod = (new Produto(resultSetRows.getInt("codpro"),
                    resultSetRows.getInt("qntd"),
                    resultSetRows.getString("nompro"),
                    resultSetRows.getString("marpro"),
                    resultSetRows.getDouble("precom"),
                    resultSetRows.getDouble("preven")));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return prod;
    }

    
}
