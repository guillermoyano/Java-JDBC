package tienda.en.clase.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.en.clase.entidades.Fabricante;

public class DAOFabricante extends DAO {

    public Collection<Fabricante> mostrarFabricantes() throws Exception {

        try {
            String sql = "Select * from fabricante";
            consultarBase(sql);
            Fabricante fabricante = null;
            Collection<Fabricante> marcas = new ArrayList();
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                marcas.add(fabricante);
            }
            for (Fabricante aux : marcas) {
                System.out.println(aux.getCodigo() + " - " + aux.getNombre());
            }
            return marcas;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Fabricante verificarFabricante(int codigo) throws Exception {

        try {
            String sql = "Select * from fabricante where codigo =  '  " + codigo + " ' ";
            consultarBase(sql);
            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            return fabricante;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void instertIntoFabricante(Fabricante miFabricante) throws Exception {

        try {
            if (miFabricante == null) {
                throw new Exception("Debe indicar el fabricante");
            }
            String consulta_codigo = "SELECT * FROM Fabricante  ORDER BY codigo DESC LIMIT 0,1";
            consultarBase(consulta_codigo);
            int codigo = 0;
            while (resultado.next()) {
                codigo = resultado.getInt(1);
                miFabricante.setCodigo(codigo + 1);
            }
            codigo = codigo + 1;
            String sql = "INSERT INTO Fabricante (codigo,nombre)"
                    + "VALUES ( '" + codigo + "' , '" + miFabricante.getNombre() + "' );";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Fabricante buscarFabricantePorNombre(String fabricante) throws Exception {
        try {
            String sql = "SELECT * FROM Fabricante "
                    + " WHERE nombre = '" + fabricante + "'";
            consultarBase(sql);
            Fabricante miFabricante = null;
            while (resultado.next()) {
                miFabricante = new Fabricante();
                miFabricante.setCodigo(resultado.getInt(1));
                miFabricante.setNombre(resultado.getString(2));
            }
            return miFabricante;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
}
