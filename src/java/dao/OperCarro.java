
package dao;

import dto.Carro;
import java.util.List;

public interface OperCarro {
    public int insertar (Carro dtoE);
    public int modificar (Carro dtoE);
    public int eliminar (String placa);
    public Carro consultar(long pk);
    public List<Carro> consultar();
    public List<Carro> consultarNoAsignados();
    public int cambiarEstado (Carro dtoE);
}
