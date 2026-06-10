package structures;

import models.Mascota;

public class ListaDobleMascotas {

    private NodoMascota cabeza;
    private NodoMascota cola;

    public void insertar(Mascota mascota) {

        NodoMascota nuevo = new NodoMascota(mascota);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    public Mascota buscarPorId(int id) {

        NodoMascota actual = cabeza;

        while (actual != null) {

            if (actual.dato.getId() == id) {
                return actual.dato;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    public boolean eliminarPorId(int id) {

        NodoMascota actual = cabeza;

        while (actual != null) {

            if (actual.dato.getId() == id) {

                if (actual == cabeza) {
                    cabeza = actual.siguiente;
                }

                if (actual == cola) {
                    cola = actual.anterior;
                }

                if (actual.anterior != null) {
                    actual.anterior.siguiente = actual.siguiente;
                }

                if (actual.siguiente != null) {
                    actual.siguiente.anterior = actual.anterior;
                }

                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public String recorrer() {

        StringBuilder texto = new StringBuilder();

        NodoMascota actual = cabeza;

        if (actual == null) {
            return "No existen reportes registrados.";
        }

        while (actual != null) {

            texto.append(actual.dato.toString());
            texto.append("\n");

            actual = actual.siguiente;
        }

        return texto.toString();
    }

}