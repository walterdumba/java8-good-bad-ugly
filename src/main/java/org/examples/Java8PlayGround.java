package org.examples;

import org.examples.common.Person;
import org.examples.common.PersonDTO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8PlayGround {

    public static void main(String[] args) {

        //Thread
        Thread thread = new Thread(() -> System.out.println("Hello there!"));

        //Comparator
        Integer [] numbers = {43, 23, 7, 90, 42, 10};
        Arrays.sort(numbers, (n1, n2) -> n1-n2); //Alternative: Comparator.comparingInt()

        System.out.println("Numbers sorted: "+Arrays.toString(numbers));
        Optional<Integer> maybeMax = findMax(numbers);
        System.out.println("Max: "+maybeMax.orElse(null));//Null is just to ensure the same semantic between both playground

        List<Person> personList = Arrays.asList(
                new Person("John Doe", 42),
                new Person("Jane Doe", 35),
                new Person("Bob", 25),
                new Person("Alice", 20),
                new Person("Fred", 18)
        );

        /* Stream API */

        //Predicate: Filtering
        List<Person> filteredList= getPersonByCriteria(personList, each -> each.age >= 25);
        System.out.println("Person (>=25y): "+ filteredList);

        //map(): Data transformation
        System.out.println("Person(DTO): "+ toPersonDTO(personList));

    }

    private static Optional<Integer> findMax(Integer[] numbers){
        return Arrays.stream(numbers).max(Comparator.naturalOrder());
    }

    private static List<Person> getPersonByCriteria(List<Person>personList, Predicate<Person> criteria){
        return personList
                .stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }

    private static List<PersonDTO> toPersonDTO(List<Person> personList){
        return personList
                .stream()
                .map(each -> new PersonDTO(each.name, each.age))
                .collect(Collectors.toList());
    }
}