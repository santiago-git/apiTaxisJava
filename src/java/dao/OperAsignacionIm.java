/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Carro;
import conectar.conexion;
import dto.Asignacion;
import dto.Conductor;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class OperAsignacionIm implements OperAsignacion {

    @Override
    public int asignarCarroConductor(Asignacion dtoA) {
        conexion con = new conexion();
        EntityManager em = con.conectarse();

        dtoA.setOcupado(false);

        try {
            em.getTransaction().begin();
            em.persist(dtoA); //Es lo mismo que el inser into *from
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;
    }

    @Override
    public int modificar(Asignacion dtoA) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(Asignacion dtoEdtoA) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Asignacion consultar(Integer pk) {

        Asignacion a = new Asignacion();
        a.setId(pk);
        conexion con = new conexion();
        EntityManager em = con.conectarse();
        try {
            em.getTransaction().begin();
            Asignacion asig = em.find(Asignacion.class, a.getId());
            em.getTransaction().commit();
            return asig;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Asignacion> consultar() {
        conexion con = new conexion();
        EntityManager conectar = con.conectarse();
        conectar.getTransaction().begin();
        Query q = conectar.createQuery("SELECT a FROM Asignacion a"); //Consulta de tipo jpql
        List<Asignacion> ls = q.getResultList();  //me devuelve una lista de equipo 
        conectar.getTransaction().commit();
        return ls;
    }

    @Override
    public List<Asignacion> consultarDisponibles() {
        conexion con = new conexion();
        EntityManager em = con.conectarse();

        try {

            em.getTransaction().begin();
            Query q = em.createQuery("SELECT a FROM Asignacion a WHERE a.estado = true AND a.ocupado = false", Asignacion.class); //Consulta de tipo jpql
            List<Asignacion> listaA = q.getResultList();  //me devuelve una lista de Asignacion 
            em.getTransaction().commit();

            return listaA;
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Asignacion> consultarNoAsignados() {
        return null;
    }

    @Override
    public Asignacion consultarUsuarioAsignado(Long cc) {
        conexion con = new conexion();
        EntityManager em = con.conectarse();

        Conductor c = new Conductor();
        c.setCedula(cc);
        try {

            em.getTransaction().begin();
            Query q = em.createQuery("SELECT a FROM Asignacion a WHERE a.idConductor = :cc_conductor AND a.estado = true", Asignacion.class); //Consulta de tipo jpql
            q.setParameter("cc_conductor", c);

            Asignacion a = (Asignacion) q.getSingleResult();  //me devuelve una lista de equipo 

            return a;
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int cambiarEstado(Asignacion a) {
        conexion con = new conexion();
        EntityManager em = con.conectarse();

        try {
            a.setEstado(false);
            em.getTransaction().begin();
            em.merge(a); //Es lo mismo que el update
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int cambiarOcupado(Asignacion a) {
        conexion con = new conexion();
        EntityManager em = con.conectarse();

        Asignacion asig = em.find(Asignacion.class, a.getId());

        try {
            asig.setOcupado(!asig.getOcupado());
            em.getTransaction().begin();
            em.merge(asig); //Es lo mismo que el update
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}
