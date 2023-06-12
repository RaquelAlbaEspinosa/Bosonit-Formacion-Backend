package org.example;

public class Person {
    String name;
    String town;
    int age;
    // El controlador que contiene los mismos elementos y comprueba que tengan el formato correcto
    Person(String[] person1) {
        this.name = person1[0].trim();
        // Cuando el campo town está vacío se llena con unknown de forma predeterminada
        if(person1[1].isBlank()){
            this.town = "unknown";
        } else {
            this.town = person1[1];
        }
        // Cuando el campo age está vacío se llena con un 0 de forma predeterminada
        if(person1[2].isBlank()) {
            this.age = 0;
        } else {
            this.age = Integer.parseInt(person1[2]);
        }

    }
    // Métodos que nos devuelven el nombre, la ciudad o la edad
    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public int getAge() {
        return age;
    }
    // Mátodo que nos devuelve un String con todos los campos
    public String getAll() {
        return "Nombre: " + name + ". Ciudad: " + town + ". Edad: " + age + ".";
    }

    public void checkObligatoryName (String name) throws InvalidLineFormatException {
        if(name.isEmpty() || name == null) {
            throw new InvalidLineFormatException(name, "La propiedad es obligatoria, no puede estar vacía");
        }
    }
}
