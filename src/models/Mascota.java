package models;

public class Mascota {

    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String estado;
    private int popularidad;
    private int diasRefugio;

    public Mascota(int id,
                   String nombre,
                   String especie,
                   String raza,
                   int edad,
                   String estado,
                   int popularidad,
                   int diasRefugio) {

        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.estado = estado;
        this.popularidad = popularidad;
        this.diasRefugio = diasRefugio;
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

    public int getPopularidad() {
        return popularidad;
    }

    public int getDiasRefugio() {
        return diasRefugio;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + especie;
    }
}