package agenda;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AgendaDAO {
    private Map<String, ArrayList<Contacto>> agendas;

    public AgendaDAO() {
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
            System.out.println("No existe una agenda con el nombre '" + nombreAgenda + "'.");
        }
    }

    public void verContacto(String nombreAgenda, String nombreContacto) {
        if (agendas.containsKey(nombreAgenda)) {
            ArrayList<Contacto> contactos = agendas.get(nombreAgenda);
            boolean encontrado = false;
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equals(nombreContacto)) {
                    System.out.println(contacto.toString());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontró un contacto con el nombre '" + nombreContacto + "' en la agenda '" + nombreAgenda + "'.");
            }
        } else {
            System.out.println("No existe una agenda con el nombre '" + nombreAgenda + "'.");
        }
    }

    public boolean anadirContacto(String nombreAgenda, String nombreContacto, String telefonoContacto) {
        if (agendas.containsKey(nombreAgenda)) {
            ArrayList<Contacto> contactos = agendas.get(nombreAgenda);
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equals(nombreContacto)) {
                    System.out.println("El contacto con el nombre '" + nombreContacto + "' ya existe en la agenda '" + nombreAgenda + "'.");
                    return false;
                }
            }
            Contacto nuevoContacto = new Contacto(nombreContacto, telefonoContacto, "", ""); // Crear un nuevo Contacto con datos mínimos
            contactos.add(nuevoContacto);
            System.out.println("Contacto añadido: " + nuevoContacto.toString());
            return true;
        } else {
            System.out.println("No existe una agenda con el nombre '" + nombreAgenda + "'.");
            return false;
        }
    }

    public boolean editarAgenda(String nombreAgenda, String nuevoNombreAgenda) {
        if (agendas.containsKey(nombreAgenda)) {
            ArrayList<Contacto> contactos = agendas.remove(nombreAgenda);
            agendas.put(nuevoNombreAgenda, contactos);
            return true;
        } else {
            System.out.println("No existe una agenda con el nombre '" + nombreAgenda + "'.");
            return false;
        }
    }
    public boolean eliminarContacto(String nombreAgenda, String nombreContacto) {
        if (agendas.containsKey(nombreAgenda)) {
            ArrayList<Contacto> contactos = agendas.get(nombreAgenda);
            Contacto contactoAEliminar = null;
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equals(nombreContacto)) {
                    contactoAEliminar = contacto;
                    break;
                }
            }
            if (contactoAEliminar != null) {
                contactos.remove(contactoAEliminar);
                System.out.println("Contacto eliminado: " + nombreContacto);
                return true;
            } else {
                System.out.println("Contacto no encontrado: " + nombreContacto);
                return false;
            }
        } else {
            System.out.println("Agenda no encontrada: " + nombreAgenda);
            return false;
        }
    }

    public boolean guardarAgenda(String agendaNombre) {
        if (agendas.containsKey(agendaNombre)) {
            ArrayList<Contacto> contactos = agendas.get(agendaNombre);
            String archivo = generarNombreArchivo(agendaNombre); // Generar nombre de archivo único
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
                oos.writeObject(contactos);
                System.out.println("Agenda guardada en el archivo: " + archivo);
                return true; // Indicar que se ha guardado exitosamente
            } catch (IOException e) {
                System.out.println("Error al guardar la agenda en el archivo: " + archivo);
                e.printStackTrace();
            }
        } else {
            System.out.println("No existe una agenda con el nombre '" + agendaNombre + "'.");
        }
        return false; // Indicar que no se ha guardado exitosamente
    }


    private String generarNombreArchivo(String agendaNombre) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); // Generar timestamp
        String archivo = agendaNombre + "_" + timeStamp + ".dat"; // Nombre de archivo único
        return archivo;
    }


    
}