package org.examples.common;

public class PersonDTO {
    public String name;
    public int age;

    public PersonDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}