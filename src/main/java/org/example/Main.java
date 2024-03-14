package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Solicitar inicio de sesión
        Usuario usuario = autenticarUsuario(biblioteca);

        if (usuario != null) {
            while (true) {
                String[] opciones = {"Ver libros disponibles", "Ver libros alquilados", "Alquilar libro", "Devolver libro", "Cerrar sesión" ,"Salir"};

                int indiceOpciones = JOptionPane.showOptionDialog(null, "Hola " + usuario.getNombre() + ". Bienvenidx a la biblioteca, ¿qué necesita?:", "La Biblioteca de AdrianoLandia", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                String opcionElegida = opciones[indiceOpciones];

                switch (opcionElegida) {

                    case "Ver libros disponibles":
                        biblioteca.verLibrosDisponibles(usuario);
                        break;

                    case "Ver libros alquilados":
                        biblioteca.verLibrosAlquilados(usuario);
                        break;

                    case "Alquilar libro":
                        biblioteca.alquilarLibro(usuario);
                        break;

                    case "Devolver libro":
                        biblioteca.devolverLibro(usuario);
                        break;

                    case "Cerrar sesión":
                        usuario = null; // Limpiar usuario autenticado
                        JOptionPane.showMessageDialog(null, "Has cerrado sesión.");

                        // Confirmar si el usuario desea iniciar sesión nuevamente
                        int confirmarInicioSesion = JOptionPane.showConfirmDialog(null, "¿Desea realizar otro inicio de sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (confirmarInicioSesion == JOptionPane.YES_OPTION) {
                            // Solicitar número de teléfono y contraseña
                            String telefonoInicioSesion = JOptionPane.showInputDialog("Ingrese su número de teléfono:");
                            String contrasenaInicioSesion = JOptionPane.showInputDialog("Ingrese su contraseña:");

                            // Verificar la autenticación
                            usuario = biblioteca.autenticarUsuario(telefonoInicioSesion, contrasenaInicioSesion);
                            if (usuario != null) {
                                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Número de teléfono o contraseña incorrectos. Por favor, inténtalo de nuevo.");
                            }
                        }
                        break;

                    case "Salir":
                        JOptionPane.showMessageDialog(null, "Has salido de la biblioteca. Bye-bye.");
                        System.exit(0);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Inicio de sesión fallido. Saliendo del programa.");
            System.exit(0);
        }
    }

    private static Usuario autenticarUsuario(Biblioteca biblioteca) {
        String telefono = JOptionPane.showInputDialog("Ingrese su número de teléfono para acceder a su usuario");
        String contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");

        for (Usuario usuario : biblioteca.listaUsuarios) {
            if (telefono.equals(usuario.getTelefono()) && contrasena.equals(usuario.getContrasena())) {
                return usuario; // Devolver el usuario autenticado
            }
        }
        return null; // Si no se encuentra un usuario con las credenciales proporcionadas
    }
}
