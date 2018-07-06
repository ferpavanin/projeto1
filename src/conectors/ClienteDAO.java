/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tabelas.Cliente;

/**
 *
 * @author ferpa
 */
public class ClienteDAO {

    private Connection conn;

    public ClienteDAO() {
        this.conn = ConectaDB.ConectaDB();
    }

    private void Insere(Cliente cliente) throws SQLException {

        String s = "insert into cliente(idcliente,razao,cnpjcpf) values(?,?,?)";

        PreparedStatement ps = this.conn.prepareStatement(s);
        ps.setInt(1, cliente.getIdCliente());
        ps.setString(2, cliente.getRazao());
        ps.setLong(3, cliente.getCnpjcpf());

        ps.execute();
        ps.close();

    }

    public static void main(String[] args) throws SQLException {
        ClienteDAO c = new ClienteDAO();
        Cliente tc = new Cliente();
        tc.setIdCliente(2);
        tc.setCnpjcpf(36393656824l);
        tc.setRazao("FERNANDO PAVANIN2");
        c.Insere(tc);
    }

}
