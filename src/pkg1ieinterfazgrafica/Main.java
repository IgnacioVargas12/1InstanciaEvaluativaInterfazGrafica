package pkg1ieinterfazgrafica;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Estudiante alumno = new Estudiante ("Samuel De Luque", "35275", "Marketing", 2015);
        int opcion;
        int opcion2;
        do {
            System.out.println(" \n === MENU PRINCIPAL ===");
            System.out.println("1. Ver perfil");
            System.out.println("2. Gestion de materias");
            System.out.println("3. Registrar asistencia");
            System.out.println("4. Registrar calificacion");
            System.out.println("5. Ver reportes");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            try { //este try catch se utiliza para validar cuando se escriba algun valor que no sea numerico
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Error: Debes ingresar una opcion valida.");
                opcion = -1;   // setea una opción que dispare el 'default' del switch
                continue; //lleva directamente al final del do-while
            }
            switch (opcion){
                case 1: //Ver perfil
                    alumno.mostrarResumen();
                    break;
                case 2: //Gestion de materias
                    System.out.println("\n=== SUB-MENU ===");
                    System.out.println("1. Inscribirse a una materia");
                    System.out.println("2. Dar de baja una materia");
                    System.out.println("3. Listado de materias y sus promedios");
                    System.out.println("4. Buscar materia por codigo o nombre");
                    System.out.println("5. Salir");
                    System.out.print("Opcion: ");
                    opcion2 =Integer.parseInt(sc.nextLine());
                    switch (opcion2){
                        case 1: 
                            boolean datoValido = false;
                            while (!datoValido){
                                try {
                                    System.out.print("\nIngrese el nombre de la materia: ");
                                    String nombremateria;
                                    nombremateria = sc.nextLine();
                                    System.out.print("Ingrese el cuatrimestre: ");
                                    int cuatrimestre;
                                    cuatrimestre = Integer.parseInt(sc.nextLine());
                                    System.out.print("Ingrese el codigo de la materia (entre 3 - 10 caracteres): ");
                                    String codigo;
                                    codigo = sc.nextLine();
                                    System.out.print("Ingrese el anio: ");
                                    int anio;
                                    anio = Integer.parseInt(sc.nextLine());
                                    Materia nuevamateria = new Materia(nombremateria, codigo, cuatrimestre, anio);
                                    //Si pasa el constructor, significa que todos los datos son correctos
                                    datoValido = true; //permite salir del while
                                    System.out.println("Materia creada exitosamente!");
                                    alumno.inscribirse(nuevamateria);
                                }
                                catch (IllegalArgumentException e) {
                                    System.out.println("\n[ERROR] " + e.getMessage()); //e.getMessage es el sout que se envia desde la clase materia cuando da error
                                    System.out.println("Por favor, intente nuevamente.\n");
                                }
                            }
                        break; 
                        case 2: 
                            System.out.print("\nIngrese el codigo de la materia: ");
                            String codigo;
                            codigo = sc.nextLine();
                            alumno.darDeBaja(codigo);
                        break;
                        case 3:
                            System.out.println("\nListado de materias inscriptas junto a su promedio");
                            ArrayList<InscripcionMateria> lista = alumno.getMaterias();
                            if (lista.isEmpty()) {
                                System.out.println("No hay materias registradas.");
                            } else {
                                    for (InscripcionMateria ins : lista) {
                                    System.out.println("- " + ins.getMateria().getNombre() + " | Promedio: " + ins.getPromedio());
                                }
                            }
                        break;
                        case 4:
                            System.out.println("\n1. Buscar materia por el nombre");
                            System.out.println("2. Buscar materia por el codigo");
                            System.out.print("Opcion: ");
                            int opcion3;
                            opcion3 = Integer.parseInt(sc.nextLine());
                            switch (opcion3) {
                                case 1:
                                {
                                    System.out.print("\nIngrese el nombre de la materia: ");
                                    String nombre = sc.nextLine();
                                    alumno.buscarPorNombre(nombre);
                                    InscripcionMateria encontrada = alumno.buscarPorNombre(nombre);
                                    if (encontrada != null) {
                                        System.out.println("Materia encontrada!");
                                        System.out.println("Codigo: " + encontrada.getMateria().getCodigo());
                                        System.out.println("Promedio actual: " + encontrada.getPromedio());
                                    } else {
                                        System.out.println("El alumno no esta inscripto en la materia: " + nombre);
                                    }
                                    break;
                                }
                                case 2:
                                {
                                    System.out.print("\nIngrese el codigo de la materia: ");
                                    String codigo2 = sc.nextLine();
                                    alumno.buscarPorCodigo(codigo2);
                                    InscripcionMateria encontrada = alumno.buscarPorCodigo(codigo2);
                                    if (encontrada != null) {
                                        System.out.println("Materia encontrada!");
                                        System.out.println("Codigo: " + encontrada.getMateria().getCodigo());
                                        System.out.println("Promedio actual: " + encontrada.getPromedio());
                                    } else {
                                        System.out.println("El alumno no esta inscripto en la materia: " + codigo2);
                                    }
                                    break;
                                }
                                default:
                                    System.out.println("Opcion invalida.");
                                    break;
                        }
                        break;
                    }
                    break;
                case 3: //Registro de asistencias
                    System.out.print("\nIngrese el nombre de la materia en la cual va a registrar la asistencia: ");
                    String nombre;
                    nombre = sc.nextLine();
                    InscripcionMateria insEncontrada = alumno.buscarPorNombre(nombre);

                    if (insEncontrada != null) {
                        System.out.print("Asistio a la clase? (S/N): ");
                        String respuesta = sc.nextLine();

                        boolean presente = respuesta.equalsIgnoreCase("S");
                        insEncontrada.registrarAsistencia(presente);
                    } else {
                        System.out.println("[ERROR] No se encontro la materia '" + nombre + 
                        "'. Verifique que el alumno este inscripto.");
                    }
                    break;
                case 4: //Registro de calificaciones
                    System.out.print("\nIngrese el nombre de la materia en la cual va a registrar la nota: ");
                    String nombre2;
                    nombre2 = sc.nextLine();
                    InscripcionMateria insEncontrada2 = alumno.buscarPorNombre(nombre2);
                    if (insEncontrada2 != null) {
                        System.out.print("Ingrese la nota: ");
                        int nota;
                        nota = Integer.parseInt(sc.nextLine());
                        insEncontrada2.agregarNota(nota);
                    } else {
                        System.out.println("[ERROR] No se encontro la materia '" + nombre2 + 
                        "'. Verifique que el alumno este inscripto.");
                    }
                    break;
                case 5: //Reportes académicos
                    System.out.println("\n1. Reporte de situacion general");
                    System.out.println("2. Reporte de materias criticas");
                    System.out.println("3. Reporte de materias aprobadas");
                    System.out.println("Opcion: ");
                    int opcion4;
                    opcion4 = Integer.parseInt(sc.nextLine());
                    switch (opcion4){
                        case 1:
                            ArrayList<InscripcionMateria> lista = alumno.getMaterias();
                            if (lista.isEmpty()) {
                                System.out.println("No hay materias registradas.");
                            } else {
                                    int regulares = 0;
                                    int libres = 0;
                                    int enRiesgo = 0;

                                    System.out.println("\n======= REPORTE DE SITUACION GENERAL =======");
                                    for (InscripcionMateria ins : lista) {
                                        String estado;
                                        if (ins.getPorcentajeAsistencia() < 75 || ins.getPromedio() < 4) {
                                            estado = "Libre";
                                            libres++;
                                        } else if (ins.getPromedio() >= 7) {
                                            estado = "Aprobado (Promocionado)";
                                            regulares++;
                                        } else {
                                            estado = "En curso (Regular)";
                                            regulares++;
                                        }

                                        if (ins.getPorcentajeAsistencia() < 80 || ins.getPromedio() < 5) {
                                            enRiesgo++;
                                        }

                                        System.out.println("- " + ins.getMateria().getNombre() + 
                                        " | Condicion: " + ins.getCondicion() + 
                                        " | Promedio: " + ins.getPromedio() + 
                                        " | Estado: " + estado);
                                    }
                                    System.out.println("\n--- RESUMEN ESTADISTICO ---");
                                    System.out.println("Materias Regulares/Aprobadas: " + regulares);
                                    System.out.println("Materias Libres: " + libres);
                                    System.out.println("Materias en Riesgo: " + enRiesgo);
                                    System.out.println("Promedio General: " + alumno.getPromedioGeneral());
                                    
                                    ArrayList<InscripcionMateria> ranking = alumno.getRankingMaterias();

                                    System.out.println("\n--- RANKING DE MATERIAS (Puntaje Descendente) ---");
                                    int puesto = 1;
                                    for (InscripcionMateria ins : ranking) {
                                        System.out.println(puesto + " - " + ins.getMateria().getNombre() + 
                                        " | Puntaje: " +  ins.getPuntajeRanking());
                                        puesto++;
                                    }
                                }
                            break;
                        case 2: 
                            System.out.println("\n=== MATERIAS EN RIESGO DE ASISTENCIA (75% - 85%) ===");
                            ArrayList<InscripcionMateria> riesgo = alumno.getMateriasEnRiesgo();

                            if (riesgo.isEmpty()) {
                                System.out.println("No se encontraron materias en este rango de asistencia.");
                            } else {
                                System.out.printf("%-20s | %-12s | %-10s%n", "Materia", "Asistencia", "Promedio");
                                System.out.println("-------------------------------------------------------");

                                for (InscripcionMateria ins : riesgo) {
                                    System.out.printf("%-20s | %-12.2f%% | %-10.2f%n", 
                                        ins.getMateria().getNombre(), 
                                        ins.getPorcentajeAsistencia(),
                                        ins.getPromedio());
                                }
                                System.out.println("-------------------------------------------------------");
                                System.out.println("Nota: Estas materias requieren atencion para no quedar libre.");
                            }
                            break;
                        case 3:
                            ArrayList<InscripcionMateria> todas = alumno.getMaterias();    
                            double sumaPromedios = 0;
                            double notaMax = -1; 
                            double notaMin = 11; 
                            int contadorAprobadas = 0;

                            System.out.println("\n======= REPORTE DE MATERIAS APROBADAS =======");
                            System.out.printf("%-25s | %-10s | %-10s%n", "Materia", "Promedio", "Condicion");
                            System.out.println("------------------------------------------------------------");

                            for (InscripcionMateria ins : todas) {
                                double promActual = ins.getPromedio();
                                String condActual = ins.getCondicion();

                                if (promActual >= 6 && condActual.equalsIgnoreCase("Regular")) {
                                    System.out.printf("%-25s | %-10.2f | %-10s%n", 
                                    ins.getMateria().getNombre(), promActual, condActual);
                                    sumaPromedios += promActual;
                                    if (promActual > notaMax) notaMax = promActual;
                                    if (promActual < notaMin) notaMin = promActual;
                                    contadorAprobadas++;
                                }
                            }
                            if (contadorAprobadas > 0) {
                                double promedioDelConjunto = sumaPromedios / contadorAprobadas;

                                System.out.println("------------------------------------------------------------");
                                System.out.println("ESTADISTICAS DEL CONJUNTO:");
                                System.out.printf("Nota Maxima:  %.2f%n", notaMax);
                                System.out.printf("Nota Minima:  %.2f%n", notaMin);
                                System.out.printf("Promedio:     %.2f%n", promedioDelConjunto);
                                System.out.println("Total materias aprobadas: " + contadorAprobadas);
                            } else {
                                System.out.println("No se encontraron materias que cumplan con los criterios.");
                            }
                            break;
                    }
                    break;
                case 0: System.out.println("Hasta luego");
                    break;
                default:
                    if (opcion!= -1){
                        System.out.println("Opcion invalida. Intente nuevamente");
                        break;
                    }
            }
        } while (opcion != 0);
    }
}
