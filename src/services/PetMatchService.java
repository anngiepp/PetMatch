package services;

import models.Mascota;
import structures.ArbolMascotas;
import structures.ColaPrioridadAlertas;
import structures.ListaDobleMascotas;
import structures.PilaAcciones;

public class PetMatchService {

    private ListaDobleMascotas listaMascotas;
    private PilaAcciones pilaAcciones;
    private ColaPrioridadAlertas colaAlertas;
    private ArbolMascotas arbolMascotas;

    public PetMatchService() {
        listaMascotas = new ListaDobleMascotas();
        pilaAcciones = new PilaAcciones();
        colaAlertas = new ColaPrioridadAlertas();
        arbolMascotas = new ArbolMascotas();

        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        registrarMascota(new Mascota(1, "Luna", "Perro", "Mestiza", "3 años",
                "Perdida", "Los Olivos", 10, 2));

        registrarMascota(new Mascota(2, "Michi", "Gato", "Criollo", "2 años",
                "Encontrada", "San Miguel", 7, 1));

        registrarMascota(new Mascota(3, "Rocky", "Perro", "Labrador", "5 años",
                "Perdida", "Miraflores", 9, 5));
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
}