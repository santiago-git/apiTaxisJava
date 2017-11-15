package dao;

import conectar.conexion;
import dto.Carro;
import dto.Reserva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class OperReservaIm implements OperReserva {

    @Override
    public int crearCarrera(Reserva r) {
        conexion con = new conexion();
        EntityManager em = con.conectarse();
        
        

        try {
            em.getTransaction().begin();
            em.persist(r); //Es lo mismo que el inser into *from
            em.getTransaction().commit();
            return 1;
        } catch (ConstraintViolationException e) {
            // Aqui tira los errores de constraint
            for (ConstraintViolation actual : e.getConstraintViolations()) {
                System.out.println(actual.toString());
            }
        }
        return 0;
    }

    @Override
    public List<Reserva> listarReservas() {
        conexion con = new conexion();
        EntityManager conectar = con.conectarse();

        conectar.getTransaction().begin();
        try {
            Query q = conectar.createQuery("SELECT r FROM Reserva r", Reserva.class); //Consulta de tipo jpql
            List<Reserva> lr = q.getResultList();  //me devuelve una lista de equipo 
            conectar.getTransaction().commit();
            return lr;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public Reserva consultar(Integer pk) {
        conexion con = new conexion();
        EntityManager em = con.conectarse();
        
        try {
            em.getTransaction().begin();
            Reserva r = em.find(Reserva.class, pk);
            em.getTransaction().commit();
            return r;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
