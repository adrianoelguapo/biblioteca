package org.example;

import javax.swing.*;
import java.util.ArrayList;

public class Biblioteca {
    ArrayList<Libro> listaLibros = new ArrayList<>();
    static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public Biblioteca() {
        listaLibros.add(new Libro("El Principito", "Antoine de Saint-Exupéry", "978-84-204-6644-2"));
        listaLibros.add(new Libro("1984", "George Orwell", "978-84-663-3747-4"));
        listaLibros.add(new Libro("Crimen y Castigo", "Fyodor Dostoevsky", "978-84-9838-125-5"));
        listaLibros.add(new Libro("El Quijote", "Miguel de Cervantes", "978-84-492-3526-2"));
        listaLibros.add(new Libro("Hamlet", "William Shakespeare", "978-84-16634-34-7"));
        listaLibros.add(new Libro("Los Juegos del Hambre", "Suzanne Collins", "978-84-08-08878-7"));
        listaLibros.add(new Libro("Cumbres Borrascosas", "Emily Brontë", "978-84-670-4044-7"));
        listaLibros.add(new Libro("El Señor de los Anillos", "J.R.R. Tolkien", "978-84-9754-533-8"));
        listaLibros.add(new Libro("Orgullo y Prejuicio", "Jane Austen", "978-84-663-2812-5"));

        listaUsuarios.add(new Usuario("Adriano","Elguapo","1234","elguapo@gmail.com","elguapo"));
        listaUsuarios.add(new Usuario("Beatriz", "SuperBea", "5678", "beatriz.super@hotmail.com","beafea"));
        listaUsuarios.add(new Usuario("Carlos", "ElIngenioso", "9012", "carlos.ingenioso@outlook.es","elfeo"));
        listaUsuarios.add(new Usuario("Daniela", "LaViajera", "3456", "daniela.viajera@yahoo.com","daniviaje"));
        listaUsuarios.add(new Usuario("Enzo", "ElMagnífico", "7890", "enzo.magnifico@icloud.com","provenzo"));
    }

    public void verLibrosDisponibles(Usuario usuario){
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuario no válido");
            return;
        }
        StringBuilder librosDisponibles = new StringBuilder("Bienvenidx " + usuario.getNombre() + ". Estos son los libros disponibles:\n");
        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libro = listaLibros.get(i);
            librosDisponibles.append(i + 1).append(". ").append(libro.getTitulo()).append("\n");
        }
        JOptionPane.showMessageDialog(null,librosDisponibles);
    }

    public void verLibrosAlquilados(Usuario usuario) {
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuario no válido");
            return;
        }

        if (usuario.getLibrosPorDevolver().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Estimadx " + usuario.getNombre() + ", no tiene libros alquilados");
            return;
        }

        StringBuilder librosUsuarios = new StringBuilder("Bienvenidx " + usuario.getNombre() + ". Estos son los libros que ha alquilado y pendientes de devolver:" + "\n");

        for (int x = 0; x < usuario.getLibrosPorDevolver().size(); x++) {
            Libro libro = usuario.getLibrosPorDevolver().get(x);
            librosUsuarios.append(x + 1).append(". ").append(libro.getTitulo()).append("\n");
        }

        JOptionPane.showMessageDialog(null, librosUsuarios);
    }

    public void alquilarLibro(Usuario usuario) {
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuario no válido");
            return;
        }

        StringBuilder librosDisponibles = new StringBuilder("Bienvenidx " + usuario.getNombre() + ". ¿Qué libro desea alquilar?\n");

        for (int x = 0; x < listaLibros.size(); x++) {
            Libro libro = listaLibros.get(x);
            librosDisponibles.append(x + 1).append(". ").append(libro.getTitulo()).append("\n");
        }

        String input = JOptionPane.showInputDialog(librosDisponibles.toString());
        int indiceLibro = Integer.parseInt(input);

        if (indiceLibro > 0 && indiceLibro <= listaLibros.size()) {
            Libro libroSeleccionado = listaLibros.get(indiceLibro - 1);

            if (libroSeleccionado.getPosicionCola() == 0) {
                usuario.getLibrosPorDevolver().add(libroSeleccionado);
                libroSeleccionado.setPosicionCola(1);
                JOptionPane.showMessageDialog(null, "Ha alquilado el libro " + libroSeleccionado.getTitulo());
            } else {
                // El libro está alquilado por otro usuario
                String[] opcionReserva = {"Si", "No"};
                int reserva = JOptionPane.showOptionDialog(null, "El libro ya está alquilado, ¿desea reservarlo?", "Reservar libro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionReserva, opcionReserva[0]);

                if (reserva == JOptionPane.YES_OPTION) {
                    usuario.getLibrosPorDevolver().add(libroSeleccionado);
                    libroSeleccionado.getListaReserva().add(usuario);
                    int nuevaPosicion = libroSeleccionado.getListaReserva().size();
                    libroSeleccionado.setPosicionCola(nuevaPosicion);
                    JOptionPane.showMessageDialog(null, "Se ha realizado la reserva del libro " + libroSeleccionado.getTitulo() + ". Su posición en la cola es la número " + nuevaPosicion);
                } else {
                    JOptionPane.showMessageDialog(null, "Volviendo a la lista de libros");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "El índice del libro no es válido. Inténtelo de nuevo.");
        }
    }

    public void devolverLibro(Usuario usuario) {
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuario no válido");
            return;
        }

        if (usuario.getLibrosPorDevolver().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Estimadx " + usuario.getNombre() + ", no tiene libros para devolver");
            return;
        }

        StringBuilder librosDevolver = new StringBuilder("Bienvenidx " + usuario.getNombre() + ". ¿Qué libro desea devolver?" + "\n");

        for (int x = 0; x < usuario.getLibrosPorDevolver().size(); x++) {
            Libro libro = usuario.getLibrosPorDevolver().get(x);
            librosDevolver.append(x + 1).append(". ").append(libro.getTitulo()).append("\n");
        }

        String input = JOptionPane.showInputDialog(librosDevolver.toString());
        int indiceLibro = Integer.parseInt(input);

        if (indiceLibro > 0 && indiceLibro <= usuario.getLibrosPorDevolver().size()) {
            Libro libroSeleccionado = usuario.getLibrosPorDevolver().get(indiceLibro - 1);
            usuario.getLibrosPorDevolver().remove(libroSeleccionado);

            if (!libroSeleccionado.getListaReserva().isEmpty()) {
                Usuario siguienteUsuario = libroSeleccionado.getListaReserva().remove(0);
                libroSeleccionado.setPosicionCola(libroSeleccionado.getPosicionCola() - 1);
                JOptionPane.showMessageDialog(null, "El libro " + libroSeleccionado.getTitulo() + " ha sido devuelto y está disponible para " + siguienteUsuario.getNombre());
            } else {
                JOptionPane.showMessageDialog(null, "Has devuelto el libro " + libroSeleccionado.getTitulo());
            }
        } else {
            JOptionPane.showMessageDialog(null, "El índice del libro no es válido. Inténtalo de nuevo.");
        }
    }

    public Usuario autenticarUsuario(String telefono, String contrasena) {
        for (Usuario usuario : listaUsuarios) {
            if (telefono.equals(usuario.getTelefono()) && contrasena.equals(usuario.getContrasena())) {
                return usuario;
            }
        }
        return null;
    }

}
