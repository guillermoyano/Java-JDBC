package tienda.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {
    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    
    private final String user = "root";
    private final String password = "root";
    private final String dataBase = "tienda";
    private final String driver = "com.mysql.jdbc.Driver";
    
    protected void connectDB() throws ClassNotFoundException, SQLException{
        try {
            Class.forName(driver);
            String urlDB = "jdbc:mysql://localhost:3306/" + dataBase + "?useSSL=false";
            conexion = DriverManager.getConnection(urlDB, user, password);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    protected void disconnectDB () throws Exception{
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    protected void insertModifyDelete(String SQL) throws Exception{
        try {
            connectDB();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(SQL);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally{
            disconnectDB();
        }
    }
    protected void consultDB (String SQL) throws Exception{
        try {
            connectDB();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(SQL);
        } catch (Exception e) {
            throw e;
        }
    }
} 
