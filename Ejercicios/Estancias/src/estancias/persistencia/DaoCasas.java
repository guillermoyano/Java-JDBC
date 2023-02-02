package estancias.persistencia;

import estancias.entidades.Casas;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class DaoCasas extends DAO {

    public void mostrarCasasDisponiblesEntre() throws Exception {
        try {
            String sql = "SELECT * FROM casas WHERE fecha_desde BETWEEN "
                    + "'2020-08-01' AND '2020-08-31' AND pais = 'Reino Unido';";
            consultarBase(sql);
            Casas casa = null;
            List<Casas> casuchas = new ArrayList();
            while (resultado.next()) {
                casa = new Casas();
                casa.setFecha_desde(resultado.getDate("Fecha_desde"));
                casa.setFecha_hasta(resultado.getDate("Fecha_hasta"));
                casa.setPais(resultado.getString(6));
                casuchas.add(casa);
            }
            for (Casas aux : casuchas) {
                System.out.println(aux.getPais() + " " + aux.getFecha_desde() + " " + aux.getFecha_hasta());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
        

    }

    public void vacacionesDesde(Date fecha_desde, Date fecha_hasta, int cant) throws Exception {
        try {
            String sql = "SELECT * FROM casas WHERE fecha_desde <= "
                    + "'" + new SimpleDateFormat("yyyy-MM-dd").format(fecha_desde)
                    + "' AND fecha_hasta >='" + new SimpleDateFormat("yyyy-MM-dd").format(fecha_hasta) + "'"
                    + " AND tiempo_minimo <= " + cant + " AND tiempo_maximo >= " + cant + ";";
            consultarBase(sql);
            Casas casa = null;
            List<Casas> casuchas = new ArrayList();
            while (resultado.next()) {
                casa = new Casas();
                casa.setFecha_desde(resultado.getDate("Fecha_desde"));
                casa.setFecha_hasta(resultado.getDate("Fecha_hasta"));
                casa.setId_casa(resultado.getInt(1));
                casuchas.add(casa);
            }
            for (Casas aux : casuchas) {
                System.out.println(aux.getId_casa() + " " + aux.getFecha_desde() + " " + aux.getFecha_hasta());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

}
