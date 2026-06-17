package structures;

public class GrafoZonas {

    private NodoZona inicio;
    private NodoAvistamiento inicioAvistamiento;

    private static class NodoZona {
        String nombreZona;
        NodoConexion conexiones;
        NodoZona siguiente;

        NodoZona(String nombreZona) {
            this.nombreZona = nombreZona;
        }
    }

    private static class NodoConexion {
        NodoZona zonaDestino;
        NodoConexion siguiente;

        NodoConexion(NodoZona zonaDestino) {
            this.zonaDestino = zonaDestino;
        }
    }

    private static class NodoAvistamiento {
        String mascota;
        String ultimaZona;
        String persona;
        String comentario;
        NodoAvistamiento siguiente;

        NodoAvistamiento(String mascota, String ultimaZona, String persona, String comentario) {
            this.mascota = mascota;
            this.ultimaZona = ultimaZona;
            this.persona = persona;
            this.comentario = comentario;
        }
    }

    public void registrarAvistamiento(String mascota, String ultimaZona, String persona, String comentario) {
        String zonaAnterior = obtenerUltimaZonaDeMascota(mascota);

        agregarZona(ultimaZona);

        if (zonaAnterior != null) {
            conectarZonas(zonaAnterior, ultimaZona);
        }

        NodoAvistamiento nuevo = new NodoAvistamiento(mascota, ultimaZona, persona, comentario);

        if (inicioAvistamiento == null) {
            inicioAvistamiento = nuevo;
        } else {
            NodoAvistamiento actual = inicioAvistamiento;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }
    }

    private String obtenerUltimaZonaDeMascota(String mascota) {
        NodoAvistamiento actual = inicioAvistamiento;
        String ultimaZona = null;

        while (actual != null) {
            if (actual.mascota.equalsIgnoreCase(mascota)) {
                ultimaZona = actual.ultimaZona;
            }

            actual = actual.siguiente;
        }

        return ultimaZona;
    }

    public void conectarZonas(String zonaA, String zonaB) {
        agregarZona(zonaA);
        agregarZona(zonaB);

        NodoZona nodoA = buscarZona(zonaA);
        NodoZona nodoB = buscarZona(zonaB);

        agregarConexion(nodoA, nodoB);
        agregarConexion(nodoB, nodoA);
    }

    private void agregarZona(String nombreZona) {
        if (buscarZona(nombreZona) != null) {
            return;
        }

        NodoZona nuevaZona = new NodoZona(nombreZona);

        if (inicio == null) {
            inicio = nuevaZona;
        } else {
            NodoZona actual = inicio;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevaZona;
        }
    }

    private NodoZona buscarZona(String nombreZona) {
        NodoZona actual = inicio;

        while (actual != null) {
            if (actual.nombreZona.equalsIgnoreCase(nombreZona)) {
                return actual;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    private void agregarConexion(NodoZona origen, NodoZona destino) {
        if (origen == null || destino == null) {
            return;
        }

        if (existeConexion(origen, destino)) {
            return;
        }

        NodoConexion nuevaConexion = new NodoConexion(destino);

        if (origen.conexiones == null) {
            origen.conexiones = nuevaConexion;
        } else {
            NodoConexion actual = origen.conexiones;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevaConexion;
        }
    }

    private boolean existeConexion(NodoZona origen, NodoZona destino) {
        NodoConexion actual = origen.conexiones;

        while (actual != null) {
            if (actual.zonaDestino.nombreZona.equalsIgnoreCase(destino.nombreZona)) {
                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public String mostrarSeguimientoAvistamientos() {
        if (inicioAvistamiento == null) {
            return "No hay avistamientos registrados.";
        }

        StringBuilder texto = new StringBuilder();
        texto.append("Seguimiento de avistamientos registrados\n\n");

        NodoAvistamiento actual = inicioAvistamiento;
        int contador = 1;

        while (actual != null) {
            texto.append(contador).append(". Mascota: ").append(actual.mascota).append("\n");
            texto.append("   Ultima zona donde fue vista: ").append(actual.ultimaZona).append("\n");
            texto.append("   Reportado por: ").append(actual.persona).append("\n");
            texto.append("   Comentario: ").append(actual.comentario).append("\n\n");

            actual = actual.siguiente;
            contador++;
        }

        texto.append("Ruta probable generada por avistamientos:\n");
        texto.append(generarRutaProbable());

        return texto.toString();
    }

    private String generarRutaProbable() {
        if (inicioAvistamiento == null) {
            return "Sin ruta disponible.";
        }

        StringBuilder ruta = new StringBuilder();
        NodoAvistamiento actual = inicioAvistamiento;

        while (actual != null) {
            ruta.append(actual.ultimaZona);

            if (actual.siguiente != null) {
                ruta.append(" -> ");
            }

            actual = actual.siguiente;
        }

        return ruta.toString();
    }

    public String mostrarConexiones() {
        if (inicio == null) {
            return "No hay zonas registradas.";
        }

        StringBuilder texto = new StringBuilder();
        texto.append("Zonas relacionadas por avistamientos\n\n");

        NodoZona actual = inicio;

        while (actual != null) {
            texto.append(actual.nombreZona).append(" se relaciona con: ");

            NodoConexion conexion = actual.conexiones;

            if (conexion == null) {
                texto.append("sin zonas relacionadas");
            }

            while (conexion != null) {
                texto.append(conexion.zonaDestino.nombreZona);

                if (conexion.siguiente != null) {
                    texto.append(", ");
                }

                conexion = conexion.siguiente;
            }

            texto.append("\n");
            actual = actual.siguiente;
        }

        return texto.toString();
    }
}