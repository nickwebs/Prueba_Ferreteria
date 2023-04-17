package agenda;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String agendaNombre;
		AgendaDAO agendaDAO = new AgendaDAO();

		System.out.println("¡Bienvenido a la Agenda!");
		while (true) {
			System.out.println("Por favor, elija una opción:");
			System.out.println("1. Crear nueva agenda");
			System.out.println("2. Ver agenda");
			System.out.println("3. Ver contacto");
			System.out.println("4. Añadir contacto");
			System.out.println("5. Editar agenda");
			System.out.println("6. Eliminar contacto");
			System.out.println("7. Guardar agenda");
			System.out.println("8. Salir");

			int opcion = scanner.nextInt();
			scanner.nextLine(); // Limpiar el buffer de entrada

			switch (opcion) {
			case 1:
				System.out.println("Por favor, ingrese el nombre de la nueva agenda:");
				agendaNombre = scanner.nextLine();
				if (agendaDAO.crearAgenda(agendaNombre)) {
					System.out.println("Se ha creado la agenda '" + agendaNombre + "' exitosamente.");
				} else {
					System.out.println("Ya existe una agenda con el nombre '" + agendaNombre + "'.");
				}
				break;
			case 2:
				System.out.println("Por favor, ingrese el nombre de la agenda:");
				agendaNombre = scanner.nextLine();
				agendaDAO.verAgenda(agendaNombre);
				break;
			case 3:
				System.out.println("Por favor, ingrese el nombre de la agenda:");
				agendaNombre = scanner.nextLine();
				System.out.println("Por favor, ingrese el nombre del contacto:");
				String nombreContacto = scanner.nextLine();
				agendaDAO.verContacto(agendaNombre, nombreContacto);
				break;
			case 4:
				System.out.println("Por favor, ingrese el nombre de la agenda:");
				agendaNombre = scanner.nextLine();
				System.out.println("Por favor, ingrese el nombre del contacto:");
				nombreContacto = scanner.nextLine();
				System.out.println("Por favor, ingrese el número de teléfono del contacto:");
				String telefonoContacto = scanner.nextLine();
				if (agendaDAO.anadirContacto(agendaNombre, nombreContacto, telefonoContacto)) {
					System.out.println("Se ha añadido el contacto '" + nombreContacto + "' exitosamente.");
				} else {
					System.out.println("Ya existe un contacto con el nombre '" + nombreContacto + "' en la agenda '"
							+ agendaNombre + "'.");
				}
				break;
			case 5:
				System.out.println("Por favor, ingrese el nombre de la agenda:");
				agendaNombre = scanner.nextLine();
				System.out.println("Por favor, ingrese el nuevo nombre de la agenda:");
				String nuevoNombreAgenda = scanner.nextLine();
				if (agendaDAO.editarAgenda(agendaNombre, nuevoNombreAgenda)) {
					System.out.println("Se ha editado la agenda exitosamente.");
				} else {
					System.out.println("No se pudo editar la agenda.");
				}
				break;
			case 6:
				System.out.println("Por favor, ingrese el nombre de la agenda:");
				agendaNombre = scanner.nextLine();
				System.out.println("Por favor, ingrese el nombre del contacto a eliminar:");
				nombreContacto = scanner.nextLine();
				if (agendaDAO.eliminarContacto(agendaNombre, nombreContacto)) {
					System.out.println("Se ha eliminado el contacto exitosamente.");
				} else {
					System.out.println("No se pudo eliminar el contacto.");
				}
				break;
			case 7:
			    System.out.println("Por favor, ingrese el nombre de la agenda:");
			    agendaNombre = scanner.nextLine();
			    if (agendaDAO.guardarAgenda(agendaNombre)) {
			        System.out.println("Se ha guardado la agenda exitosamente.");
			    } else {
			        System.out.println("No se pudo guardar la agenda.");
			    }
			    break;
			case 8:
				System.out.println("¡Hasta luego!");
				System.exit(0);
			default:
				System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
				break;
			}
		}
	}
}
