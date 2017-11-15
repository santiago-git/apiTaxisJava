package dao;

import dto.Reserva;
import java.util.List;

public interface OperReserva {
    public int crearCarrera (Reserva r);
    public List<Reserva> listarReservas ();
    public Reserva consultar(Integer pk);
    
}
