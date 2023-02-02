package tienda.persistencia;

import java.util.Scanner;
import tienda.entidades.Fabricante; 
import tienda.entidades.Producto;

public final class FabricanteDaoExt extends DAO{
    Scanner readNumber = new Scanner(System.in);
    Scanner readString = new Scanner(System.in);
    public void guardarFabricante(Fabricante fabricante)throws Exception{
        try {
            if (fabricante == null) {
                throw new Exception ("El producto no puede estar vacío.");
            }
            
            String SQL = "INSERT INTO producto (nombre)"
                    + "VALUES ( '" + fabricante.getNombre() + "');";
            
            insertModifyDelete(SQL);
        } catch (Exception e) {
            throw e;
        }
    }
    public boolean searchProducer (int producerCode) throws Exception{
        boolean answer;
        try {
            String SQL = "SELECT COUNT(codigo) FROM fabricante WHERE codigo = " + producerCode + ";";
            consultDB(SQL);
            int producerQuantity;
            if (resultado.next()) {
                producerQuantity = resultado.getInt(1);
            } else{
                producerQuantity = 0;
            }
            if (producerQuantity == 0) {
                answer = false;
            } else{
                answer = true;
            }
            return answer;
        } catch (Exception e) {
            throw e;
        } finally {
            disconnectDB();
        }
    }
}
