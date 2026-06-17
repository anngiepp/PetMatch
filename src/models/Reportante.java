package models;

public class Reportante extends Usuario {

    private int reportesRealizados;

    public Reportante(int id, String nombre, String correo, int reportesRealizados) {
        super(id, nombre, correo);
        this.reportesRealizados = reportesRealizados;
    }

    @Override
    public String toString() {
        return "[REPORTANTE] "
                + nombre
                + " | Reportes: "
                + reportesRealizados;
    }
}