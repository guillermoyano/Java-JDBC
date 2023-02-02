package tienda;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDaoExt;
import tienda.servicios.FabricanteServicio;
import tienda.servicios.ProductoServicio;

/**
 *
 * @author Usuario
 */
public class Tienda {

    /**
     * a) Lista el nombre de todos los productos que hay en la tabla producto.✓
     * b) Lista los nombres y los precios de todos los productos de la tabla producto.✓
     * c) Listar aquellos productos que su precio esté entre 120 y 202.✓
     * d) Buscar y listar todos los Portátiles de la tabla producto.✓
     * e) Listar el nombre y el precio del producto más barato.✓
     * f) Ingresar un producto a la base de datos.✓
     * g) Ingresar un fabricante a la base de datos✓
     * h) Editar un producto con datos a elección.✓
     * @param args 
     */
    public static void main(String[] args) {
        ProductoServicio productService = new ProductoServicio();
        FabricanteServicio producerService = new FabricanteServicio ();
        Scanner readNumber = new Scanner (System.in);
        try {
            int option;
            do{
                System.out.println("Ingrese la opción que desea realizar:"
                        + "\n1. Ver los nombres de los productos"
                        + "\n2. Ver los nombres y precios de los productos"
                        + "\n3. Ver los productos con precio entre $120 y $202"
                        + "\n4. Ver las computadoras portátiles"
                        + "\n5. Ver el producto más barato"
                        + "\n6. Ingresar un nuevo producto"
                        + "\n7. Ingresar un nuevo fabricante"
                        + "\n8. Editar un producto existente"
                        + "\n9. Salir");
                try {
                   option = readNumber.nextInt(); 
                } catch (InputMismatchException e) {
                    option = 0;
                }
                switch (option){
                    case 1:
                        productService.showProductsName();
                        break;
                    case 2:
                        productService.showProductsNameNPrice();
                        break;
                    case 3:
                        productService.showProductsByPrice();
                        break;
                    case 4:
                        productService.showLaptop();
                        break;
                    case 5:
                        productService.showLowerPrice();
                        break;
                    case 6:
                        productService.createProduct();
                        break;
                    case 7:
                        producerService.createProduct();
                        break;
                    case 8:
                        productService.modify();
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("La opción ingresada es incorrecta.");
                        break;
                }
            } while (option != 9);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
