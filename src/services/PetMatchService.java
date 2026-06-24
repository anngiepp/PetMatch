package services;

import models.Mascota;
import sorting.Ordenamientos;
import structures.ArbolMascotas;
import structures.ColaPrioridadAlertas;
import structures.GrafoZonas;
import structures.ListaDobleMascotas;
import structures.PilaAcciones;
import models.Administrador;
import models.Reportante;
import models.Usuario;
import models.Voluntario;

public class PetMatchService {

    private ListaDobleMascotas listaMascotas;
    private PilaAcciones pilaAcciones;
    private ColaPrioridadAlertas colaAlertas;
    private ArbolMascotas arbolMascotas;
    private GrafoZonas grafoZonas;

    public PetMatchService() {
        listaMascotas = new ListaDobleMascotas();
        pilaAcciones = new PilaAcciones();
        colaAlertas = new ColaPrioridadAlertas();
        arbolMascotas = new ArbolMascotas();
        grafoZonas = new GrafoZonas();

        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        registrarMascota(new Mascota(1, "Luna", "Perro", "Mestiza", "3 años",
                "Perdida", "Los Olivos", 10, 2));

        registrarMascota(new Mascota(2, "Michi", "Gato", "Criollo", "2 años",
                "Encontrada", "San Miguel", 7, 1));

        registrarMascota(new Mascota(3, "Rocky", "Perro", "Labrador", "5 años",
                "Perdida", "Miraflores", 9, 5));

        registrarAvistamiento("Luna", "Los Olivos", "Carlos", "La vi cerca del parque.");
        registrarAvistamiento("Luna", "Independencia", "Maria", "Parecia caminar hacia la avenida.");
        registrarAvistamiento("Luna", "Comas", "Ana", "La vi cerca de una tienda.");
    }

    public void registrarMascota(Mascota mascota) {
        listaMascotas.insertar(mascota);
        arbolMascotas.insertar(mascota);
        pilaAcciones.push("Se registró el reporte de " + mascota.getNombre());
    }

    public boolean eliminarReporte(int id) {
        Mascota mascota = listaMascotas.buscarPorId(id);

        if (mascota != null) {
            listaMascotas.eliminarPorId(id);
            pilaAcciones.push("Se eliminó el reporte de " + mascota.getNombre());
            return true;
        }

        return false;
    }

    public Mascota buscarMascota(int id) {
        return listaMascotas.buscarPorId(id);
    }

    public Mascota buscarMascotaEnArbol(int id) {
        return arbolMascotas.buscar(id);
    }

    public String mostrarArbolInOrder() {
        return arbolMascotas.mostrarInOrder();
    }

    public String mostrarArbolPreOrder() {
        return arbolMascotas.mostrarPreOrder();
    }

    public String mostrarArbolPostOrder() {
        return arbolMascotas.mostrarPostOrder();
    }

    public String mostrarReportes() {
        return listaMascotas.recorrer();
    }

    public void registrarAlerta(String zona, String descripcion, int prioridad) {
        String alerta = descripcion + " | Zona: " + zona;
        colaAlertas.encolar(alerta, prioridad);
        pilaAcciones.push("Se registró una alerta en " + zona);
    }

    public String atenderAlerta() {
        String resultado = colaAlertas.atender();
        pilaAcciones.push(resultado);
        return resultado;
    }

    public String mostrarAlertas() {
        return colaAlertas.recorrer();
    }

    public String mostrarHistorial() {
        return pilaAcciones.recorrer();
    }

    public String verUltimaAccion() {
        return pilaAcciones.peek();
    }

    public String deshacerUltimaAccion() {
        return pilaAcciones.pop();
    }

    public String ordenarPorUrgencia() {
        Mascota[] arreglo = listaMascotas.convertirAArreglo();

        if (arreglo.length == 0) {
            return "No hay reportes para ordenar.";
        }

        Ordenamientos.quickSortPorUrgencia(arreglo, 0, arreglo.length - 1);

        return convertirArregloATexto(arreglo);
    }

    public String ordenarPorDiasDesaparecida() {
        Mascota[] arreglo = listaMascotas.convertirAArreglo();

        if (arreglo.length == 0) {
            return "No hay reportes para ordenar.";
        }

        Ordenamientos.insertionSortPorDias(arreglo);

        return convertirArregloATexto(arreglo);
    }

    private String convertirArregloATexto(Mascota[] arreglo) {
        StringBuilder texto = new StringBuilder();

        for (Mascota mascota : arreglo) {
            texto.append(mascota.toString()).append("\n");
        }

        return texto.toString();
    }

    public void conectarZonas(String zonaA, String zonaB) {
        grafoZonas.conectarZonas(zonaA, zonaB);
        pilaAcciones.push("Se conectaron las zonas " + zonaA + " y " + zonaB);
    }

    public String mostrarMapaZonas() {
        return grafoZonas.mostrarConexiones();
    }

    public void registrarAvistamiento(String mascota, String ultimaZona, String persona, String comentario) {
        grafoZonas.registrarAvistamiento(mascota, ultimaZona, persona, comentario);
        pilaAcciones.push("Se registró un avistamiento de " + mascota + " en " + ultimaZona);
    }

    public String mostrarSeguimientoAvistamientos() {
        return grafoZonas.mostrarSeguimientoAvistamientos();
    }

    public String mostrarUsuariosSistema() {
        Usuario[] usuarios = new Usuario[3];

        usuarios[0] = new Reportante(1, "Angie", "angie@correo.com", 5);
        usuarios[1] = new Voluntario(2, "Maria", "maria@correo.com", "Los Olivos");
        usuarios[2] = new Administrador(3, "Carlos", "carlos@correo.com", "Total");

        StringBuilder texto = new StringBuilder();
        texto.append("Usuarios del sistema\n\n");

        for (Usuario usuario : usuarios) {
            texto.append(usuario.toString()).append("\n");
        }

        return texto.toString();
    }

    public String mostrarDashboard() {

        StringBuilder texto = new StringBuilder();

        texto.append("RESUMEN GENERAL DEL SISTEMA\n\n");

        texto.append("Total de reportes: ")
                .append(listaMascotas.contar())
                .append("\n");

        texto.append("Mascotas perdidas: ")
                .append(listaMascotas.contarPerdidas())
                .append("\n");

        texto.append("Mascotas encontradas: ")
                .append(listaMascotas.contarEncontradas())
                .append("\n");

        texto.append("Alertas activas: ")
                .append(colaAlertas.contarAlertas())
                .append("\n");

        texto.append("Usuarios registrados: 3\n");

        return texto.toString();
    }
}