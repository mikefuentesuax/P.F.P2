package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BorrarPoblacionDialog extends JDialog {
    private JComboBox<String> poblacionesComboBox;
    private JButton btnBorrar;
    private JButton btnCancelar;
    private boolean confirmed = false;
    private String selectedPoblacion = null;

    public BorrarPoblacionDialog(JFrame parent, List<PoblacionBacterias> poblaciones) {
        super(parent, "Borrar una población de bacterias del experimento actual", true);
        setSize(400, 150);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel lblPoblaciones = new JLabel("Seleccione la población a borrar:");
        panel.add(lblPoblaciones);

        poblacionesComboBox = new JComboBox<>();
        for (PoblacionBacterias poblacion : poblaciones) {
            poblacionesComboBox.addItem(poblacion.getNombre());
        }
        panel.add(poblacionesComboBox);

        btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedPoblacion = (String) poblacionesComboBox.getSelectedItem();
                confirmed = true;
                dispose();
            }
        });
        panel.add(btnBorrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getSelectedPoblacion() {
        return selectedPoblacion;
    }
}