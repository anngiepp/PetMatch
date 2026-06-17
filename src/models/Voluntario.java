package models;

public class Voluntario extends Usuario {

    private String zonaApoyo;

    public Voluntario(int id, String nombre, String correo, String zonaApoyo) {
        super(id, nombre, correo);
        this.zonaApoyo = zonaApoyo;
    }

    @Override
    public String toString() {
        return "[VOLUNTARIO] "
                + nombre
                + " | Zona: "
                + zonaApoyo;
    }
}