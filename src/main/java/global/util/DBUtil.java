package global.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    static Properties p = new Properties();

    static {
        try {
            p.load(new FileInputStream("db.properties"));
            Class.forName(p.getProperty("jdbc.driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(p.getProperty("jdbc.url"),
                p.getProperty("jdbc.id"),
                p.getProperty("jdbc.pw"));
    }

    public static void close(Connection con, Statement stmt) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public static void close(Connection con, Statement stmt, ResultSet rset) {
        try {
            if (rset != null) {
                rset.close();
                rset = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
