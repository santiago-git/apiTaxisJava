package dao;

import dto.Carro;
import conectar.conexion;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.Query;

public class OperCarroIm implements OperCarro {

    @Override
    public int insertar(Carro dtoE) {
        conexion con = new conexion();
        EntityManager conectar = con.conectarse();
        conectar.getTransaction().begin();
        
        dtoE.setAsignado(false);
        
        conectar.persist(dtoE); //Es lo mismo que el inser into *from
        conectar.getTransaction().commit();
        return 1;
    }

    @Override
    public int modificar(Carro dtoE) {
        conexion con = new conexion();
        EntityManager conectar = con.conectarse();
        conectar.getTransaction().begin();
        conectar.merge(dtoE); //Es lo mismo que el update
        conectar.getTransaction().commit();
        return 1;
    }

    @Override
    public int eliminar(String placa) {
        Carro c = new Carro();
        c.setPlaca(placa);

        conexion con = new conexion();
        EntityManager conectar = con.conectarse();
        conectar.getTransaction().begin();
        conectar.remove(c); //Es lo mismo que el delete
        conectar.getTransaction().commit();
        return 1;
    }

    @Override
    public Carro consultar(long pk) {
        conexion con = new conexion();
        EntityManager conectar = con.conectarse();
        //Query q = conectar.createQuery("SELECT e FROM Carro e WHERE e.equCodigo = :equCodigo");
        Integer inpk = new Integer("" + pk);
        Carro e = conectar.find(Carro.class, inpk);   //le asigno a e el paramettro
        return e;
    }

    @Override
    public List<Carro> consultar() {
        conexion con = new conexion();
        EntityManager conectar = con.conectarse();
        conectar.getTransaction().begin();
        Query q = conectar.createQuery("SELECT e FROM Carro e", Carro.class); //Consulta de tipo jpql
        List<Carro> ls = q.getResultList();  //me devuelve una lista de equipo 
        conectar.getTransaction().commit();
        return ls;
    }

    @Override
    public List<Carro> consultarNoAsignados() {
        conexion con = new conexion();
        EntityManager em = con.conectarse();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT c FROM Carro c WHERE c.asignado != :estado"); //Consulta de tipo jpql
            q.setParameter("estado", true);
            List<Carro> lista = q.getResultList();  //devuelve una lista de equipo
            em.getTransaction().commit();
            return lista;

            /*for(Carro c: lista){
                System.out.println(c.getPlaca());
            }
            
            em.getTransaction().commit();*/
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public int cambiarEstado(Carro dtoC) {

        conexion con = new conexion();
        EntityManager em = con.conectarse();

        try {

            Carro carro = em.find(Carro.class, dtoC.getPlaca());
            
            em.getTransaction().begin();
            carro.setAsignado(!carro.getAsignado());
            em.getTransaction().commit();

            /*em.getTransaction().begin();
            em.merge(dtoC); //update from...
            em.getTransaction().commit();*/
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}
