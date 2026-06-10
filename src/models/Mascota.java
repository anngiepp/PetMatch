package models;

public class Mascota {

    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String estado;
    private String zona;
    private int urgencia;
    private int diasDesaparecida;

    public Mascota(int id,
                   String nombre,
                   String especie,
                   String raza,
                   int edad,
                   String estado,
                   String zona,
                   int urgencia,
                   int diasDesaparecida) {

        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.estado = estado;
        this.zona = zona;
        this.urgencia = urgencia;
        this.diasDesaparecida = diasDesaparecida;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public String getEstado() {
        return estado;
    }

    public String getZona() {
        return zona;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public int getDiasDesaparecida() {
        return diasDesaparecida;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    public void setDiasDesaparecida(int diasDesaparecida) {
        this.diasDesaparecida = diasDesaparecida;
    }

    @Override
    public String toString() {
        return id + " | " + nombre +
                " | " + especie +
                " | " + raza +
                " | Edad: " + edad +
                " | Estado: " + estado +
                " | Zona: " + zona +
                " | Urgencia: " + urgencia +
                " | Días desaparecida: " + diasDesaparecida;
    }
}