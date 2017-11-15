package dao;

import conectar.conexion;
import dto.Carro;
import dto.Cliente;
import javax.persistence.EntityManager;

public class OperClienteIm implements OperCliente {

    @Override
    public Cliente validarCliente(Cliente dtoC) {
        conexion con = new conexion();
        EntityManager em = con.conectarse();

        try {
            em.getTransaction().begin();
            Cliente c = em.find(Cliente.class, dtoC.getCedula());   //le asigno a e el paramettro
            em.getTransaction().commit();
            return c;
        } catch (Exception e) {
        }

        return null;
    }

}
