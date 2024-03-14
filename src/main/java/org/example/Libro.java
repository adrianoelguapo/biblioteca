package org.example;
import java.util.ArrayList;

public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private ArrayList<Usuario> listaReserva;
    private int posicionCola;

    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.listaReserva = new ArrayList<>();
        this.posicionCola = 0;
    }

    // getters y setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public ArrayList<Usuario> getListaReserva() {
        return listaReserva;
    }

    public int getPosicionCola() {
        return posicionCola;
    }

    public void setPosicionCola(int posicionCola) {
        this.posicionCola = posicionCola;
    }
}

