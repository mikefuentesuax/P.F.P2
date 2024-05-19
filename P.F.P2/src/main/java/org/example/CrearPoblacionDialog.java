package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearPoblacionDialog extends JDialog {
    private JTextField nombreField;
    private JTextField fechaInicioField;
    private JTextField fechaFinField;
    private JTextField numBacteriasField;
    private JTextField temperaturaField;
    private JComboBox<String> luminosidadBox;
    private JTextField comidaInicialField;
    private JTextField comidaIncrementoField;
    private JTextField comidaDiaField;
    private JTextField comidaFinalField;
    private JComboBox<String> tipoPatronComidaBox;
    private boolean confirmed;
    private SimpleDateFormat dateFormat;

    public CrearPoblacionDialog(JFrame parentFrame) {
        super(parentFrame, "Crear Nueva Población de Bacterias", true);
        setLayout(new GridLayout(13, 2));

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        nombreField = new JTextField();
        fechaInicioField = new JTextField();
        fechaFinField = new JTextField();
        numBacteriasField = new JTextField();
        temperaturaField = new JTextField();
        luminosidadBox = new JComboBox<>(new String[]{"Alta", "Media", "Baja"});
        comidaInicialField = new JTextField();
        comidaIncrementoField = new JTextField();
        comidaDiaField = new JTextField();
        comidaFinalField = new JTextField();
        tipoPatronComidaBox = new JComboBox<>(new String[]{"Lineal Incremento/Decremento", "Constante", "Lineal Incremento", "Intermitente"});


        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Fecha de Inicio (yyyy-MM-dd):"));
        add(fechaInicioField);
        add(new JLabel("Fecha de Fin (yyyy-MM-dd):"));
        add(fechaFinField);
        add(new JLabel("Número de Bacterias Iniciales:"));
        add(numBacteriasField);
        add(new JLabel("Temperatura:"));
        add(temperaturaField);
        add(new JLabel("Luminosidad:"));
        add(luminosidadBox);
        add(new JLabel("Comida Inicial (µg):"));
        add(comidaInicialField);
        add(new JLabel("Día hasta incremento:"));
        add(comidaIncrementoField);
        add(new JLabel("Comida en día de incremento (µg):"));
        add(comidaDiaField);
        add(new JLabel("Comida Final (µg):"));
        add(comidaFinalField);
        add(new JLabel("Tipo de Patrón de Comida:"));
        add(tipoPatronComidaBox);

        JButton btnConfirm = new JButton("Confirmar");
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                setVisible(false);
            }
        });

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                setVisible(false);
            }
        });

        add(btnConfirm);
        add(btnCancel);

        pack();
        setLocationRelativeTo(parentFrame);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public PoblacionBacterias getPoblacionBacterias() {
        if (!confirmed) {
            return null;
        }
        try {
            String nombre = nombreField.getText();
            Date fechaInicio = dateFormat.parse(fechaInicioField.getText());
            Date fechaFin = dateFormat.parse(fechaFinField.getText());
            int numBacteriasIniciales = Integer.parseInt(numBacteriasField.getText());
            int temperatura = Integer.parseInt(temperaturaField.getText());
            String luminosidad = (String) luminosidadBox.getSelectedItem();
            int comidaInicial = Integer.parseInt(comidaInicialField.getText());
            int diaIncremento = Integer.parseInt(comidaIncrementoField.getText());
            int comidaDia = Integer.parseInt(comidaDiaField.getText());
            int comidaFinal = Integer.parseInt(comidaFinalField.getText());
            String tipoPatronComida = (String) tipoPatronComidaBox.getSelectedItem();


            return new PoblacionBacterias(nombre, fechaInicio, fechaFin, numBacteriasIniciales, temperatura, luminosidad, comidaInicial, diaIncremento, comidaDia, comidaFinal, tipoPatronComida);
        } catch (NumberFormatException | ParseException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los valores correctamente.");
            return null;
        }
    }
}
