package tienda.en.clase.servicios;

import java.util.Scanner;
import tienda.en.clase.entidades.Fabricante;
import tienda.en.clase.persistencia.DAO;
import tienda.en.clase.persistencia.DAOFabricante;

public class FabricanteServicio extends DAO {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    DAOFabricante frs = new DAOFabricante();
    
    public void ingresarFabricante() throws Exception {
        
      try {
            Fabricante miFabricante = new Fabricante();
            System.out.println("Ingrese nombre del Fabricamte");
            String nombreFabricante = leer.next();
            if (frs.buscarFabricantePorNombre(nombreFabricante) != null) {
                System.out.println("El fabricante ya existe");
            } else {
                miFabricante.setNombre(nombreFabricante);
                frs.instertIntoFabricante(miFabricante);
                System.out.println("El fabricante ingresado es: " + miFabricante.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    
    
    
}
