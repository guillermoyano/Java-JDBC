package tienda.persistencia;

import java.util.ArrayList;
import java.util.Scanner;
import tienda.entidades.Producto;

public final class ProductoDaoExt extends DAO{
    Scanner readNumber = new Scanner(System.in);
    Scanner readString = new Scanner(System.in);
    
    public void saveProduct(Producto producto)throws Exception{
        try {
            if (producto == null) {
                throw new Exception ("El producto no puede estar vacío.");
            }
            
            String SQL = "INSERT INTO producto (nombre, precio, codigo_fabricante)"
                    + "VALUES ( '" + producto.getNombre()
                    + "', '" + producto.getPrecio() + "', '" + producto.getCodigoFabricante() + "') ";
            insertModifyDelete(SQL);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * modifica el nombre del producto
     * @throws Exception 
     */
    public void modifyName(int codigoProducto, String newName) throws Exception{
        try {
            String SQL = "UPDATE producto SET nombre = '" + newName + "' WHERE codigo =" + codigoProducto + ";";
            insertModifyDelete(SQL);
        } catch (Exception e) {
            throw e;
        }          
    }
    /**
     * modifica el precio del producto
     * @throws Exception 
     */
    public void modifyPrice(int codigoProducto, double newPrice) throws Exception{
        try {
            String SQL = "UPDATE producto SET precio = " + newPrice + " WHERE codigo =" + codigoProducto + ";";
            insertModifyDelete(SQL);
        } catch (Exception e) {
            throw e;
        }     
    }
    /**
     * modifica el nombre y precio del producto
     * @throws Exception 
     */
    public void modifyNameNPrice(int codigoProducto, String newName, double newPrice) throws Exception{
        try {
            String SQL = "UPDATE producto SET nombre = '" + newName + "', precio = " + newPrice + " WHERE codigo =" + codigoProducto + ";";
            insertModifyDelete(SQL);
        } catch (Exception e) {
            throw e;
        }           
    }
    /**
     * Lista el nombre de todos los productos que hay en la tabla producto.
     * @return
     * @throws Exception 
     */
    public ArrayList <String> searchProductName () throws Exception{
        ArrayList <String> productList = new ArrayList();
        try {
            String SQL = "SELECT nombre FROM producto";
            consultDB(SQL);
            while (resultado.next()){
                productList.add(resultado.getString(1));    
            }
        } catch (Exception e) {
            disconnectDB();
            throw e;
        } finally{
            disconnectDB();
        }
        return productList;
    }
    /**
     * Lista los nombres y los precios de todos los productos de la tabla producto
     */
    public ArrayList <Producto> searchProductNameNPrice ()throws Exception{
        ArrayList <Producto> productList = new ArrayList();
        Producto product = null;
        try {
            String SQL = "SELECT nombre, precio FROM producto";
            consultDB(SQL);
            while (resultado.next()) {     
                product = new Producto();
                product.setNombre(resultado.getString(1));
                product.setPrecio(resultado.getDouble(2));
                productList.add(product);
            }
        } catch (NullPointerException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
        return productList;
    }
    /**
     * Listar aquellos productos que su precio esté entre 120 y 202
     */
    public ArrayList <Producto> searchProductByPrice ()throws Exception{
        ArrayList <Producto> productList = new ArrayList();
        Producto product = null;
        try {
            String SQL = "SELECT * FROM producto WHERE precio > 120 AND precio <202;";
            consultDB(SQL);
            while (resultado.next()) {     
                product = new Producto();
                product.setCodigo(resultado.getInt(1));
                product.setNombre(resultado.getString(2));
                product.setPrecio(resultado.getDouble(3));
                product.setCodigoFabricante(resultado.getInt(4));
                productList.add(product);
            }
        } catch (NullPointerException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
        return productList;
    }
    /**
     * Buscar y listar todos los Portátiles de la tabla producto.
     */
    public ArrayList <Producto> searchLaptop ()throws Exception{
        ArrayList <Producto> productList = new ArrayList();
        Producto product = null;
        try {
            String SQL = "SELECT * FROM producto WHERE nombre like('%portatil%');";
            consultDB(SQL);
            while (resultado.next()) {     
                product = new Producto();
                product.setCodigo(resultado.getInt(1));
                product.setNombre(resultado.getString(2));
                product.setPrecio(resultado.getDouble(3));
                product.setCodigoFabricante(resultado.getInt(4));
                productList.add(product);
            }
        } catch (NullPointerException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
        return productList;
    }
    /**
     * Listar el nombre y el precio del producto más barato
     */
    public ArrayList <Producto> searchLowerPrice ()throws Exception{
        ArrayList <Producto> productList = new ArrayList();
        Producto product = null;
        try {
            String SQL = "SELECT nombre,precio FROM producto WHERE precio = (SELECT MIN(precio) FROM producto);";
            consultDB(SQL);
            while (resultado.next()) {     
                product = new Producto();
                product.setNombre(resultado.getString(1));
                product.setPrecio(resultado.getDouble(2));
                productList.add(product);
            }
        } catch (NullPointerException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
        return productList;
    }
}
