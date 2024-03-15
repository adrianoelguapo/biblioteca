package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        while (true) {
            String[] opciones = {"Iniciar sesión", "Registrarse", "Salir"};

            int indiceOpciones = JOptionPane.showOptionDialog(null, "¿Qué desea hacer?", "Inicio de sesión", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            String opcionElegida = opciones[indiceOpciones];

            switch (opcionElegida) {
                case "Iniciar sesión":
                    Usuario usuarioIniciarSesion = autenticarUsuario(biblioteca, false);
                    if (usuarioIniciarSesion != null) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                        mostrarMenu(usuarioIniciarSesion, biblioteca);
                    } else {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión fallido. Por favor, inténtelo de nuevo.");
                    }
                    break;
                case "Registrarse":
                    registrarUsuario(biblioteca);
                    break;
                case "Salir":
                    JOptionPane.showMessageDialog(null, "Has salido de la biblioteca. Bye-bye.");
                    System.exit(0);
            }
        }
    }

    private static void mostrarMenu(Usuario usuario, Biblioteca biblioteca) {
        while (true) {
            String[] opciones = {"Ver libros disponibles", "Ver libros alquilados", "Alquilar libro", "Devolver libro", "Cerrar sesión"};

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
                    JOptionPane.showMessageDialog(null, "Has cerrado sesión.");
                    return;
            }
        }
    }

    private static Usuario autenticarUsuario(Biblioteca biblioteca, boolean registrando) {
        String telefono = JOptionPane.showInputDialog("Ingrese su número de teléfono para acceder a su usuario");
        String contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");

        if (registrando) {
            // Comprobar si el número de teléfono ya está registrado
            for (Usuario usuario : biblioteca.listaUsuarios) {
                if (telefono.equals(usuario.getTelefono())) {
                    JOptionPane.showMessageDialog(null, "El número de teléfono ya está registrado. Por favor, inicie sesión con su contraseña.");
                    return null; // El usuario ya está registrado
                }
            }

            // Registrar al usuario
            String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
            String apellido = JOptionPane.showInputDialog("Ingrese su apellido:");
            String email = JOptionPane.showInputDialog("Ingrese su email:");

            Usuario nuevoUsuario = new Usuario(nombre, apellido, telefono, email, contrasena);
            biblioteca.listaUsuarios.add(nuevoUsuario);
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente. Por favor, inicie sesión.");
            return null;
        } else {
            // Iniciar sesión
            for (Usuario usuario : biblioteca.listaUsuarios) {
                if (telefono.equals(usuario.getTelefono()) && contrasena.equals(usuario.getContrasena())) {
                    return usuario; // Devolver el usuario autenticado
                }
            }
            JOptionPane.showMessageDialog(null, "Número de teléfono o contraseña incorrectos. Por favor, inténtelo de nuevo.");
            return null;
        }
    }

    private static void registrarUsuario(Biblioteca biblioteca) {
        autenticarUsuario(biblioteca, true);
    }
}
