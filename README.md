# PETMATCH RESCUE - ENTREGA 1

## Descripción

PetMatch Rescue es una aplicación de escritorio desarrollada en Java que permite registrar y gestionar reportes de mascotas perdidas o encontradas. En esta primera entrega se implementaron tres estructuras de datos lineales dinámicas: lista doblemente enlazada, pila y cola de prioridad.

---

# Funcionalidades Implementadas

* Registro de reportes de mascotas.
* Búsqueda de reportes por ID.
* Eliminación de reportes.
* Visualización de reportes registrados.
* Gestión de alertas urgentes.
* Historial de acciones realizadas.

---

# Evidencias

## Interfaz Principal

La aplicación cuenta con una interfaz gráfica que permite acceder a los módulos de reportes, alertas urgentes e historial.

![Interfaz Principal](evidencias/interfaz-principal.png)

---

## Registro de Reporte

Se registró una nueva mascota dentro del sistema utilizando el formulario de reportes.

![Registro de Reporte](evidencias/registro-reporte.png)

---

## Lista Doblemente Enlazada

La lista doblemente enlazada se utiliza para almacenar los reportes de mascotas registrados. La siguiente captura muestra los reportes almacenados en la estructura.

![Lista de Reportes](evidencias/lista-reportes.png)

---

## Búsqueda de Reporte

Se realizó la búsqueda de una mascota mediante su identificador único (ID).

![Búsqueda de Reporte](evidencias/busqueda-reporte.png)

---

## Eliminación de Reporte

Se eliminó un reporte previamente registrado dentro del sistema.

![Eliminación de Reporte](evidencias/eliminacion-reporte.png)

---

## Cola de Prioridad

La cola de prioridad se utiliza para gestionar alertas urgentes. Las alertas con mayor prioridad son atendidas antes que las demás.

![Cola de Prioridad](evidencias/alertas-prioridad.png)

---

## Pila de Acciones

La pila se utiliza para almacenar el historial de acciones realizadas dentro del sistema.

![Historial de Acciones](evidencias/historial-acciones.png)

---

# Estructuras Implementadas

| Estructura                | Uso dentro del sistema          |
| ------------------------- | ------------------------------- |
| Lista Doblemente Enlazada | Almacenar reportes de mascotas  |
| Pila                      | Registrar historial de acciones |
| Cola de Prioridad         | Gestionar alertas urgentes      |

---

# Estructura del Proyecto

```text
src
├── models
│   └── Mascota.java
├── services
│   └── PetMatchService.java
├── structures
│   ├── NodoMascota.java
│   ├── ListaDobleMascotas.java
│   ├── PilaAcciones.java
│   └── ColaPrioridadAlertas.java
├── ui
│   └── PetMatchFrame.java
└── Main.java
```

---

# Conclusiones

1. Implementé una aplicación funcional utilizando estructuras de datos desarrolladas desde cero.

2. Integré una lista doblemente enlazada, una pila y una cola de prioridad dentro de un caso práctico relacionado con mascotas perdidas.

3. Apliqué conceptos de Programación Orientada a Objetos para organizar el proyecto de manera modular y facilitar su mantenimiento.
