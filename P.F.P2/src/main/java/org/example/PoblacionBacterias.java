package org.example;

import java.io.Serializable;
import java.util.Date;

public class PoblacionBacterias implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private int numBacteriasIniciales;
    private int temperatura;
    private String condicionesLuminosidad;
    private int dosisComidaInicial;
    private int diaIncrementoComida;
    private int comidaDiaIncremento;
    private int comidaFinalDia;
    private String tipoPatronComida;

    public PoblacionBacterias(String nombre, Date fechaInicio, Date fechaFin, int numBacteriasIniciales,
                              int temperatura, String condicionesLuminosidad, int dosisComidaInicial,
                              int diaIncrementoComida, int comidaDiaIncremento, int comidaFinalDia,
                              String tipoPatronComida) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numBacteriasIniciales = numBacteriasIniciales;
        this.temperatura = temperatura;
        this.condicionesLuminosidad = condicionesLuminosidad;
        this.dosisComidaInicial = dosisComidaInicial;
        this.diaIncrementoComida = diaIncrementoComida;
        this.comidaDiaIncremento = comidaDiaIncremento;
        this.comidaFinalDia = comidaFinalDia;
        this.tipoPatronComida = tipoPatronComida;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumBacteriasIniciales() {
        return numBacteriasIniciales;
    }

    public void setNumBacteriasIniciales(int numBacteriasIniciales) {
        this.numBacteriasIniciales = numBacteriasIniciales;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getCondicionesLuminosidad() {
        return condicionesLuminosidad;
    }

    public void setCondicionesLuminosidad(String condicionesLuminosidad) {
        this.condicionesLuminosidad = condicionesLuminosidad;
    }

    public int getDosisComidaInicial() {
        return dosisComidaInicial;
    }

    public void setDosisComidaInicial(int dosisComidaInicial) {
        this.dosisComidaInicial = dosisComidaInicial;
    }

    public int getDiaIncrementoComida() {
        return diaIncrementoComida;
    }

    public void setDiaIncrementoComida(int diaIncrementoComida) {
        this.diaIncrementoComida = diaIncrementoComida;
    }

    public int getComidaDiaIncremento() {
        return comidaDiaIncremento;
    }

    public void setComidaDiaIncremento(int comidaDiaIncremento) {
        this.comidaDiaIncremento = comidaDiaIncremento;
    }

    public int getComidaFinalDia() {
        return comidaFinalDia;
    }

    public void setComidaFinalDia(int comidaFinalDia) {
        this.comidaFinalDia = comidaFinalDia;
    }

    // Métodos para cálculos relacionados con la dosis de comida
    public int getDuracion() {
        return (int) ((fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24)) + 1;
    }

    public int calcularComidaDia(int dia) {
        if (dia < 1 || dia > 30) {
            throw new IllegalArgumentException("Día inválido");
        }

        int cantidadComida = 0;

        switch (tipoPatronComida) {
            case "Lineal Incremento/Decremento":
                if (dia <= diaIncrementoComida) {
                    cantidadComida = dosisComidaInicial + (dia - 1) * (comidaDiaIncremento - dosisComidaInicial) / diaIncrementoComida;
                } else {
                    cantidadComida = comidaDiaIncremento + (dia - diaIncrementoComida) * (comidaFinalDia - comidaDiaIncremento) / (30 - diaIncrementoComida);
                }
                break;

            case "Constante":
                cantidadComida = dosisComidaInicial;
                break;

            case "Lineal Incremento":
                cantidadComida = dosisComidaInicial + (dia - 1) * (comidaFinalDia - dosisComidaInicial) / 29;
                break;

            case "Intermitente":
                cantidadComida = (dia % 2 == 1) ? dosisComidaInicial : 0;
                break;
        }

        return Math.min(cantidadComida, 300000);
    }
    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", Fecha de Inicio: " + fechaInicio +
                ", Fecha de Fin: " + fechaFin +
                ", Número de Bacterias Iniciales: " + numBacteriasIniciales +
                ", Temperatura: " + temperatura +
                ", Condiciones de Luminosidad: " + condicionesLuminosidad +
                ", Dosis de Comida Inicial: " + dosisComidaInicial +
                ", Día de Incremento de Comida: " + diaIncrementoComida +
                ", Comida por Día de Incremento: " + comidaDiaIncremento +
                ", Comida Final del Día: : " + comidaFinalDia;
                ", Tipo de Patrón de Comida: " + tipoPatronComida;
    }
}