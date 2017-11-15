/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class conexion {
    
    public static final String UNIDAD_PER="apptaxisPU";
    private static EntityManagerFactory fabrica;
    
    public conexion(){  //Aqui recibe la conexion
        fabrica = Persistence.createEntityManagerFactory(UNIDAD_PER);
    }
    public EntityManager conectarse(){  //Esta es como la funcion de conectarse de programa anterior
        if (fabrica != null){   
        return fabrica.createEntityManager();   //esta devolviendo una instancia de tipo entity manager
        }
        return null;
}
}
