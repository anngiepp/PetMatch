package sorting;

import models.Mascota;

public class Ordenamientos {

    public static void quickSortPorUrgencia(Mascota[] arreglo, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particionar(arreglo, inicio, fin);

            quickSortPorUrgencia(arreglo, inicio, indicePivote - 1);
            quickSortPorUrgencia(arreglo, indicePivote + 1, fin);
        }
    }

    private static int particionar(Mascota[] arreglo, int inicio, int fin) {
        int pivote = arreglo[fin].getUrgencia();
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (arreglo[j].getUrgencia() >= pivote) {
                i++;
                intercambiar(arreglo, i, j);
            }
        }

        intercambiar(arreglo, i + 1, fin);
        return i + 1;
    }

    public static void insertionSortPorDias(Mascota[] arreglo) {
        for (int i = 1; i < arreglo.length; i++) {
            Mascota actual = arreglo[i];
            int j = i - 1;

            while (j >= 0 && arreglo[j].getDiasDesaparecida() > actual.getDiasDesaparecida()) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }

            arreglo[j + 1] = actual;
        }
    }

    private static void intercambiar(Mascota[] arreglo, int i, int j) {
        Mascota temporal = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temporal;
    }
}