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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lanchonete.Lanchonete;

import lanchonete.model.Venda;

/**
 *
 * @author Bileko
 */
public class VendaSqlDAO extends ConnectionFactory implements VendaDAO {

    @Override
    public void save(Venda venda) throws SQLException {
        String[] codigoGerado = {"codven"};
        super.preparedStatementInitialize("insert into venda(codcli, codfun, valorcompra,  tipven, horven, datven) values (?,?,?,?,?,?)", codigoGerado);
        super.prepared.setInt(1, venda.getCodigoCliente());
        super.prepared.setInt(2, venda.getCodigoFuncionario());
        super.prepared.setDouble(3, venda.getValorCompra());
        super.prepared.setString(4, venda.getTipo());
        super.prepared.setString(5, venda.getHora());
        super.prepared.setString(6, venda.getDataEmissao());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0) {
            throw new SQLException("Não foi possível criar a nova venda");
        }
        ResultSet resultSetRows = super.prepared.getGeneratedKeys();
        if (resultSetRows.next()) {
            venda.setCodigo(resultSetRows.getInt("codven"));
        }
        super.closePreparedStatement();

        venda.getItens().forEach(item -> {
            item.setCodigoVenda(venda.getCodigo());
            try {
                DAOFactory.getProdutoDAO().updateQtd(item.getCodigoProduto(), item.getQntdComprada());
                DAOFactory.getItensVendaDAO().save(item);
            } catch (SQLException ex) {
                Logger.getLogger(VendaSqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alerta.showAndWait();
            }
        });

    }

    @Override
    public void update(Venda venda) throws SQLException {
        super.preparedStatementInitialize(
                "update venda set valorcompra = 1, tipven = 2, horven = 3,  datven = 4 WHERE codven = ?");
        super.prepared.setDouble(1, venda.getValorCompra());
        super.prepared.setString(2, venda.getTipo());
        super.prepared.setString(3, venda.getHora());
        super.prepared.setString(4, venda.getDataEmissao());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0) {
            throw new SQLException("Não foi possível alterar a venda.");
        }

        super.closePreparedStatement();
    }

    @Override
    public void delete(Venda venda) throws SQLException {
        super.preparedStatementInitialize(
                "delete from venda where codven = ?");
        super.prepared.setInt(1, venda.getCodigo());
        int linhasAfetadas = super.prepared.executeUpdate();
        if (linhasAfetadas == 0) {
            throw new SQLException("Não foi possível deletar a venda.");
        }

        super.closePreparedStatement();
    }

    @Override
    public List<Venda> getAll() throws SQLException {

        List<Venda> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from venda");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Venda(resultSetRows.getInt("codven"),
                    resultSetRows.getInt("codfun"),
                    resultSetRows.getInt("codcli"),
                    resultSetRows.getDouble("valorcompra"),
                    resultSetRows.getString("tipven"),
                    resultSetRows.getString("horven"),
                    resultSetRows.getString("datven"),
                    DAOFactory.getItensVendaDAO().getItensVendaByCodigoVenda(resultSetRows.getInt("codven"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }

    @Override
    public List<Venda> procurarVendas(String texto) throws SQLException {

        List<Venda> rows = new ArrayList<>();
        super.preparedStatementInitialize("select a.* from venda a ,funcionario b,cliente c where a.codfun = b.codfun AND a.codcli = c.codcli AND (b.nomfun = ? OR c.nomcli = ? OR a.datven = ? OR a.horven = ? OR a.tipven = ?)");
        super.prepared.setString(1, texto);
        super.prepared.setString(2, texto);
        super.prepared.setString(3, texto);
        super.prepared.setString(4, texto);
        super.prepared.setString(5, texto);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Venda(resultSetRows.getInt("codven"),
                    resultSetRows.getInt("codfun"),
                    resultSetRows.getInt("codcli"),
                    resultSetRows.getDouble("valorcompra"),
                    resultSetRows.getString("tipven"),
                    resultSetRows.getString("horven"),
                    resultSetRows.getString("datven"),
                    DAOFactory.getItensVendaDAO().getItensVendaByCodigoVenda(resultSetRows.getInt("codven"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }

    @Override
    public List<Venda> procurarReservas(String texto) throws SQLException {

        List<Venda> rows = new ArrayList<>();
        super.preparedStatementInitialize("select a.* from venda a ,funcionario b,cliente c where a.codfun = b.codfun AND a.codcli = c.codcli AND a.tipven = 'Reserva' AND (b.nomfun = ? OR c.nomcli = ? OR a.datven = ? OR a.horven = ? )");
        super.prepared.setString(1, texto);
        super.prepared.setString(2, texto);
        super.prepared.setString(3, texto);
        super.prepared.setString(4, texto);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Venda(resultSetRows.getInt("codven"),
                    resultSetRows.getInt("codfun"),
                    resultSetRows.getInt("codcli"),
                    resultSetRows.getDouble("valorcompra"),
                    resultSetRows.getString("tipven"),
                    resultSetRows.getString("horven"),
                    resultSetRows.getString("datven"),
                    DAOFactory.getItensVendaDAO().getItensVendaByCodigoVenda(resultSetRows.getInt("codven"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    @Override
    public List<Venda> pegarReservas() throws SQLException {

        List<Venda> rows = new ArrayList<>();
        super.preparedStatementInitialize("select * from venda where tipven = 'Reserva'");
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            rows.add(new Venda(resultSetRows.getInt("codven"),
                    resultSetRows.getInt("codfun"),
                    resultSetRows.getInt("codcli"),
                    resultSetRows.getDouble("valorcompra"),
                    resultSetRows.getString("tipven"),
                    resultSetRows.getString("horven"),
                    resultSetRows.getString("datven"),
                    DAOFactory.getItensVendaDAO().getItensVendaByCodigoVenda(resultSetRows.getInt("codven"))));
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return rows;
    }
    
    @Override
    public String pegarNomeCliente(Integer codigoCliente) throws SQLException {

        String resultado = null;
        super.preparedStatementInitialize("SELECT nomcli FROM cliente WHERE codcli = ? ");
        super.prepared.setInt(1, codigoCliente);
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
    public String pegarNumeroCliente(Integer codigoCliente) throws SQLException {

        String resultado = null;
        super.preparedStatementInitialize("SELECT telcli FROM cliente WHERE codcli = ? ");
        super.prepared.setInt(1, codigoCliente);
        super.prepared.execute();
        ResultSet resultSetRows = super.prepared.getResultSet();
        while (resultSetRows.next()) {
            resultado = resultSetRows.getString(1);
        }
        resultSetRows.close();
        super.closePreparedStatement();
        return resultado;
    }
}
