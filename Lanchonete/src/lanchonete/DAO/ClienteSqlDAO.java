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



/**
 *
 * @author Senai
 */
public class ClienteSqlDAO extends ConnectionFactory implements ClienteDAO{
    
    @Override
    public void saveAluno (Cliente cliente) throws SQLException {
        String[] codigoGerado = {"codcli"};
        super.preparedStatementInitialize("insert into cliente(tipcli, nomcli, idecli,  telcli, emacli,matcli) values (?,?,?,?,?,?)",codigoGerado);
        super.prepared.setString(1, cliente.getTipo());
        super.prepared.setString(2, cliente.getNome());
        super.prepared.setString(3, cliente.getIdentificador());
        super.prepared.setString(4, cliente.getTelefone());
        super.prepared.setString(5, cliente.getEmail());
        super.prepared.setString(6, "Aluno não tem");
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar a nova conta");
        }
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            cliente.setCodigo(resultSetRows.getInt("codcli"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }
    
    @Override
    public void saveColaborador (Cliente cliente) throws SQLException {
        String[] codigoGerado = {"codcli"};
        super.preparedStatementInitialize("insert into cliente(tipcli, nomcli, idecli,  telcli, emacli, matcli) values (?,?,?,?,?,?)",codigoGerado);
        super.prepared.setString(1, cliente.getTipo());
        super.prepared.setString(2, cliente.getNome());
        super.prepared.setString(3, cliente.getIdentificador());
        super.prepared.setString(4, cliente.getTelefone());
        super.prepared.setString(5, cliente.getEmail());
        super.prepared.setString(6, cliente.getMatricula());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar a nova conta");
        }
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            cliente.setCodigo(resultSetRows.getInt("codcli"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Cliente cliente) throws SQLException {
        super.preparedStatementInitialize(
                "update cliente set tipcli = ?, nomcli = ?, idecli = ?,  telcli = ?, emacli = ? WHERE codcli = ?");
        super.prepared.setString(1, cliente.getTipo());
        super.prepared.setString(2, cliente.getNome());
        super.prepared.setString(3, cliente.getIdentificador());
        super.prepared.setString(4, cliente.getTelefone());
        super.prepared.setString(5, cliente.getEmail());
        super.prepared.setInt(6, cliente.getCodigo());
        
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível alterar a conta");
        }
        super.closePreparedStatement();
    }

    @Override
    public void delete(Cliente cliente) throws SQLException {
        super.preparedStatementInitialize(
                "delete from cliente where codcli = ?");
        super.prepared.setInt(1, cliente.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar a conta");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Cliente> getAll() throws SQLException {
        
        List<Cliente> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from cliente ");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Cliente(resultSetRows.getInt("codcli"),
                    resultSetRows.getString("nomcli"),
                    resultSetRows.getString("telcli"),
                    resultSetRows.getString("emacli"),
                    resultSetRows.getString("tipcli"),
                    resultSetRows.getString("idecli"),
                    resultSetRows.getString("matcli")
            ));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    @Override
    public List<Cliente> procurarClientes(String texto) throws SQLException {
        
        List<Cliente> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from cliente where nomcli = ? OR tipcli = ? OR idecli = ? OR matcli = ? ");
        super.prepared.setString(1, texto);
        super.prepared.setString(2, texto);
        super.prepared.setString(3, texto);
        super.prepared.setString(4, texto);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Cliente(resultSetRows.getInt("codcli"),
                    resultSetRows.getString("nomcli"),
                    resultSetRows.getString("telcli"),
                    resultSetRows.getString("emacli"),
                    resultSetRows.getString("tipcli"),
                    resultSetRows.getString("idecli"),
                    resultSetRows.getString("matcli")
            ));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }

    @Override
    public Cliente pegarCliente(Integer codigoCli) throws SQLException {
        Cliente cli = new Cliente();
        super.preparedStatementInitialize("select * from cliente where codcli = ? ");
        super.prepared.setInt(1, codigoCli);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            cli = (new Cliente(resultSetRows.getInt("codcli"),
                    resultSetRows.getString("nomcli"),
                    resultSetRows.getString("telcli"),
                    resultSetRows.getString("emacli"),
                    resultSetRows.getString("tipcli"),
                    resultSetRows.getString("idecli"),
                    resultSetRows.getString("matcli")
            ));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return cli;
    }

    @Override
    public Cliente pegarClientePeloIdentificador(String cpf) throws SQLException {
        Cliente cli = new Cliente();
        super.preparedStatementInitialize("select * from cliente where idecli like ?");
        super.prepared.setString(1, "%"+cpf+"%");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            cli = (new Cliente(resultSetRows.getInt("codcli"),
                    resultSetRows.getString("nomcli"),
                    resultSetRows.getString("telcli"),
                    resultSetRows.getString("emacli"),
                    resultSetRows.getString("tipcli"),
                    resultSetRows.getString("idecli"),
                    resultSetRows.getString("matcli")
            ));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return cli;
    }
    
}
