/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidad.Fabricante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public final class FabricanteDao extends DAO {

    public static void guardarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar el fabricante");
            }
            String consulta_codigo = "SELECT * FROM Fabricante  ORDER BY codigo DESC LIMIT 0,1";
            consultarBase(consulta_codigo);
            int codigo = 0;
            while (resultado.next()) {
                codigo = resultado.getInt(1);
                fabricante.setCodigo(codigo + 1);
            }
            codigo = codigo + 1;
            String sql = "INSERT INTO Fabricante (codigo,nombre)"
                    + "VALUES ( '" + codigo + "' , '" + fabricante.getNombre() + "' );";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static void modificarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar el fabricante que desea modificar");
            }

            String sql = "UPDATE Fabricante SET "
                    + "nombre = '" + fabricante.getNombre() + "' WHERE nombre = '" + fabricante.getNombre() + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static void eliminarFabricante(String fabricante) throws Exception {
        try {
            String sql = "DELETE FROM Producto WHERE nombre = '" + fabricante + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

 

    public static List<Fabricante> listarFabricantes() throws Exception {
        try {
            String sql = "SELECT * FROM Fabricantes ";
            consultarBase(sql);
            Fabricante fabricante = null;
            List<Fabricante> fabricanteList = new ArrayList();
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setNombre(resultado.getString(2));
                fabricanteList.add(fabricante);
            }
            desconectarBase();
            return fabricanteList;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public static Fabricante buscarFabricantePorCodigo(Integer codigo_e) throws Exception {
        try {
            String sql = "SELECT * FROM Fabricante "
                    + " WHERE codigo = '" + codigo_e + "'";
            consultarBase(sql);
            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

       public static Fabricante buscarFabricantePorNombre(String fabricante) throws Exception {
        try {
            String sql = "SELECT * FROM Fabricante "
                    + " WHERE nombre = '" + fabricante + "'";
            consultarBase(sql);
            Fabricante miFabricante = null;
            while (resultado.next()) {
                miFabricante = new Fabricante();
                miFabricante.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return miFabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public static int buscarCodigoFabricantePorNombre(String fabricante) throws Exception {
        try {
            String sql = "SELECT codigo FROM Fabricante "
                    + " WHERE nombre LIKE '" + fabricante + "'";
            consultarBase(sql);
            int codigo = 0;
            Fabricante fabricante_p = null;
            while (resultado.next()) {
                fabricante_p = new Fabricante();
                fabricante_p.setCodigo(resultado.getInt(1));
                codigo = resultado.getInt(1);
            }
            desconectarBase();
            return codigo;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
}
