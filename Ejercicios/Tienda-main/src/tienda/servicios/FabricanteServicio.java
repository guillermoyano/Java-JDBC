package tienda.servicios;

import java.util.InputMismatchException;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDaoExt;

public class FabricanteServicio {
    Scanner readString = new Scanner (System.in);
    Scanner readNumber = new Scanner (System.in);
    FabricanteDaoExt producerDAO = new FabricanteDaoExt();
    /**
     * Crea un fabricante y lo guarda en la base de datos
     * @throws Exception 
     */
    public void createProduct ()throws Exception{
        try {
            String producerName;
            System.out.println("Ingrese el nombre del fabricante: ");
            try {
                producerName = readString.nextLine();
            } catch (InputMismatchException e) {
                throw e;
            }
            Fabricante producer = new Fabricante(null, producerName);
            producerDAO.guardarFabricante(producer);
        } catch (Exception e) {
            throw e;
        }
    }
}
