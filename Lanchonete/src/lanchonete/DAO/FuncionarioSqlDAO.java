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
import lanchonete.model.Funcionario;
import lanchonete.Lanchonete;

/**
 *
 * @author Senai
 */
public class FuncionarioSqlDAO extends ConnectionFactory implements FuncionarioDAO{
    
    public void save(Funcionario funcionario) throws SQLException {
        String[] codigoGerado = {"codfun"};
        super.preparedStatementInitialize("insert into funcionario(tipfun, nomfun, idefun,  telfun, emafun, endfun, cidfun, estfun, paifun, logfun, senfun) values (?,?,?,?,?,?,?,?,?,?,?)",codigoGerado);
        super.prepared.setString(1, funcionario.getTipo());
        super.prepared.setString(2, funcionario.getNome());
        super.prepared.setString(3, funcionario.getIdentificador());
        super.prepared.setString(4, funcionario.getTelefone());
        super.prepared.setString(5, funcionario.getEmail());
        super.prepared.setString(6, funcionario.getEndereco());
        super.prepared.setString(7, funcionario.getCidade());
        super.prepared.setString(8, funcionario.getEstado());
        super.prepared.setString(9, funcionario.getPais());
        super.prepared.setString(10, funcionario.getLogin());
        super.prepared.setString(11, funcionario.getSenha());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível cadastrar a nova conta");
        }
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            funcionario.setCodigo(resultSetRows.getInt("codfun"));
        }
        resultSetRows.close();
        super.closePreparedStatement();
    }

    @Override
    public void update(Funcionario funcionario) throws SQLException {
        super.preparedStatementInitialize(
                "update funcionario set tipfun = ?, nomfun = ?, idefun = ?,  telfun = ?, emafun = ?, endfun = ?, cidfun = ?, estfun = ?, paifun = ?, logfun = ?, senfun = ? WHERE codfun = ?");
        super.prepared.setString(1, funcionario.getTipo());
        super.prepared.setString(2, funcionario.getNome());
        super.prepared.setString(3, funcionario.getIdentificador());
        super.prepared.setString(4, funcionario.getTelefone());
        super.prepared.setString(5, funcionario.getEmail());
        super.prepared.setString(6, funcionario.getEndereco());
        super.prepared.setString(7, funcionario.getCidade());
        super.prepared.setString(8, funcionario.getEstado());
        super.prepared.setString(9, funcionario.getPais());
        super.prepared.setString(10, funcionario.getLogin());
        super.prepared.setString(11, funcionario.getSenha());
        super.prepared.setInt(12, funcionario.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível alterar a conta");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public void delete(Funcionario funcionario) throws SQLException {
        super.preparedStatementInitialize(
                "delete from funcionario where codfun = ?");
        super.prepared.setInt(1, funcionario.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0){
            throw new SQLException("Não foi possível deletar a conta");
        }
        
        super.closePreparedStatement();
    }

    @Override
    public List<Funcionario> getAll() throws SQLException {
        
        List<Funcionario> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from funcionario");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Funcionario(resultSetRows.getInt("codfun"),
                    resultSetRows.getString("nomfun"),
                    resultSetRows.getString("telfun"),
                    resultSetRows.getString("logfun"),
                    resultSetRows.getString("senfun"),
                    resultSetRows.getString("emafun"),
                    resultSetRows.getString("tipfun"),
                    resultSetRows.getString("endfun"),
                    resultSetRows.getString("cidfun"),
                    resultSetRows.getString("estfun"),
                    resultSetRows.getString("paifun"),
                    resultSetRows.getString("idefun")));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    public List<Funcionario> procurarFuncionarios(String texto) throws SQLException {
        
        List<Funcionario> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from funcionario where nomfun = ? OR tipfun = ? OR idefun = ?");
        super.prepared.setString(1, texto);
        super.prepared.setString(2, texto);
        super.prepared.setString(3, texto);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Funcionario(resultSetRows.getInt("codfun"),
                    resultSetRows.getString("nomfun"),
                    resultSetRows.getString("telfun"),
                    resultSetRows.getString("logfun"),
                    resultSetRows.getString("senfun"),
                    resultSetRows.getString("emafun"),
                    resultSetRows.getString("tipfun"),
                    resultSetRows.getString("endfun"),
                    resultSetRows.getString("cidfun"),
                    resultSetRows.getString("estfun"),
                    resultSetRows.getString("paifun"),
                    resultSetRows.getString("idefun")));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    public Boolean procurarLoginGerente(String login) throws SQLException {
    
        Boolean resultado = false;
        super.preparedStatementInitialize("SELECT * FROM funcionario WHERE tipfun = 'Gerente' AND logfun = ? ");
        super.prepared.setString(1, login);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = true;
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return resultado;
    }
    
    public String procurarSenhaGerente (String login) throws SQLException {
        
        String resultado = null;
        super.preparedStatementInitialize("SELECT senfun FROM funcionario WHERE tipfun = 'Gerente' AND logfun = ? ");
        super.prepared.setString(1,login);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getString(1);
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return resultado;
    }
    
    public Boolean procurarLoginVendedor(String login) throws SQLException {
    
        Boolean resultado = false;
        super.preparedStatementInitialize("SELECT * FROM funcionario WHERE tipfun = 'Vendedor' AND logfun = ? ");
        super.prepared.setString(1, login);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = true;
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return resultado;
    }
    
    public String procurarSenhaVendedor(String login) throws SQLException {
        
        String resultado = null;
        super.preparedStatementInitialize("SELECT senfun FROM funcionario WHERE tipfun = 'Vendedor' AND logfun = ? ");
        super.prepared.setString(1,login);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getString(1);
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return resultado;
    }
    
    public List<Funcionario>  pegarObjeto (String login, String senha) throws SQLException {
        Funcionario resultado = null;
        List<Funcionario> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from funcionario where logfun = ? and senfun = ? ");
        super.prepared.setString(1,login);
        super.prepared.setString(2,senha);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Funcionario(resultSetRows.getInt("codfun"),
                    resultSetRows.getString("nomfun"),
                    resultSetRows.getString("telfun"),
                    resultSetRows.getString("logfun"),
                    resultSetRows.getString("senfun"),
                    resultSetRows.getString("emafun"),
                    resultSetRows.getString("tipfun"),
                    resultSetRows.getString("endfun"),
                    resultSetRows.getString("cidfun"),
                    resultSetRows.getString("estfun"),
                    resultSetRows.getString("paifun"),
                    resultSetRows.getString("idefun")));
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return rows;
    }

    @Override
    public Funcionario pegarFuncionario(Integer codigoFunc) throws SQLException {
        Funcionario resultado = new Funcionario();
        super.preparedStatementInitialize("select * from funcionario where codfun = ? ");
        super.prepared.setInt(1,codigoFunc);
        super.prepared.execute(); 
        ResultSet resultSetRows = super.prepared.getResultSet();
        if (resultSetRows.next()) {
            resultado = (new Funcionario(resultSetRows.getInt("codfun"),
                    resultSetRows.getString("nomfun"),
                    resultSetRows.getString("telfun"),
                    resultSetRows.getString("logfun"),
                    resultSetRows.getString("senfun"),
                    resultSetRows.getString("emafun"),
                    resultSetRows.getString("tipfun"),
                    resultSetRows.getString("endfun"),
                    resultSetRows.getString("cidfun"),
                    resultSetRows.getString("estfun"),
                    resultSetRows.getString("paifun"),
                    resultSetRows.getString("idefun")));
        }
        resultSetRows.close();
        super.closePreparedStatement();     
        return resultado;
    }
}
