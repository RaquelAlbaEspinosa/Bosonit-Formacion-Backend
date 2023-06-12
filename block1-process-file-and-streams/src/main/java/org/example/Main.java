package org.example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InvalidLineFormatException {
        // rescatar el contenido de el archivo people.csv
        Path path = Paths.get("src/main/resources/people.csv");
        // convertir dicho contenido en un String
        String read = Files.readAllLines(path).toString();
        // transformar dicho String en un Array de Strings, cada elemento del array es una de las personas
        String[] people = read.split(",");
        // Generar una lista de objetos tipo Person (vacía por el momento)
        List<Person> peopleClass = new ArrayList<>();
        // Bucle que va a recorrer el Array de Strings generado anteriormente
        for (String person : people) {
            //System.out.println(person);
            // Condicionales para eliminar los elementos "[" y "]" de los Strings del Array si los tienen
            if(person.contains("]")){
                person = person.replace("]", "");
            }
            if(person.contains("[")){
                person = person.replace("[", "");
            }
            // Genera un array dividiendo el String que contiene los datos de cada persona por sus delimitadores de campo
            String[] personSplit = person.split(":", -1);
            // Condicional en el que comprueba que el Array de cada persona tenga una longitud de 3 (pues son 3 campos)
            if (personSplit.length != 3) {
                // Si no tiene y la longitud es 1, faltan los dos delimitadores, si es de 2 falta 1 solo delimitador
                if(personSplit.length == 1){
                    try {
                        throw new InvalidLineFormatException(person, "Faltan los dos delimitadores de campo (:)");
                    } catch (InvalidLineFormatException e) {
                        System.out.println(e);
                    }

                } else {
                    try {
                        throw new InvalidLineFormatException(person, "Falta 1 delimitador de campo (:)");
                    } catch (InvalidLineFormatException e){
                        System.out.println(e);
                    }
                }
            } else {
                // Una vez comprobado que los datos se han introducido correctamente en el array creamos un nuevo objeto
                // de tipo Persona y le pasamos los datos del array
                Person person2 = new Person(personSplit);
                // Comprobamos que contiene el campo obligatorio Name
                person2.checkObligatoryName(person2.name);
                // Añadimos la nueva persona a la lista de personas que generamos antes del bucle
                peopleClass.add(person2);
            }
        }

        // a.
        List<Person> peopleFilterAge = filterAge(peopleClass);
        peopleFilterAge.forEach(person -> System.out.println(person.getAll()));
        System.out.println("---------------------------------");
        // b.
        List<Person> peopleFilterNameA = filterNameA(peopleClass);
        peopleFilterNameA.forEach(person -> System.out.println(person.getAll()));
        System.out.println("---------------------------------");
        // c.
        Optional<Person> personMadrid = peopleFilterAge.stream()
                .filter(person -> person.getTown().equals("Madrid"))
                .findFirst();
        if(personMadrid.isPresent()){
            System.out.println(personMadrid.get().getAll());
        } else {
            System.out.println("No se ha encontrado a ninguna persona cuya ciudad sea Madrid y tenga menos de 25 años");
        }
        System.out.println("---------------------------------");
        // d.
        Optional<Person> personBarcelona = peopleFilterAge.stream()
                .filter(person -> person.getTown().equals("Barcelona"))
                .findFirst();
        if(personBarcelona.isPresent()){
            System.out.println(personBarcelona.get().getAll());
        } else {
            System.out.println("No se ha encontrado a ninguna persona cuya ciudad sea Barcelona y tenga menos de 25 años");
        }
        System.out.println("---------------------------------");
    }
    public static List<Person> filterAge (List<Person> personList){
        List<Person> personFilterAge = new ArrayList<>();
        personList.stream()
                .filter(person -> person.getAge() < 25 && person.getAge() != 0)
                .forEach(personFilterAge::add);
        return personFilterAge;
    }
    public static List<Person> filterNameA (List<Person> personList){
        List<Person> peopleFilterNameA = new ArrayList<>();
        personList.stream()
                .filter(person -> !person.getName().startsWith("A") && !person.getName().startsWith("Á"))
                .forEach(peopleFilterNameA::add);
        return peopleFilterNameA;
    }
}