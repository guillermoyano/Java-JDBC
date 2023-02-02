/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import entidad.Fabricante;
import entidad.Producto;
import java.util.Scanner;
import persistencia.FabricanteDao;
import persistencia.ProductoDao;

/**
 *
 * @author Usuario
 */
public class ServicioProducto {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void mostrarNombreProductos() throws Exception {
        try {
            System.out.println("------------LISTA NOMBRE DE LOS PRODUCTOS------------");
            for (Producto i : ProductoDao.listarProductos()) {
                System.out.println("Nombre=  " + i.getNombre());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void ListarNombrePrecioProducto() throws Exception {
        try {
            System.out.println("------------LISTA NOMBRE Y PRECIO DE TODOS LOS PRODUCTOS------------");
            for (Producto i : ProductoDao.listarProductos()) {
                System.out.println("Nombre=  " + i.getNombre() +"\n Precio= " + i.getPrecio());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void ListarPrecioProductoEntre() throws Exception {
        try {
            System.out.println("------------LISTA PRECIO ENTRE 120 Y 202 DE TODOS LOS PRODUCTOS------------");
            for (Producto i : ProductoDao.listarProductosPrecioEntre120y202()) {
                System.out.println("Nombre=  " + i.getNombre() +"\n Precio= " + i.getPrecio());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void ListarPortatiles() throws Exception {
        try {
            System.out.println("------------LISTA PRODUCTOS PORTATILES------------");
            for (Producto i : ProductoDao.listarProductosPortatiles()) {
                System.out.println("Nombre=  " + i.getNombre() +"\n Precio= " + i.getPrecio());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void ProductosMasBaratos() throws Exception {
        try {
            System.out.println("------------PRODUCTO MAS BARATO------------");
            for (Producto i : ProductoDao.listarProductosPorNombrePrecioMasBaratos()) {
                System.out.println("Nombre=  " + i.getNombre() +"\n Precio= " + i.getPrecio());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void IngresarProductos() throws Exception {
        try {
            System.out.println("------------INGRESAR PRODUCTO------------");
            Producto miProducto = new Producto();
            Fabricante miFabricante = new Fabricante();
            System.out.println("Ingrese nombre del producto");
            miProducto.setNombre(leer.next());
            System.out.println("Ingrese precio del producto");
            miProducto.setPrecio(leer.nextDouble());
            System.out.println("Ingrese nombre del Fabricamte");
            String nombreFabricante = leer.next();
            if (FabricanteDao.buscarFabricantePorNombre(nombreFabricante) != null) {
                miFabricante.setNombre(nombreFabricante);
                int codigo = FabricanteDao.buscarCodigoFabricantePorNombre(nombreFabricante);
                miFabricante.setCodigo(codigo);
                miProducto.setCodigoFabricante(miFabricante);
                ProductoDao.guardarProducto(miProducto, codigo);
                System.out.println("El producto ingresado es: " + miProducto.toString());
            } else {
                System.out.println("El nombre del fabricante no existe");
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public void modificarPrecio() throws Exception {
        try {
            System.out.println("-------MODIFICAR PRECIO DE PRODUCTO--------");
            System.out.println("Ingrese el nombre del producto que desea modificar");
            String nombreProducto = leer.next();
            if (ProductoDao.buscarProductoPorNombre(nombreProducto) != null) {
                System.out.println("Ingrese el nuevo precio");
                double precioProdcuto = leer.nextDouble();
                ProductoDao.modificarProductoPrecio(precioProdcuto, nombreProducto);
                System.out.println("El precio del producto fue cambiado con exito");
            } else {
                System.out.println("El nombre del producto no existe");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarNombre() throws Exception {
        try {
            System.out.println("-------MODIFICAR NOMBRE DE PRODUCTO--------");
            System.out.println("Ingrese el nombre del producto que desea modificar");
            String nombreProducto = leer.next();
            if (ProductoDao.buscarProductoPorNombre(nombreProducto) != null) {
                System.out.println("Ingrese el nuevo nombre");
                String nuevoNombre = leer.next();
                ProductoDao.modificarProductoNombre(nuevoNombre, nombreProducto);
                System.out.println("El nombre del producto fue cambiado con exito");
            } else {
                System.out.println("El nombre del producto no existe");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarFabricante() throws Exception {
        System.out.println("-------MODIFICAR FABRICANTE DEL PRODUCTO--------");

        System.out.println("Ingrese el nombre el nombre del producto que desea modificar");
        String nombreProducto = leer.next();

        if (ProductoDao.buscarProductoPorNombre(nombreProducto) != null) {
            System.out.println("Ingrese el nuevo fabricante");
            String nuevoNombreFabricante = leer.next();
            if (FabricanteDao.buscarFabricantePorNombre(nuevoNombreFabricante) != null) {

                int codigo = FabricanteDao.buscarCodigoFabricantePorNombre(nuevoNombreFabricante);
                ProductoDao.modificarFabricante(codigo, nombreProducto);
            } else {
                System.out.println("El nombre del fabricante ingresado no existe");
            }
        } else {
            System.out.println("El fabricante ingresado no existe");
        }
    }

    public void ModificarCualquierValor() throws Exception {
        try {
            System.out.println("Que desea modificar? 1-precio, 2-nombre, 3-fabricante");
            int opc2 = leer.nextInt();
            switch (opc2) {
                case 1:
                    modificarPrecio();
                    break;
                case 2:
                    modificarNombre();
                    break;
                case 3:
                    modificarFabricante();
                    break;
                default:
                    System.out.println("La opcion elegida es invalida");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
