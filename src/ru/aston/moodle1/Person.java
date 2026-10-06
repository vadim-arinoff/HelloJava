package ru.aston.moodle1;

public final class Person {
    private final String name;
    private final Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = new Address(address.getCity());
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return new Address(address.getCity());
    }
}
