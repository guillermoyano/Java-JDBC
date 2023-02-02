/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import servicio.TiendaServicio;

/**
 *
 * @author Usuario
 */
public class Tienda {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            TiendaServicio miTienda = new TiendaServicio();
            miTienda.menu();
        } catch (Exception e) {
            throw e;
        }
    }
}
