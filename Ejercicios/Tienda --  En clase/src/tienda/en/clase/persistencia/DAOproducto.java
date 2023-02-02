package tienda.en.clase.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.en.clase.entidades.Producto;

public class DAOproducto extends DAO {

    public void instertInto(Producto miProducto) throws Exception {

        try {

            if (miProducto == null) {
                throw new Exception("Ingresa algo que está vacío");
            }

            String sql = "INSERT INTO PRODUCTO (codigo, nombre, precio, codigo_fabricante) "
                    + "VALUES ( '" + miProducto.getCodigo() + "' , '" + miProducto.getNombre() + "' ,'"
                    + miProducto.getPrecio() + "','" + miProducto.getCodigoFabricante() + "' );";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }
}
