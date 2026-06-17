package structures;

import models.Mascota;

public class ArbolMascotas {

    private class NodoArbol {

        Mascota mascota;
        NodoArbol izquierda;
        NodoArbol derecha;

        NodoArbol(Mascota mascota) {
            this.mascota = mascota;
            this.izquierda = null;
            this.derecha = null;
        }
    }

    private NodoArbol raiz;

    public ArbolMascotas() {
        this.raiz = null;
    }

    public void insertar(Mascota mascota) {
        raiz = insertarRecursivo(raiz, mascota);
    }

    private NodoArbol insertarRecursivo(NodoArbol actual, Mascota mascota) {

        if (actual == null) {
            return new NodoArbol(mascota);
        }

        if (mascota.getId() < actual.mascota.getId()) {
            actual.izquierda = insertarRecursivo(actual.izquierda, mascota);
        } else if (mascota.getId() > actual.mascota.getId()) {
            actual.derecha = insertarRecursivo(actual.derecha, mascota);
        }

        return actual;
    }

    public Mascota buscar(int id) {
        NodoArbol resultado = buscarRecursivo(raiz, id);

        if (resultado == null) {
            return null;
        }

        return resultado.mascota;
    }

    private NodoArbol buscarRecursivo(NodoArbol actual, int id) {

        if (actual == null) {
            return null;
        }

        if (actual.mascota.getId() == id) {
            return actual;
        }

        if (id < actual.mascota.getId()) {
            return buscarRecursivo(actual.izquierda, id);
        }

        return buscarRecursivo(actual.derecha, id);
    }

    public String mostrarInOrder() {

        if (raiz == null) {
            return "El árbol no tiene reportes registrados.";
        }

        StringBuilder resultado = new StringBuilder();
        inOrder(raiz, resultado);
        return resultado.toString();
    }

    private void inOrder(NodoArbol actual, StringBuilder resultado) {

        if (actual != null) {
            inOrder(actual.izquierda, resultado);

            resultado.append(actual.mascota.toString())
                    .append("\n");

            inOrder(actual.derecha, resultado);
        }
    }

    public String mostrarPreOrder() {

        if (raiz == null) {
            return "El árbol no tiene reportes registrados.";
        }

        StringBuilder resultado = new StringBuilder();
        preOrder(raiz, resultado);
        return resultado.toString();
    }

    private void preOrder(NodoArbol actual, StringBuilder resultado) {

        if (actual != null) {
            resultado.append(actual.mascota.toString())
                    .append("\n");

            preOrder(actual.izquierda, resultado);
            preOrder(actual.derecha, resultado);
        }
    }

    public String mostrarPostOrder() {

        if (raiz == null) {
            return "El árbol no tiene reportes registrados.";
        }

        StringBuilder resultado = new StringBuilder();
        postOrder(raiz, resultado);
        return resultado.toString();
    }

    private void postOrder(NodoArbol actual, StringBuilder resultado) {

        if (actual != null) {
            postOrder(actual.izquierda, resultado);
            postOrder(actual.derecha, resultado);

            resultado.append(actual.mascota.toString())
                    .append("\n");
        }
    }
}