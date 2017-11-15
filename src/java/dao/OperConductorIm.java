package dao;

import conectar.conexion;
import dto.Conductor;
import javax.persistence.EntityManager;

public class OperConductorIm implements OperConductor {

    @Override
    public Conductor login(Conductor c) {
        conexion con = new conexion();
        EntityManager em = con.conectarse();
        try {
            Long pk = new Long(c.getCedula());

            System.out.println(c.getCedula());
            Conductor cond = em.find(Conductor.class, pk);
            return cond;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
