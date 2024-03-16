package org.example;
import java.util.ArrayList;
import javax.swing.*;

public class Biblioteca {
    ArrayList<Libro> listaDisponibles = new ArrayList<>();
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public Biblioteca(){
        listaDisponibles.add(new Libro("Don Quijote de la Mancha", "Cervantes", "9788424115283"));
        listaDisponibles.add(new Libro("Moby Dick", "Herman Melville", "9788491050688"));
        listaDisponibles.add(new Libro("Drácula", "Bram Stoker", "9788491051883"));
        listaDisponibles.add(new Libro("El nombre del viento", "Patrick Rothfuss", "9788499082479"));
        listaDisponibles.add(new Libro("El código Da Vinci", "Dan Brown", "9788408011076"));
        listaUsuarios.add(new Usuario("David", "Álvarez", "1111", "emailspam@gmail.com"));
        listaUsuarios.add(new Usuario("Carolina", "González", "2222", "caro@gmail.com"));
    }

    public void verDisponibles(){
        StringBuilder libros = new StringBuilder("Libros disponibles:\n");
        for (int i = 0; i < listaDisponibles.size(); i++) {
            Libro libro = listaDisponibles.get(i);
            libros.append(i + 1).append(". ").append(libro.getTitulo()).append("\n");
        }
        JOptionPane.showMessageDialog(null, libros);
    }

    public void verAlquilados(){
        String telefono = JOptionPane.showInputDialog("Acceso de usuario, introduce el telefono");
        boolean encontrado = false;
        for(Usuario usuario : listaUsuarios){
            if(telefono.equals(usuario.getTelefono())){
                encontrado = true;
                if(usuario.getLibrosDevolver().isEmpty()){
                    JOptionPane.showMessageDialog(null, "No tienes ningun libro alquilado");
                    return;
                }
                StringBuilder librosalquilados = new StringBuilder("Libros alquilados:" + "\n");
                for (int i = 0; i < usuario.getLibrosDevolver().size(); i++) {
                    Libro libro = usuario.getLibrosDevolver().get(i);
                    librosalquilados.append(i + 1).append(" ").append(libro.getTitulo()).append("\n");
                }
                JOptionPane.showMessageDialog(null, librosalquilados);
            }
        }
        if(!encontrado){
            JOptionPane.showMessageDialog(null,"El usuario no existe, vuelva a probar");
        }
    }

    public void pedirLibro(){
        String telefono = JOptionPane.showInputDialog("Acceso de usuario, introduce el telefono");
        boolean encontrado = false;
        for (Usuario usuario : listaUsuarios) {
            if (telefono.equals(usuario.getTelefono())) {
                encontrado = true;
                StringBuilder disponibles = new StringBuilder("Introduce el numero del libro que quieres alquilar" + "\n");
                for (int i = 0; i < listaDisponibles.size(); i++) {
                    Libro libro = listaDisponibles.get(i);
                    disponibles.append(i + 1).append(" ").append(libro.getTitulo()).append("\n");
                }
                String input = JOptionPane.showInputDialog(disponibles.toString());
                int libro = Integer.parseInt(input);
                if (libro > 0 && libro <= listaDisponibles.size()) {
                    Libro seleccionado = listaDisponibles.get(libro - 1);
                    usuario.getLibrosDevolver().add(seleccionado);
                    listaDisponibles.remove(seleccionado);
                    JOptionPane.showMessageDialog(null, "Libro alquilado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "No existe dicho libro, intentalo de nuevo");
                    return;
                }
            }
        }
        if (!encontrado){
            JOptionPane.showMessageDialog(null,"El usuario no existe, vuelva a probar");
        }
    }

    public void devolverLibro(){
        String telefono = JOptionPane.showInputDialog("Acceso de usuario, introduce el telefono");
        boolean encontrado = false;
        for (Usuario usuario : listaUsuarios) {
            if (telefono.equals(usuario.getTelefono())) {
                encontrado = true;
                if (usuario.getLibrosDevolver().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ningun libro por devolver");
                    return;
                }
                StringBuilder devolver = new StringBuilder("Introduce el numero del libro que quieres devolver" + "\n");
                for (int i = 0; i < usuario.getLibrosDevolver().size(); i++) {
                    Libro libro = usuario.getLibrosDevolver().get(i);
                    devolver.append(i + 1).append(" ").append(libro.getTitulo()).append("\n");
                }
                String input = JOptionPane.showInputDialog(devolver.toString());
                int libro = Integer.parseInt(input);
                if (libro > 0 && libro <= usuario.getLibrosDevolver().size()) {
                    Libro seleccionado = usuario.getLibrosDevolver().get(libro - 1);
                    listaDisponibles.add(seleccionado);
                    usuario.getLibrosDevolver().remove(seleccionado);
                    JOptionPane.showMessageDialog(null, "El libro se devolvio con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "No existe dicho libro, intentalo de nuevo");
                    return;
                }

            }
        }
        if (!encontrado){
            JOptionPane.showMessageDialog(null,"El usuario no existe, vuelva a probar");
        }
    }
}
