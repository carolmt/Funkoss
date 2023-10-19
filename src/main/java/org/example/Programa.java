package org.example;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Programa {

    public static void main(String[] args) throws IOException {
        GestionFunkos gestion = new GestionFunkos();

        Path UbiFunkos = Path.of(".", "src","main","java","org","example", "funkos.csv");         //ruta donde tengo el CSV integrado en el IJ

        // Lectura de ficheros CSV con Files.lines en java.nio
        try (Stream<String> contenidoFichero = Files.lines(UbiFunkos)) {
            List<Funko> funkos_obj = contenidoFichero.skip(1)               //esquivamos la 1a linea que es la de COD,NOMBRe
                    .map(Funko::new)                                            //aqui es cuando entramos en el constructor y por eso lee el string
                    .toList();

            System.out.println("FUNKO MAS CARO");
            gestion.FunkoMasCaro(funkos_obj);
            System.out.println("LA MEDIA");
            gestion.mediaPrecio(funkos_obj);
            System.out.println("FUNKOS AGRUPADO");
            gestion.agrupados(funkos_obj);
            System.out.println("CUANTOS FUNKS HAY POR CADA MODELO");
            gestion.cantidadPorModelo(funkos_obj);
            System.out.println("LNZADOS 2023");
            gestion.lanzados2023(funkos_obj);

            GestionFunkos.backup(funkos_obj, "C:\\Users\\carol\\Documents\\DAM\\backup.dat");
            List<Funko> funkosRestaurados = GestionFunkos.restore("C:\\Users\\carol\\Documents\\DAM\\backup.dat");

        } catch (IOException e) {
            e.printStackTrace(System.out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}