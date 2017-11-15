package dao;

import dto.Asignacion;
import java.util.List;

public interface OperAsignacion {
    public int asignarCarroConductor (Asignacion dtoA);
    public int modificar (Asignacion dtoA);
    public int eliminar (Asignacion dtoEdtoA);
    public Asignacion consultar(Integer pk);
    public List<Asignacion> consultar();
    public List<Asignacion> consultarNoAsignados();
    public List<Asignacion> consultarDisponibles();
    public Asignacion consultarUsuarioAsignado(Long cc);
    public int cambiarEstado(Asignacion a);
    public int cambiarOcupado(Asignacion a);
}
