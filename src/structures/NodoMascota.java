package structures;

import models.Mascota;

public class NodoMascota {

    public Mascota dato;
    public NodoMascota siguiente;
    public NodoMascota anterior;

    public NodoMascota(Mascota dato) {
        this.dato = dato;
    }

}