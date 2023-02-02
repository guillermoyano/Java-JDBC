package tienda.en.clase;

import tienda.en.clase.servicios.TiendaServicio;

/**
 *
 * @author Guillote
 */
public class TiendaEnClase {

    public static void main(String[] args) throws Exception {
        
        TiendaServicio ts = new TiendaServicio();
        
        ts.menu();
    }

}
