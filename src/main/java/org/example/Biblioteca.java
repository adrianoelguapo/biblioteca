package org.example;

import javax.swing.*;
import java.util.ArrayList;

public class Biblioteca {
    ArrayList<Libro> listaLibrosDisponibles = new ArrayList<>();
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public Biblioteca() {
        listaLibrosDisponibles.add(new Libro("El Principito", "Antoine de Saint-Exupéry", "978-84-204-6644-2"));
        listaLibrosDisponibles.add(new Libro("1984", "George Orwell", "978-84-663-3747-4"));
        listaLibrosDisponibles.add(new Libro("Crimen y Castigo", "Fyodor Dostoevsky", "978-84-9838-125-5"));
        listaLibrosDisponibles.add(new Libro("El Quijote", "Miguel de Cervantes", "978-84-492-3526-2"));
        listaLibrosDisponibles.add(new Libro("Hamlet", "William Shakespeare", "978-84-16634-34-7"));
        listaLibrosDisponibles.add(new Libro("Los Juegos del Hambre", "Suzanne Collins", "978-84-08-08878-7"));
        listaLibrosDisponibles.add(new Libro("Cumbres Borrascosas", "Emily Brontë", "978-84-670-4044-7"));
        listaLibrosDisponibles.add(new Libro("El Señor de los Anillos", "J.R.R. Tolkien", "978-84-9754-533-8"));
        listaLibrosDisponibles.add(new Libro("Orgullo y Prejuicio", "Jane Austen", "978-84-663-2812-5"));

        listaUsuarios.add(new Usuario("Adriano","Elguapo","1234","elguapo@gmail.com"));
        listaUsuarios.add(new Usuario("Beatriz", "SuperBea", "5678", "beatriz.super@hotmail.com"));
        listaUsuarios.add(new Usuario("Carlos", "ElIngenioso", "9012", "carlos.ingenioso@outlook.es"));
        listaUsuarios.add(new Usuario("Daniela", "LaViajera", "3456", "daniela.viajera@yahoo.com"));
        listaUsuarios.add(new Usuario("Enzo", "ElMagnífico", "7890", "enzo.magnifico@icloud.com"));
    }

    public void verLibrosDisponibles(){
        StringBuilder librosDisponibles = new StringBuilder("Estos son los libros disponibles:\n");
        for (int i = 0; i < listaLibrosDisponibles.size(); i++) {
            Libro libro = listaLibrosDisponibles.get(i);
            librosDisponibles.append(i + 1).append(". ").append(libro.getTitulo()).append("\n");
        }
        JOptionPane.showMessageDialog(null,librosDisponibles);
    }

    public void verLibrosAlquilados(){
        String telefono = JOptionPane.showInputDialog("Ingrese su número de teléfono para acceder a su usuario");
        boolean usuarioEncontrado = false;
        for (Usuario usuario : listaUsuarios) {
            if (telefono.equals(usuario.getTelefono())) {
                usuarioEncontrado = true;
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
        }
        if (!usuarioEncontrado){
            JOptionPane.showMessageDialog(null,"Error al encontrar el usuario");
        }
    }

    public void alquilarLibro(){
        String telefono = JOptionPane.showInputDialog("Ingrese su número de teléfono para acceder a su usuario");
        boolean usuarioEncontrado = false;
        for (Usuario usuario : listaUsuarios) {
            if (telefono.equals(usuario.getTelefono())) {
                usuarioEncontrado = true;
                StringBuilder librosDisponibles = new StringBuilder("Bienvenidx " + usuario.getNombre() + ". ¿Qué libro desea alquilar?" + "\n");
                for (int x = 0; x < listaLibrosDisponibles.size(); x++) {
                    Libro libro = listaLibrosDisponibles.get(x);
                    librosDisponibles.append(x + 1).append(". ").append(libro.getTitulo()).append("\n");
                }
                String input = JOptionPane.showInputDialog(librosDisponibles.toString());
                int indiceLibro = Integer.parseInt(input);
                if (indiceLibro > 0 && indiceLibro <= listaLibrosDisponibles.size()) {
                    Libro libroSeleccionado = listaLibrosDisponibles.get(indiceLibro - 1);
                    usuario.getLibrosPorDevolver().add(libroSeleccionado);
                    listaLibrosDisponibles.remove(libroSeleccionado);
                    JOptionPane.showMessageDialog(null, "Ha alquilado el libro " + libroSeleccionado.getTitulo());
                } else {
                    JOptionPane.showMessageDialog(null, "El índice del libro no es válido. Inténtelo de nuevo.");
                    return;
                }
            }
        }
        if (!usuarioEncontrado){
            JOptionPane.showMessageDialog(null,"Error al encontrar el usuario");
        }
    }

    public void devolverLibro(){
        String telefono = JOptionPane.showInputDialog("Ingrese su número de teléfono para acceder a su usuario");
        boolean usuarioEncontrado = false;
        for (Usuario usuario : listaUsuarios) {
            if (telefono.equals(usuario.getTelefono())) {
                usuarioEncontrado = true;
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
                    listaLibrosDisponibles.add(libroSeleccionado);
                    usuario.getLibrosPorDevolver().remove(libroSeleccionado);
                    JOptionPane.showMessageDialog(null, "Has devuelto el libro " + libroSeleccionado.getTitulo());
                } else {
                    JOptionPane.showMessageDialog(null, "El índice del libro no es válido. Inténtalo de nuevo.");
                    return;
                }
            }
        }
        if (!usuarioEncontrado){
            JOptionPane.showMessageDialog(null,"Error al encontrar el usuario");
        }
    }
}