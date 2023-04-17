package agenda;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContactoDAO {
    private Map<String, ArrayList<Contacto>> agendas;

    public ContactoDAO() {
        agendas = new HashMap<>();
    }

    public boolean crearAgenda(String nombreAgenda) {
        if (agendas.containsKey(nombreAgenda)) {
            return false;
        } else {
            agendas.put(nombreAgenda, new ArrayList<>());
            return true;
        }
    }

    public void verAgenda(String nombreAgenda) {
        if (agendas.containsKey(nombreAgenda)) {
            System.out.println("Agenda: " + nombreAgenda);
            ArrayList<Contacto> contactos = agendas.get(nombreAgenda);
            if (contactos.isEmpty()) {
                System.out.println("No hay contactos en esta agenda.");
            } else {
                for (Contacto contacto : contactos) {
                    System.out.println("Nombre: " + contacto.getNombre() + ", Teléfono: " + contacto.getTelefono());
                }
            }
        } else {
            System.out.println("No se encontró la agenda con el nombre '" + nombreAgenda + "'.");
        }
    }

    public void verContacto(String nombreAgenda, String nombreContacto) {
        if (agendas.containsKey(nombreAgenda)) {
            ArrayList<Contacto> contactos = agendas.get(nombreAgenda);
            boolean encontrado = false;
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equalsIgnoreCase(nombreContacto)) {
                    System.out.println("Nombre: " + contacto.getNombre() + ", Teléfono: " + contacto.getTelefono());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontró el contacto con el nombre '" + nombreContacto + "' en la agenda '" + nombreAgenda + "'.");
            }
        } else {
            System.out.println("No se encontró la agenda con el nombre '" + nombreAgenda + "'.");
        }
    }
    public boolean anadirContacto(String nombreAgenda, String nombreContacto, String telefonoContacto, String emailContacto, String direccionContacto) {
        if (agendas.containsKey(nombreAgenda)) {
            ArrayList<Contacto> contactos = agendas.get(nombreAgenda);
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equalsIgnoreCase(nombreContacto)) {
                    return false; // Ya existe un contacto con el mismo nombre
                }
            }
            Contacto nuevoContacto = new Contacto(nombreContacto, telefonoContacto, emailContacto, direccionContacto);
            contactos.add(nuevoContacto);
            return true;
        } else {
            System.out.println("No se encontró la agenda con el nombre '" + nombreAgenda + "'.");
            return false;
        }
    }

    public boolean editarContacto(String nombreAgenda, String nombreContacto, String nuevoNombre, String nuevoTelefono, String nuevoEmail, String nuevaDireccion) {
        if (agendas.containsKey(nombreAgenda)) {
            ArrayList<Contacto> contactos = agendas.get(nombreAgenda);
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equalsIgnoreCase(nombreContacto)) {
                    contacto.setNombre(nuevoNombre);
                    contacto.setTelefono(nuevoTelefono);
                    contacto.setEmail(nuevoEmail);
                    contacto.setDireccion(nuevaDireccion);
                    return true;
                }
            }
            System.out.println("No se encontró el contacto con el nombre '" + nombreContacto + "' en la agenda '" + nombreAgenda + "'.");
            return false;
        } else {
            System.out.println("No se encontró la agenda con el nombre '" + nombreAgenda + "'.");
            return false;
        }
    }

    public boolean eliminarContacto(String nombreAgenda, String nombreContacto) {
        if (agendas.containsKey(nombreAgenda)) {
            ArrayList<Contacto> contactos = agendas.get(nombreAgenda);
            for (int i = 0; i < contactos.size(); i++) {
                if (contactos.get(i).getNombre().equalsIgnoreCase(nombreContacto)) {
                    contactos.remove(i);
                    return true;
                }
            }
            System.out.println("No se encontró el contacto con el nombre '" + nombreContacto + "' en la agenda '" + nombreAgenda + "'.");
            return false;
        } else {
            System.out.println("No se encontró la agenda con el nombre '" + nombreAgenda + "'.");
            return false;
        }
    }

    public boolean guardarAgenda(String nombreAgenda) {
        if (agendas.containsKey(nombreAgenda)) {
            ArrayList<Contacto> contactos = agendas.get(nombreAgenda);
            String nombreArchivo = nombreAgenda + ".txt";
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo)))) {
                for (Contacto contacto : contactos) {
                    writer.println(contacto.getNombre() + "," + contacto.getTelefono());
                }
                writer.flush();
                return true;
            } catch (IOException e) {
                System.out.println("Error al guardar la agenda en el archivo.");
                return false;
            }
        } else {
            System.out.println("No se encontró la agenda con el nombre '" + nombreAgenda + "'.");
            return false;
        }
    }
}
