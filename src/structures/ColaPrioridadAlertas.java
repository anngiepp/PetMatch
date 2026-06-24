package structures;

public class ColaPrioridadAlertas {

    private NodoAlerta frente;

    private static class NodoAlerta {

        String alerta;
        int prioridad;
        NodoAlerta siguiente;

        NodoAlerta(String alerta, int prioridad) {
            this.alerta = alerta;
            this.prioridad = prioridad;
            this.siguiente = null;
        }
    }

    public void encolar(String alerta, int prioridad) {

        NodoAlerta nuevo = new NodoAlerta(alerta, prioridad);

        if (frente == null || prioridad > frente.prioridad) {
            nuevo.siguiente = frente;
            frente = nuevo;
            return;
        }

        NodoAlerta actual = frente;

        while (actual.siguiente != null && actual.siguiente.prioridad >= prioridad) {
            actual = actual.siguiente;
        }

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }

    public String atender() {

        if (frente == null) {
            return "No hay alertas urgentes pendientes.";
        }

        String alertaAtendida = frente.alerta;
        frente = frente.siguiente;

        return "Alerta atendida: " + alertaAtendida;
    }

    public String recorrer() {

        if (frente == null) {
            return "No hay alertas registradas.";
        }

        StringBuilder texto = new StringBuilder();
        NodoAlerta actual = frente;

        while (actual != null) {
            texto.append("Prioridad ")
                    .append(actual.prioridad)
                    .append(" - ")
                    .append(actual.alerta)
                    .append("\n");

            actual = actual.siguiente;
        }

        return texto.toString();
    }

    public int contarAlertas() {

        int contador = 0;
        NodoAlerta actual = frente;

        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }

        return contador;
    }

}