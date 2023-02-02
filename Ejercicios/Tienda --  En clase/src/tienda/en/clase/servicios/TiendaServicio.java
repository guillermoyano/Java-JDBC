/*
Realizar un menú en Java a través del cual se permita elegir qué consulta se desea realizar. 
Las consultas a realizar sobre la BD son las siguientes:
a) Lista el nombre de todos los productos que hay en la tabla producto. 
b) Lista los nombres y los precios de todos los productos de la tabla producto. 
c) Listar aquellos productos que su precio esté entre 120 y 202. 
d) Buscar y listar todos los Portátiles de la tabla producto. 
e) Listar el nombre y el precio del producto más barato. 
f) Ingresar un producto a la base de datos.
g) Ingresar un fabricante a la base de datos
h) Editar un producto con datos a elección.
*/
package tienda.en.clase.servicios;

import java.util.Scanner;
import tienda.en.clase.persistencia.DAOproducto;

public class TiendaServicio{

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    ProductoServicio p = new ProductoServicio();
    FabricanteServicio fs = new FabricanteServicio();
    
    public void menu() throws Exception {
        int opc = 0;
        do {
            System.out.println("\t-------- ELIGE LA OPCION-------");
            System.out.println("Bienvenidos a tienda LOS HIJOS DE PUTA");
            System.out.println("1-  Lista el nombre de todos los productos que hay en la tabla producto. ");
            System.out.println("2-  Lista los nombres y los precios de todos los productos de la tabla producto. ");
            System.out.println("3-  Listar aquellos productos que su precio esté entre 120 y 202.");
            System.out.println("4-  Buscar y listar todos los Portátiles de la tabla producto. ");
            System.out.println("5-  Listar el nombre y el precio del producto más barato. ");
            System.out.println("6-  Ingresar un producto a la base de datos.");
            System.out.println("7-  Ingresar un fabricante a la base de datos");
            System.out.println("8-  Editar un producto con datos a elección.");
            System.out.println("9-  SALIR");
            System.out.println("");
            opc = leer.nextInt();
            switch (opc) {
                case 1:
                    p.mostrarProductos();
                    break;
                case 2:
                    p.mostrarProductosyPrecios();
                    break;
                case 3:
                    p.mostrarPreciosentre120y202();
                    break;
                case 4:
                    p.buscarPortatiles();
                    break;
                case 5:
                    p.masBarato();
                    break;
                case 6:
                    p.ingresarProducto();
                    break;
                case 7:
                    fs.ingresarFabricante();
                    break;
                case 8:
                    p.submenu();
                    break;
                case 9:
                    opc = 0;
                    break;
                default:
                    System.out.println("La opcion elegida es invalida");
            }
        } while (opc != 0);
    }
    
}
