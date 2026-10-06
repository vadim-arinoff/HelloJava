package ru.aston.moodle1;

public class Main {
    public static void main(String[] args) {
        Address address = new Address ("Moscow");
        Person person = new Person("Kolya", address);

        address.setCity("Volgograd");
        System.out.println(person.getAddress().getCity());

        person.getAddress().setCity("Rostov");
        System.out.println(person.getAddress().getCity());
    }
}
