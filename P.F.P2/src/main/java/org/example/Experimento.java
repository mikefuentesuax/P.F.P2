package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;



public class Experimento implements Serializable {
    private static final long serialVersionUID = 1L; // Añadir un serialVersionUID para la serialización
    private Map<String, PoblacionBacterias> poblaciones;

    public Experimento() {
        poblaciones = new HashMap<>();
    }

    // Método para agregar una población
    public void agregarPoblacion(PoblacionBacterias poblacion) {
        poblaciones.put(poblacion.getNombre(), poblacion);
    }

    // Método para eliminar una población
    public void eliminarPoblacion(PoblacionBacterias poblacion) {
        poblaciones.remove(poblacion.getNombre());
    }

    // Método para obtener todas las poblaciones
    public List<PoblacionBacterias> obtenerPoblaciones() {
        return new ArrayList<>(poblaciones.values());
    }
    public PoblacionBacterias obtenerPoblacionPorNombre(String nombre) {
        for (PoblacionBacterias poblacion : poblaciones.values()) {
            if (poblacion.getNombre().equals(nombre)) {
                return poblacion;
            }
        }
        return null; // Devolver null si no se encuentra ninguna población con ese nombre
    }


    // Método estático para abrir un archivo y devolver un objeto Experimento
    public static Experimento abrir(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Experimento) ois.readObject();
        }
    }

    // Método para guardar el experimento en un archivo
    public void guardarComo(String rutaArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(this);
        }
    }
}