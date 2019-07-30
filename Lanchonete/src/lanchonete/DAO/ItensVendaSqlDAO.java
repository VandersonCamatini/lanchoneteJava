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
import lanchonete.model.Cliente;

import lanchonete.model.ItensVenda;

/**
 *
 * @author Bileko
 */
public class ItensVendaSqlDAO extends ConnectionFactory implements ItensVendaDAO{
    
    public void save(ItensVenda itensVenda) throws SQLException {
        //String[] codigoGerado = {"codven"};
        super.preparedStatementInitialize("insert into itensVenda(codven, codpro, qntdcomprada, valorTotal) values (?,?,?,?)"/*,codigoGerado*/);
        super.prepared.setInt(1, itensVenda.getCodigoVenda());
        super.prepared.setInt(2, itensVenda.getCodigoProduto());
        super.prepared.setInt(3, itensVenda.getQntdComprada());
        super.prepared.setDouble(4, itensVenda.getValorTotal());
        
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar os novos itens.");
        }
        /*
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            itensVenda.setCodigoVenda(resultSetRows.getInt("codven"));
        }
        resultSetRows.close();*/
        super.closePreparedStatement();
    }

    @Override
    public void update(ItensVenda itensVenda) throws SQLException {
        super.preparedStatementInitialize(
                "update itensVenda set codpro = 1, qntdComprada = 2, valorTotal = 3 WHERE codven = ?");
        super.prepared.setInt(1, itensVenda.getCodigoProduto());
        super.prepared.setInt(2, itensVenda.getQntdComprada());
        super.prepared.setDouble(3, itensVenda.getValorTotal()); 
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível alterar os itens.");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public void delete(ItensVenda itensVenda) throws SQLException {
        super.preparedStatementInitialize(
                "delete from itensVenda where codven = ?");
        super.prepared.setInt(1, itensVenda.getCodigoVenda());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar os itens.");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<ItensVenda> getAll() throws SQLException {
        
        List<ItensVenda> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from itensVenda ");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new ItensVenda(resultSetRows.getInt("codven"),
                    resultSetRows.getInt("codpro"),
                    resultSetRows.getInt("qntdComprada"),
                    resultSetRows.getDouble("valorTotal")
            ));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }

    public List<ItensVenda> getItensVendaByCodigoVenda(Integer codigoVenda) throws SQLException {
        
        List<ItensVenda> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from itensVenda where codven = ?");
        super.prepared.setInt(1, codigoVenda);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new ItensVenda(resultSetRows.getInt("codven"),
                    resultSetRows.getInt("codpro"),
                    resultSetRows.getInt("qntdComprada"),
                    resultSetRows.getDouble("valorTotal")
            ));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
}
