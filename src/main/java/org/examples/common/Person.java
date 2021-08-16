package org.examples.common;

public class Person {
    public String name;
    public int age;
    public Gender gender;
    public Address address;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", address=" + address +
                '}';
    }

    enum Gender{
        MALE, FEMALE
    }

    public static class Address{
        public String street;
        public String zipCode;
        public String city;
    }
}