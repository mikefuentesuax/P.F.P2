package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;


public class InterfazUsuario {
    private JFrame frame;
    private Experimento experimento;

    public InterfazUsuario() {
        experimento = new Experimento();
        frame = new JFrame("Gestión de Experimentos de Bacterias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre los componentes
        gbc.anchor = GridBagConstraints.WEST;

        // Crear los botones con sus respectivos ActionListener
        JButton btnAbrirArchivo = new JButton("1. Abrir archivo que contenga un experimento");
        btnAbrirArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        experimento = Experimento.abrir(fileChooser.getSelectedFile().getPath());
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(frame, "Error al abrir el archivo: " + ex.getMessage());
                    }
                }
            }
        });
        frame.add(btnAbrirArchivo, gbc);

        gbc.gridy++;
        JButton btnNuevoExperimento = new JButton("2. Crear un nuevo experimento");
        btnNuevoExperimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                experimento = new Experimento();
                JOptionPane.showMessageDialog(frame, "Nuevo experimento creado.");
            }
        });
        frame.add(btnNuevoExperimento, gbc);

        gbc.gridy++;
        JButton btnCrearPoblacion = new JButton("3. Crear una población de bacterias y añadirla al experimento actual");
        btnCrearPoblacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrearPoblacionDialog dialog = new CrearPoblacionDialog(frame);
                dialog.setVisible(true);

                if (dialog.isConfirmed()) {
                    PoblacionBacterias nuevaPoblacion = dialog.getPoblacionBacterias();
                    if (nuevaPoblacion != null) {
                        experimento.agregarPoblacion(nuevaPoblacion);
                        JOptionPane.showMessageDialog(frame, "Nueva población de bacterias añadida.");
                    }
                }
            }
        });
        frame.add(btnCrearPoblacion, gbc);

        gbc.gridy++;
        JButton btnVisualizarNombres = new JButton("4. Visualizar los nombres de todas las poblaciones de bacterias del experimento actual");
        btnVisualizarNombres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder nombres = new StringBuilder("Poblaciones de bacterias:\n");
                List<PoblacionBacterias> poblaciones = experimento.obtenerPoblaciones();
                for (PoblacionBacterias poblacion : poblaciones) {
                    nombres.append(poblacion.getNombre()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, nombres.toString());
            }
        });
        frame.add(btnVisualizarNombres, gbc);

        gbc.gridy++;
        JButton btnBorrarPoblacion = new JButton("5. Borrar una población de bacterias del experimento actual");
        btnBorrarPoblacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BorrarPoblacionDialog dialog = new BorrarPoblacionDialog(frame, experimento.obtenerPoblaciones());
                dialog.setVisible(true);

                if (dialog.isConfirmed()) {
                    String poblacionSeleccionada = dialog.getSelectedPoblacion();
                    if (poblacionSeleccionada != null) {
                        for (PoblacionBacterias poblacion : experimento.obtenerPoblaciones()) {
                            if (poblacion.getNombre().equals(poblacionSeleccionada)) {
                                experimento.eliminarPoblacion(poblacion);
                                JOptionPane.showMessageDialog(frame, "Población de bacterias borrada.");
                                break;
                            }
                        }
                    }
                }
            }
        });
        frame.add(btnBorrarPoblacion, gbc);

        gbc.gridy++;
        JButton btnVerDetalles = new JButton("6. Ver Detalles de Población");
        btnVerDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Pedir al usuario el nombre de la población de bacterias
                String nombrePoblacion = JOptionPane.showInputDialog(frame, "Ingrese el nombre de la población de bacterias:");
                if (nombrePoblacion != null && !nombrePoblacion.isEmpty()) {
                    mostrarDetallesPoblacion(nombrePoblacion);
                } else {
                    JOptionPane.showMessageDialog(frame, "Debe ingresar un nombre de población válido.");
                }
            }
        });
        frame.add(btnVerDetalles, gbc);

        gbc.gridy++;
        JButton btnGuardar = new JButton("7. Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        experimento.guardarComo(fileChooser.getSelectedFile().getPath());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error al guardar el archivo: " + ex.getMessage());
                    }
                }
            }
        });
        frame.add(btnGuardar, gbc);

        gbc.gridy++;
        JButton btnGuardarComo = new JButton("8. Guardar como");
        btnGuardarComo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        experimento.guardarComo(fileChooser.getSelectedFile().getPath());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error al guardar el archivo: " + ex.getMessage());
                    }
                }
            }
        });
        frame.add(btnGuardarComo, gbc);

        frame.pack();
        frame.setVisible(true);
    }

    public void iniciar() {
        SwingUtilities.invokeLater(() -> new InterfazUsuario());
    }

    public static void main(String[] args) {
        new InterfazUsuario().iniciar();
    }

    private void mostrarDetallesPoblacion(String nombrePoblacion) {
        PoblacionBacterias poblacion = experimento.obtenerPoblacionPorNombre(nombrePoblacion);
        if (poblacion != null) {
            // Aquí puedes mostrar los detalles de la población, por ejemplo:
            JOptionPane.showMessageDialog(frame, "Detalles de la población:\n" + poblacion.toString());
        } else {
            JOptionPane.showMessageDialog(frame, "No se encontró la población con el nombre especificado.");
        }
    }
}