/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import entidad.Fabricante;
import java.util.Scanner;
import persistencia.FabricanteDao;

/**
 *
 * @author Usuario
 */
public class ServicioFabricante {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void IngresarFabricante() throws Exception {
        try {
            Fabricante miFabricante = new Fabricante();
            System.out.println("Ingrese nombre del Fabricamte");
            String nombreFabricante = leer.next();
            if (FabricanteDao.buscarFabricantePorNombre(nombreFabricante) != null) {
                System.out.println("El fabricante ya existe");
            } else {
                miFabricante.setNombre(nombreFabricante);
                FabricanteDao.guardarFabricante(miFabricante);
                System.out.println("El fabricante ingresado es: " + miFabricante.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
