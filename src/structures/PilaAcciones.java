package structures;

public class PilaAcciones {

    private NodoAccion cima;

    private static class NodoAccion {

        String accion;
        NodoAccion siguiente;

        NodoAccion(String accion) {
            this.accion = accion;
            this.siguiente = null;
        }
    }

    public void push(String accion) {

        NodoAccion nuevo = new NodoAccion(accion);

        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public String pop() {

        if (cima == null) {
            return "No hay acciones para deshacer.";
        }

        String accionEliminada = cima.accion;
        cima = cima.siguiente;

        return "Acción deshecha: " + accionEliminada;
    }

    public String peek() {

        if (cima == null) {
            return "No hay acciones registradas.";
        }

        return cima.accion;
    }

    public String recorrer() {

        if (cima == null) {
            return "No hay acciones registradas.";
        }

        StringBuilder texto = new StringBuilder();
        NodoAccion actual = cima;

        while (actual != null) {
            texto.append(actual.accion).append("\n");
            actual = actual.siguiente;
        }

        return texto.toString();
    }
}