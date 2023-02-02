package tienda.servicios;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.FabricanteDaoExt;
import tienda.persistencia.ProductoDaoExt;

public class ProductoServicio {
    ProductoDaoExt productDAO = new ProductoDaoExt();
    FabricanteDaoExt producerDAO = new FabricanteDaoExt();
    Scanner readNumber = new Scanner (System.in);
    Scanner readString = new Scanner (System.in);
    /**
     * Muestra los nombres de los productos
     *
     * @throws Exception
     */
    public void showProductsName() throws Exception {
        try {
            ArrayList<String> nombres = productDAO.searchProductName();
            for (Iterator<String> iterator = nombres.iterator(); iterator.hasNext();) {
                String next = iterator.next();
                System.out.println(next);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Muestra los nombres y los precios de todos los productos de la tabla
     * producto
     */
    public void showProductsNameNPrice() throws Exception {
        try {
            ArrayList<Producto> productos = productDAO.searchProductNameNPrice();
            for (Iterator<Producto> iterator = productos.iterator(); iterator.hasNext();) {
                Producto next = iterator.next();
                System.out.println("Nombre: " + next.getNombre());
                System.out.println("Precio: $" + next.getPrecio());
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Muestra aquellos productos que su precio esté entre 120 y 202
     */
    public void showProductsByPrice() throws Exception {
        try {
            ArrayList<Producto> productos = productDAO.searchProductByPrice();
            for (Iterator<Producto> iterator = productos.iterator(); iterator.hasNext();) {
                Producto next = iterator.next();
                System.out.println(next.toString());
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Muestra todos los Portátiles de la tabla producto.
     */
    public void showLaptop() throws Exception {
        try {
            ArrayList<Producto> productos = productDAO.searchLaptop();
            for (Iterator<Producto> iterator = productos.iterator(); iterator.hasNext();) {
                Producto next = iterator.next();
                System.out.println(next.toString());
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Muestra el producto más barato de la tabla.
     */
    public void showLowerPrice() throws Exception {
        try {
            ArrayList<Producto> productos = productDAO.searchLowerPrice();
            for (Iterator<Producto> iterator = productos.iterator(); iterator.hasNext();) {
                Producto next = iterator.next();
                System.out.println("Nombre: " + next.getNombre());
                System.out.println("Precio: $" + next.getPrecio());
                System.out.println("---------------------------");
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Permite al usuario ingresar los parámetros que desea cambiar 
     * @throws java.lang.Exception
     */
    public void modify () throws Exception {
        try {
            int option;
            do {                
                System.out.println("Seleccione lo que desea modificar: "
                        + "\n1. Nombre"
                        + "\n2. Precio"
                        + "\n3. Nombre y precio"
                        + "\n4. Salir");
                try {
                    option = readNumber.nextInt();
                } catch (InputMismatchException e) {
                    option = 0;
                }
                int codigo;
                String newName;
                double newPrice;
                switch(option){
                    case 1:
                        System.out.println("Ingrese el código del producto que desea modificar: ");
                        try {
                            codigo = readNumber.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("La opción a ingresar debe ser un número entero");
                            break;
                        }
                        System.out.println("Ingrese el nuevo nombre del producto: ");
                        newName = readString.nextLine();
                        productDAO.modifyName(codigo, newName);
                        break;
                    case 2:
                        System.out.println("Ingrese el código del producto que desea modificar: ");
                        try {
                            codigo = readNumber.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("La opción a ingresar debe ser un número entero");
                            break;
                        }
                        System.out.println("Ingrese el nuevo precio del producto: ");
                        newPrice = readNumber.nextDouble();
                        productDAO.modifyPrice(codigo, newPrice);
                        break;
                    case 3:
                        System.out.println("Ingrese el código del producto que desea modificar: ");
                        try {
                            codigo = readNumber.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("La opción a ingresar debe ser un número entero");
                            break;
                        }
                        System.out.println("Ingrese el nuevo nombre del producto: ");
                        newName = readString.nextLine();
                        System.out.println("Ingrese el nuevo precio del producto: ");
                        newPrice = readString.nextDouble();
                        productDAO.modifyNameNPrice(codigo, newName, newPrice);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("La opción ingresada no es correcta.");
                        break;
                }
            } while (option != 4);
        } catch (Exception e) {
        }
    }
    
    /**
     * Permite al usuario crear un producto nuevo y guardarlo en la base de datos
     */
    public void createProduct ()throws Exception{
        try {
            int producerCode;
            double productPrice;
            String productName;
            System.out.println("Ingrese el nombre del producto:");
            try {
                productName = readString.nextLine();   
            } catch (InputMismatchException e) {
                throw e;
            }
            System.out.println("Ingrese el precio del producto:");
            try {
                productPrice = readNumber.nextDouble();   
            } catch (InputMismatchException e) {
                throw e;
            }
            System.out.println("Ingrese el codigo del fabricante del producto:");
            try {
                producerCode = readNumber.nextInt();
                while (!producerDAO.searchProducer(producerCode)) {                    
                System.out.println("El codigo ingresado no pertenece a ningun fabricante. Por favor, indique otro codigo:");
                producerCode = readNumber.nextInt();
                }
            } catch (InputMismatchException e) {
                throw e;
            }
            Producto product = new Producto(null, productName, productPrice, producerCode);
            productDAO.saveProduct(product);
        } catch (Exception e) {
            throw e;
        }
    }
}
