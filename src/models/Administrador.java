package models;

public class Administrador extends Usuario {

    private String nivelAcceso;

    public Administrador(int id, String nombre, String correo, String nivelAcceso) {
        super(id, nombre, correo);
        this.nivelAcceso = nivelAcceso;
    }

    @Override
    public String toString() {
        return "[ADMIN] "
                + nombre
                + " | Nivel: "
                + nivelAcceso;
    }
}