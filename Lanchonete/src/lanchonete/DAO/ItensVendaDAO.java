/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonete.DAO;

import java.sql.SQLException;
import java.util.List;
import lanchonete.model.ItensVenda;

/**
 *
 * @author Bileko
 */
public interface ItensVendaDAO {
    
    public void save(ItensVenda item)throws SQLException;
    
    public void update(ItensVenda item)throws SQLException;
    
    public void delete(ItensVenda item)throws SQLException;
    
    public List<ItensVenda> getAll()throws SQLException;
    
    public List<ItensVenda> getItensVendaByCodigoVenda(Integer codigoVenda)throws SQLException;
    
}
