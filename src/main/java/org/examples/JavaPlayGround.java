package org.examples;

import org.examples.common.Person;
import org.examples.common.PersonDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JavaPlayGround {

    public static void main(String[] args) {

        //Thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello there!");
            }
        });

        //Comparator
        Integer [] numbers = {43, 23, 7, 90, 42, 10};
        Arrays.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n1-n2;
            }
        });

        System.out.println("Numbers sorted: "+Arrays.toString(numbers));
        //Classic find max
        System.out.println("Max: "+findMax(numbers));

        List<Person> personList = Arrays.asList(
                new Person("John Doe", 42),
                new Person("Jane Doe", 35),
                new Person("Bob", 25),
                new Person("Alice", 20),
                new Person("Fred", 18)
        );

        List<Person> filteredList= getPeopleEqualOrOver(personList, 25);
        System.out.println("People (>=25y): "+ filteredList);

        //Data transformation (map)
        System.out.println("Person(DTO): "+ toPersonDTO(personList));
    }

    private static List<PersonDTO> toPersonDTO(List<Person> personList) {
        List<PersonDTO> resultList = new ArrayList<>();
        if(personList == null || personList.isEmpty()){
            return resultList;
        }
        for(Person person: personList){
            PersonDTO dto = new PersonDTO(person.name, person.age);
            resultList.add(dto);
        }
        return resultList;
    }

    private static Integer findMax(Integer[] numbers) {
        if(numbers == null || numbers.length == 0){
            return null;
        }
        Integer max = numbers[0];
        for(int i=0; i < numbers.length; ++i){
            if(numbers[i] > max){
                max = numbers[i];
            }
        }
        return max;
    }
    //So far so good...But what if you were asked to filter another criteria, say gender
    private static List<Person> getPeopleEqualOrOver(List<Person>personList, int age){
        List<Person> resultList= new ArrayList<>();
        for(Person person: personList){
            if(person.age >= age){
                resultList.add(person);
            }
        }
        return resultList;
    }
}