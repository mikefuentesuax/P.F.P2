package org.example;

import java.util.Random;

public class Bacteria {
    private int fila;
    private int columna;
    private int comidaConsumida;
    private boolean viva;

    public Bacteria(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.comidaConsumida = 0;
        this.viva = true;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getComidaConsumida() {
        return comidaConsumida;
    }

    public void setComidaConsumida(int comidaConsumida) {
        this.comidaConsumida = comidaConsumida;
    }

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

    public void simularDia(int[][] plato) {
        Random random = new Random();


        // Simular 10 pasos para cada bacteria en el día
        for (int i = 0; i < 10; i++) {
            if (!viva) break; // Si la bacteria está muerta, salir del ciclo

            int comidaEnCelda = plato[fila][columna];


            if (comidaEnCelda >= 100) {
                plato[fila][columna] -= 20;
                comidaConsumida += 20;
                int numeroAleatorio = random.nextInt(100);

                if (numeroAleatorio < 3) {
                    viva = false;
                } else if (numeroAleatorio >= 60) {
                    moverBacteria(plato, random);
                }
            } else if (comidaEnCelda > 10) {
                plato[fila][columna] -= 10;
                comidaConsumida += 10;
                int numeroAleatorio = random.nextInt(100);

                if (numeroAleatorio < 6) {
                    viva = false;
                } else if (numeroAleatorio >= 20) {
                    moverBacteria(plato, random);
                }
            } else {
                int numeroAleatorio = random.nextInt(100);

                if (numeroAleatorio < 20) {
                    viva = false;
                } else if (numeroAleatorio >= 60) {
                    moverBacteria(plato, random);
                }
            }
        }

        if (viva) {
            if (comidaConsumida >= 150) {
                // Crear tres bacterias hijas
                for (int j = 0; j < 3; j++) {
                    // Agregar nueva bacteria en la misma celda (esto se debería manejar fuera de la clase Bacteria)
                }
            } else if (comidaConsumida >= 100) {
                // Crear dos bacterias hijas
                for (int j = 0; j < 2; j++) {
                    // Agregar nueva bacteria en la misma celda (esto se debería manejar fuera de la clase Bacteria)
                }
            } else if (comidaConsumida >= 50) {
                // Crear una bacteria hija
                // Agregar nueva bacteria en la misma celda (esto se debería manejar fuera de la clase Bacteria)
            }
        }
    }

    private void moverBacteria(int[][] plato, Random random) {
        int direccion = random.nextInt(4);
        int nuevaFila = fila;
        int nuevaColumna = columna;

        switch (direccion) {
            case 0: nuevaFila -= 1; break; // Mover arriba
            case 1: nuevaColumna += 1; break; // Mover derecha
            case 2: nuevaFila += 1; break; // Mover abajo
            case 3: nuevaColumna -= 1; break; // Mover izquierda
        }

        if (nuevaFila >= 0 && nuevaFila < plato.length && nuevaColumna >= 0 && nuevaColumna < plato[0].length) {
            fila = nuevaFila;
            columna = nuevaColumna;
        }
    }
}
