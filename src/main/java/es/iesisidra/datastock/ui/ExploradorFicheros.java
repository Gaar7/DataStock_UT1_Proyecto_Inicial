package es.iesisidra.datastock.ui;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExploradorFicheros {
    public static void explorarRuta(String rutaStr) {
        Path ruta = Paths.get(rutaStr);
        // COMPRUEBA SI LA RUTA EXISTE CON UN IF SENCLLO
        if (!Files.exists(ruta)) {
            System.out.println("ERROR: LA RUTA ESPECIFICADA NO EXISTE");
            return;
        }
        // TRY-CATCH
        try {
            if (Files.isRegularFile(ruta)) { // COMPRUEBA QUE LA RUTA QUE SEA UN FICHERO
                System.out.println("[FICHERO] " + ruta.getFileName());
                System.out.println("Ruta absoluta: " + ruta.toAbsolutePath());
                System.out.println("Tamaño: " + Files.size(ruta) + " bytes."); // SI ES UN FICHERO MUESTRA SU
                                                                               // INFORMACION BÁSICA (RUTA ABS Y TAMAÑO)
            } else if (Files.isDirectory(ruta)) { // EN CAMBIO, SI ES UN DIRECTORIO, INICIA UN FOR
                int totalFicheros = 0; // CREACIÓN DE LAS VARIABLES QUE MOSTRARÁN LA CANTIDAD DE FICHEROS Y DIRECTORIOS
                int totalDirectorios = 0;

                System.out.println("=======| EXPLORAR RUTA |========"); // DECORACION PARA LA LISTA DE ELEMENTOS EN EL
                                                                        // DIRECTORIO
                System.out.println("Ruta: " + rutaStr);

                try (DirectoryStream<Path> stream = Files.newDirectoryStream(ruta)) { // TRY-CATCH QUE LISTA CON UN FOR
                                                                                      // LO QUE HAY DENTRO DE LA CARPETA
                                                                                      // USANDO DirectoryStream<Path>
                                                                                      // PARA LEERLA
                    for (Path hijo : stream) { // stream ES EL NÚMERO DE ELEMENTOS DEL DIRECTORIO
                        if (Files.isDirectory(ruta)) {
                            System.out.println("[DIR]   " + hijo.getFileName()); // LOS DIRECTORIOS LOS LISTA COMO
                                                                                 // "[DIR] [nombre del directorio]
                            totalDirectorios++;
                        } else if (Files.isRegularFile(hijo)) {
                            System.out
                                    .println(
                                            "[FICHERO]    " + hijo.getFileName() + " (" + Files.size(hijo) + " bytes)"); // LOS
                                                                                                                         // FICHEROS
                                                                                                                         // LOS
                                                                                                                         // LISTA
                                                                                                                         // COMO
                                                                                                                         // "[FICHERO]
                                                                                                                         // [nombre
                                                                                                                         // del
                                                                                                                         // directorio]
                        } else {
                            System.out.println("[OTRO]  " + hijo.getFileName()); // SI EL ELEMENTO NO ES FICHERO O
                                                                                 // DIRECTORIO, LO MUESTRA COMO "[OTRO]
                                                                                 // [nombre]"
                        }
                    }
                }
                System.out.println("Total ficheros: " + totalFicheros + " | Directorios: " + totalDirectorios); // TERMINA
                                                                                                                // MOSTRANDO
                                                                                                                // EL
                                                                                                                // TOTAL
                                                                                                                // DE
                                                                                                                // FICHEROS
                                                                                                                // Y
                                                                                                                // OTROS
                                                                                                                // DIRTECTORIOS
                                                                                                                // QUE
                                                                                                                // HABÍA
                                                                                                                // DENTRO
                                                                                                                // DE LA
                                                                                                                // RUTA

            } else {
                System.out.println("La ruta existe pero no es ni un fichero regular ni un directorio válido."); // EN
                                                                                                                // CASO
                                                                                                                // DE
                                                                                                                // QUE
                                                                                                                // LA
                                                                                                                // RUTA
                                                                                                                // NO
                                                                                                                // ESTÉ
                                                                                                                // CONTEMPLADA
                                                                                                                // COMO
                                                                                                                // UNA
                                                                                                                // POSIBILIDAD
                                                                                                                // (FICHERO
                                                                                                                // O
                                                                                                                // DIRECTORIO)
            }
        } catch (IOException e) {
            System.out.println("ERROR AL ACCEDER A LA RUTA: " + e.getMessage());
        }
    }
}
