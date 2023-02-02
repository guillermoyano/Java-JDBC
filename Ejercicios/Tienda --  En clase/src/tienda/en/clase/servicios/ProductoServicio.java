package tienda.en.clase.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import tienda.en.clase.entidades.Fabricante;
import tienda.en.clase.entidades.Producto;
import tienda.en.clase.persistencia.DAO;
import tienda.en.clase.persistencia.DAOFabricante;
import tienda.en.clase.persistencia.DAOproducto;

public class ProductoServicio extends DAO {

    DAOproducto dp = new DAOproducto();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void mostrarProductos() throws Exception {

        try {
            String sql = "Select * from producto";

            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productitos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productitos.add(producto);
            }

            for (Producto aux : productitos) {
                System.out.println(aux.toString());
            }
            System.out.println("");
            desconectarBase();

        } catch (Exception e) {
            throw e;
        }
    }

    public void mostrarProductosyPrecios() throws Exception {

        try {
            String sql = "Select * from producto";

            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productitos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productitos.add(producto);
            }

            for (Producto aux : productitos) {
                System.out.println(aux.getNombre() + "--  $" + aux.getPrecio());
            }
            System.out.println("");
            desconectarBase();

        } catch (Exception e) {
            throw e;
        }
    }

    public void mostrarPreciosentre120y202() throws Exception {

        try {
            String sql = "Select * from producto where precio > 120 and precio < 202";

            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productitos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productitos.add(producto);
            }
            for (Producto aux : productitos) {
                System.out.println(aux.getNombre() + "--  $" + aux.getPrecio());
            }
            System.out.println("");
            desconectarBase();

        } catch (Exception e) {
            throw e;
        }
    }

    public void buscarPortatiles() throws Exception {
        try {
            String sql = "Select * from producto where nombre like '%Portatil%'";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productitos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productitos.add(producto);
            }
            for (Producto aux : productitos) {
                System.out.println(aux.getNombre());
            }
            System.out.println("");
            desconectarBase();
        } catch (Exception e) {
            throw e;
        }
    }

    public void masBarato() throws Exception {
        try {
            String sql = "Select * from producto where precio = (Select min(precio) from producto)";

            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productitos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productitos.add(producto);
            }
            for (Producto aux : productitos) {
                System.out.println(aux.getNombre() + "--  $" + aux.getPrecio());
            }
            System.out.println("");
            desconectarBase();
        } catch (Exception e) {
            throw e;
        }
    }

    DAOFabricante df = new DAOFabricante();

    public void ingresarProducto() throws Exception {

        Producto productito = new Producto();
        String sql = "SELECT * FROM Producto  ORDER BY codigo DESC LIMIT 1";
        consultarBase(sql);
        int codigo = 0;
        while (resultado.next()) {
            codigo = resultado.getInt(1);
            productito.setCodigo(codigo + 1);
        }
        codigo = codigo + 1;

        try {
            System.out.println("------------INGRESAR PRODUCTO------------");
            Producto miProducto = new Producto();
            Fabricante miFabricante = new Fabricante();
            System.out.println("Ingrese nombre del producto");
            miProducto.setNombre(leer.next());
            System.out.println("Ingrese precio del producto");
            miProducto.setPrecio(leer.nextDouble());
            df.mostrarFabricantes();
            System.out.println("Ingrese el nombre del fabricante");
            String nombreFabricante = leer.next();
            miFabricante = df.buscarFabricantePorNombre(nombreFabricante);
            if (miFabricante != null) {
                miProducto.setCodigo(codigo);
                miProducto.setCodigoFabricante(miFabricante.getCodigo());
                dp.instertInto(miProducto);
                System.out.println("El producto ingresado es: " + miProducto.toString());
            } else {
                System.out.println("No ta");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    //Editar un producto con datos a elección
    //precio
    //nombre del producto
    //codigo de fabricante
    public void submenu() throws Exception {

        int opc = 0;

        System.out.println("-------- ELIGE LA OPCION-------");
        System.out.println("1- Modificar precio");
        System.out.println("2- Modificar nombre del producto");
        System.out.println("3- Modificar código de fabricante");

        opc = leer.nextInt();
        switch (opc) {
            case 1:
                cambiarPrecio();
                break;
            case 2:
                cambiarNombre();
                break;
            case 3:
                cambiarCodigoFabricante();
                break;
            default:
                System.out.println("La opcion elegida es invalida");
        }

    }

    public void cambiarPrecio() throws Exception {

        try {
            System.out.println("");
            mostrarProductos();
            System.out.println("");
            System.out.println("Elija de la siguiente lista el producto que quiere modificar el precio por número de código");
            int codigo = leer.nextInt();
            Producto producto = new Producto();
            String sql = "Select * from producto where codigo = '" + codigo + "'";
            consultarBase(sql);
            while (resultado.next()) {
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
            }
            System.out.println("Ingresá el nuevo precio del producto " + producto.getNombre());
            double precioss = leer.nextDouble();
            String otrosql = "Update producto set precio = '" + precioss + "' where codigo = '" + producto.getCodigo() + " ' ";
            insertarModificarEliminar(otrosql);
        } catch (Exception e) {
            System.out.println("Ese código no está en la lista");
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void cambiarNombre() throws Exception {
        try {
            System.out.println("");
            mostrarProductos();
            System.out.println("");
            System.out.println("Elija de la siguiente lista el producto que quiere modificar el nombre por número de código");
            int codigo = leer.nextInt();
            Producto producto = new Producto();
            String sql = "Select * from producto where codigo = '" + codigo + "'";
            consultarBase(sql);
            while (resultado.next()) {
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
            }
            System.out.println("Ingresá el nuevo precio del producto " + producto.getNombre());
            String nombre = leer.next();
            String otrosql = "Update producto set nombre = '" + nombre + "' where codigo = '" + producto.getCodigo() + " ' ";
            insertarModificarEliminar(otrosql);
            System.out.println("");
            System.out.println("El nuevo nombre del producto es: " + nombre);
        } catch (Exception e) {
            System.out.println("Ese código no está en la lista");
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void cambiarCodigoFabricante() throws Exception {
        try {
            System.out.println("");
            mostrarProductos();
            System.out.println("");
            System.out.println("Elija de la siguiente lista,  el producto que quiere modificar el código de Fabricante por número de código");
            System.out.println("Y por ende el Fabricante (la cachais bolu?)");

            int codigo = leer.nextInt();
            Producto producto = new Producto();
            String sql = "Select * from producto where codigo = '" + codigo + "'";
            consultarBase(sql);
            while (resultado.next()) {
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setCodigoFabricante(resultado.getInt(3));
            }
            System.out.println("Ingresá el nuevo código de Fabricante " + producto.getNombre());
            int codigoFabricante = leer.nextInt();
            if (df.verificarFabricante(codigoFabricante) != null) {
                String otrosql = "Update producto set codigo_fabricante = '" + codigoFabricante + "' where codigo = '" + producto.getCodigo() + " ' ";
                insertarModificarEliminar(otrosql);
                System.out.println("");
                System.out.println("El nuevo código de fabricante del producto: " + producto.getNombre() + " es " + codigoFabricante);
            } else {
                System.out.println("Ese código no esiste maestro");
            }
        } catch (Exception e) {
            System.out.println("Ese código no está en la lista");
            throw e;
        } finally {
            desconectarBase();
        }
    }

}
