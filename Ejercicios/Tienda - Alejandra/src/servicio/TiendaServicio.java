/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import servicio.ServicioProducto;
import servicio.ServicioFabricante;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class TiendaServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void menu() throws Exception {
        int opc = 0;
        ServicioProducto miServicioP = new ServicioProducto();
        ServicioFabricante miFabricante = new ServicioFabricante();
        do {
            System.out.println("-------- ELIGE LA OPCION-------");
            System.out.println("1-Lista el nombre de todos los productos que hay en la tabla producto.");
            System.out.println("2-Lista los nombres y los precios de todos los productos de la tabla producto.");
            System.out.println("3-Listar aquellos productos que su precio esté entre 120 y 202.");
            System.out.println("4-Buscar y listar todos los Portátiles de la tabla producto.");
            System.out.println("5-Listar el nombre y el precio del producto más barato.");
            System.out.println("6-Ingresar un producto a la base de datos.");
            System.out.println("7-Ingresar un fabricante a la base de datos");
            System.out.println("8-Editar un producto con datos a elección.");
            System.out.println("9-SALIR");
            opc = leer.nextInt();
            switch (opc) {
                case 1:
                    miServicioP.mostrarNombreProductos();
                    break;
                case 2:
                    miServicioP.ListarNombrePrecioProducto();
                    break;
                case 3:
                    miServicioP.ListarPrecioProductoEntre();
                    break;
                case 4:
                    miServicioP.ListarPortatiles();
                    break;
                case 5:
                    miServicioP.ProductosMasBaratos();
                    break;
                case 6:
                    miServicioP.IngresarProductos();
                    break;
                case 7:
                    miFabricante.IngresarFabricante();
                    break;
                case 8:
                    miServicioP.ModificarCualquierValor();
                    break;
                case 9:
                    opc=0;
                    break;
                default:
                    System.out.println("La opcion elegida es invalida");
            }
        } while (opc != 0);
    }
}
