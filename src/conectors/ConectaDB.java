/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ferpa
 */
public class ConectaDB {

    private static Connection conn;

    private static final String sUsuario = "SYSTEM";
    private static final String sSenha = "123";

    public static Connection ConectaDB() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";

        try {
            conn = DriverManager.getConnection(url, sUsuario, sSenha);
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Conexão nula");
                System.exit(0);
            }
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        return conn;
    }

    public static void main(String[] args) {
        ConectaDB();
    }

}
