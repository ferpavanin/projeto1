package secundario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testehj {

    public testehj() {
        pegaConn();
    }

    private Connection pegaConn() {
        Connection c = null;

        try {
            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "SYSTEM",
                    "123");
            c.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(testehj.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }

    public static void main(String[] args) throws SQLException {
        testehj t = new testehj();
        String sql = "select * from cliente";
        PreparedStatement ps = t.pegaConn().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("Razao"));
        }

        t.pegaConn().commit();
        t.pegaConn().close();
                
    }

}
