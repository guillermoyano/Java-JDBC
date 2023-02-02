/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidad.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public final class ProductoDao extends DAO {

    public static void guardarProducto(Producto Producto, int fabricante) throws Exception {
        try {
            if (Producto == null) {
                throw new Exception("Debe indicar el producto");
            }
            String consultaCodigo = "SELECT * FROM Producto  ORDER BY codigo DESC LIMIT 0,1";
            consultarBase(consultaCodigo);
            int codigo = 0;
            while (resultado.next()) {
                codigo = resultado.getInt(1);
                Producto.setCodigo(codigo + 1);
            }
            codigo = codigo + 1;
            String sql = "INSERT INTO Producto (codigo,nombre, precio,codigo_fabricante)"
                    + "VALUES ( '" + codigo + "' , '" + Producto.getNombre() + "' , '" + Producto.getPrecio() + "' , '" + fabricante + "' );";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto que desea modificar");
            }
            String sql = "UPDATE Producto SET "
                    + "precio = '" + producto.getPrecio() + "' WHERE nombre = '" + producto.getNombre() + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static void modificarProductoPrecio(double productoPrecio, String productoNombre) throws Exception {
        try {
            String sql = "UPDATE Producto SET "
                    + "precio = '" + productoPrecio + "' WHERE nombre = '" + productoNombre + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static void modificarProductoNombre(String nuevoNombre, String nombreAnterior) throws Exception {
        try {
            String sql = "UPDATE Producto SET "
                    + "nombre = '" + nuevoNombre + "' WHERE nombre LIKE '" + nombreAnterior + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static void modificarFabricante(int nuevoCodigo, String nombre) throws Exception {
        try {
            String sql = " SET SQL_SAFE_UPDATES = 0; "
                    + "UPDATE Producto SET "
                    + "codigo_fabricante = '" + nuevoCodigo + "' WHERE nombre LIKE '" + nombre + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static void eliminarProducto(String nombre) throws Exception {
        try {
            String sql = "DELETE FROM Producto WHERE nombre = '" + nombre + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static Producto buscarProductoPorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM Producto "
                    + " WHERE nombre = '" + nombre + "'";
            consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public static List<Producto> listarProductos() throws Exception {
        try {
            String sql = "SELECT * FROM Producto ";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> producto_e = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto_e.add(producto);
            }
            desconectarBase();
            return producto_e;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public static Producto buscarProductoPorCodigo(Integer codigo) throws Exception {
        try {
            String sql = "SELECT * FROM Producto "
                    + " WHERE codigo = '" + codigo + "'";
            consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public static List<Producto> listarProductosPorNombrePrecio() throws Exception {
        try {
            String sql = "SELECT nombre,precio FROM Producto ";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> productoList = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                productoList.add(producto);
            }
            desconectarBase();
            return productoList;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public static List<Producto> listarProductosPrecioEntre120y202() throws Exception {
        try {
            String sql = "SELECT * FROM producto   WHERE precio >= 120 AND precio <= 220 ORDER BY precio ASC";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> productoList = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                //producto_p.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                productoList.add(producto);
            }
            desconectarBase();
            return productoList;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public static List<Producto> listarProductosPortatiles() throws Exception {
        try {
            String sql = "SELECT * FROM producto   WHERE nombre LIKE '%Portátil%'";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> productoList = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                //  producto_p.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                productoList.add(producto);
            }
            desconectarBase();
            return productoList;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public static List<Producto> listarProductosPorNombrePrecioMasBaratos() throws Exception {
        try {
            String sql = "SELECT * FROM Producto ORDER BY precio ASC LIMIT 0,1 ";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> productoList = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                productoList.add(producto);
            }
            desconectarBase();
            return productoList;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

}
