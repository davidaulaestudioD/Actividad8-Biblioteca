package org.example;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        while (true) {
            String[] opciones = {"Ver libros disponibles", "Ver libros alquilados/devolver", "Pedir un libro", "Devolver libro", "Salir"};

            int num = JOptionPane.showOptionDialog(null, "BIBLIOTECA:", "Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,opciones[0]);

            String elegida = opciones[num];

            switch (elegida) {

                case "Ver libros disponibles":
                    biblioteca.verDisponibles();
                    break;

                case "Ver libros alquilados/devolver":
                    biblioteca.verAlquilados();
                    break;

                case "Pedir un libro":
                    biblioteca.pedirLibro();
                    break;

                case "Devolver libro":
                    biblioteca.devolverLibro();
                    break;

                case "Salir":
                    System.exit(0);
            }
        }

    }
}