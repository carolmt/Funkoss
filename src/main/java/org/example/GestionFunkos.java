package org.example;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class GestionFunkos {

    public static void backup(List<Funko> funkos_obj, String rutaAbsoluta) {
        //escritura de ficheros con serializacion
        try (FileOutputStream fos = new FileOutputStream(rutaAbsoluta);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(funkos_obj);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            System.err.println("Error al realizar el respaldo");
        }
    }
    public static List<Funko> restore(String rutaAbsoluta) {
        //lee el fichero y los objetos serializados se restaurarán desde el archivo en rutaAbsoluta
        try (FileInputStream fis = new FileInputStream(rutaAbsoluta);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<Funko> funkosRestaurados = (List<Funko>) ois.readObject();
            System.out.println("Respaldo restaurado desde " + rutaAbsoluta);
            return funkosRestaurados;

            //Si no existe el archivo en la ruta, o si ocurre un error durante la deserialización, imprime mnsj de error y devuelve una lista vacía.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
            System.err.println("Error al restaurar el respaldo");
            return Collections.emptyList(); // Manejo de error: retorna una lista vacía en caso de error
        }
    }
    public void FunkoMasCaro(List<Funko> funkos_obj) throws Exception { //Funko más caro
        Optional<Funko> funkoCaro = funkos_obj.stream() //Optional contiene o no un valor no nulo.Si hay isPresent() devuelve true y get() retorna el valor. Optional<String>
                .max(Comparator.comparing(Funko::getPrecio));

        if (funkoCaro.isPresent()) { //compruebo si el Optional está lleno
            Funko funkoMasCaro = funkoCaro.get();//como optional solo devuelve true cojo los valores guardados y los añado al objeto nuevo
            System.out.println("El funko más caro es: "+funkoMasCaro.toString());
        }
    }

    public void mediaPrecio(List<Funko> funkos_obj) throws Exception { //Media de precio de Funkos
        double media = funkos_obj.stream()
                .mapToDouble(Funko::getPrecio) // mapea los productos a sus precios
                .average()
                .orElse(0.0);
        System.out.println("La media de precio de los funkos es de " + media + "€");
    }

    public void agrupados(List<Funko> funkos_obj) throws Exception { // Funkos agrupados por modelos
        Map<String, List<Funko>> gruposDeFunkos = funkos_obj.stream()
                .collect(Collectors.groupingBy(Funko::getModelo));  // mapa que agrupa los Funkos por modelo

        List<Funko> funkosMarvel = gruposDeFunkos.get("MARVEL");// accediendo a los grupos de Funkos por nombre de modelo
        funkosMarvel.stream().forEach(dato -> System.out.println(dato.toString()));

        List<Funko> funkosDisney = gruposDeFunkos.get("DISNEY");
        funkosDisney.stream().forEach(dato -> System.out.println(dato.toString()));

        List<Funko> funkosAnime = gruposDeFunkos.get("ANIME");
        funkosAnime.stream().forEach(dato -> System.out.println(dato.toString()));

        List<Funko> funkosOtro = gruposDeFunkos.get("OTROS");
        funkosOtro.stream().forEach(dato -> System.out.println(dato.toString()));
    }
    public void cantidadPorModelo(List<Funko> funkos_obj) throws Exception { //Número de funkos por modelos
        Map<String, Long> cantidadPorModelo = funkos_obj.stream()
                .collect(Collectors.groupingBy(Funko::getModelo, Collectors.counting()));

        cantidadPorModelo.forEach((modelo, cantidad) -> {
            System.out.println("-Modelo: "+modelo+"\n-Cantidad: "+ cantidad);
        });    }

    public void lanzados2023(List<Funko> funkos_obj) { //Funkos que han sido lanzados en 2023
        funkos_obj.stream()
                .filter(funko -> funko.getFecha().getYear() == 2023)
                .forEach(dato -> System.out.println((dato.toString())));
    }

}
