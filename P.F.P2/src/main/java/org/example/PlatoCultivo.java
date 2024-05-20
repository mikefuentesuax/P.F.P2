package org.example;

import java.util.ArrayList;
import java.util.List;

public class PlatoCultivo {
    private static final int TAMANO_PLATO = 20;
    private static final int COMIDA_INICIAL = 500; // Microgramos de comida inicial en cada celda

    private int[][] plato;
    private List<Bacteria> bacterias;

    public PlatoCultivo() {
        plato = new int[TAMANO_PLATO][TAMANO_PLATO];
        // Inicializar todas las celdas del plato con comida inicial
        for (int i = 0; i < TAMANO_PLATO; i++) {
            for (int j = 0; j < TAMANO_PLATO; j++) {
                plato[i][j] = COMIDA_INICIAL;
            }
        }
        bacterias = new ArrayList<>();
        inicializarBacterias();
    }

    private void inicializarBacterias() {
        for (int i = 8; i < 12; i++) {
            for (int j = 8; j < 12; j++) {
                bacterias.add(new Bacteria(i, j));
            }
        }
}

    public void agregarBacteria(Bacteria bacteria) {
        bacterias.add(bacteria);
    }

    public void simularDia() {
        List<Bacteria> nuevasBacterias = new ArrayList<>();
        List<Bacteria> bacteriasMuertas = new ArrayList<>();

        for (Bacteria bacteria : bacterias) {
            bacteria.simularDia(plato);

            if (!bacteria.isViva()) {
                bacteriasMuertas.add(bacteria);
            } else {
                int comidaConsumida = bacteria.getComidaConsumida();

                if (comidaConsumida >= 150) {
                    for (int i = 0; i < 3; i++) {
                        nuevasBacterias.add(new Bacteria(bacteria.getFila(), bacteria.getColumna()));
                    }
                } else if (comidaConsumida >= 100) {
                    for (int i = 0; i < 2; i++) {
                        nuevasBacterias.add(new Bacteria(bacteria.getFila(), bacteria.getColumna()));
                    }
                } else if (comidaConsumida >= 50) {
                    nuevasBacterias.add(new Bacteria(bacteria.getFila(), bacteria.getColumna()));
                }
            }
        }

        bacterias.removeAll(bacteriasMuertas);

        bacterias.addAll(nuevasBacterias);
    }

    public void simular(int dias) {
        for (int i = 0; i < dias; i++) {
            simularDia();
        }
    }
}
