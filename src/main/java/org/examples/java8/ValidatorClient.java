package org.examples.java8;

import org.examples.common.Person;
import org.examples.common.ValidatorException;

public class ValidatorClient {

    //Old fashionable way
    Validator<Person> oldFashionableValidator = new Validator<Person>() {
        @Override
        public boolean validate(Person person) throws ValidatorException {
            if(person==null){
                throw new ValidatorException("person");
            }
            if(person.name == null){
                throw new ValidatorException("name");
            }
            if(person.age< 42){
                throw new ValidatorException("age");
            }
            if(person.address.city==null){
                throw new ValidatorException("city");
            }
            if(person.address.street == null){
                throw new ValidatorException("street");
            }
            if(person.address.zipCode == null){
                throw new ValidatorException("zipCode");
            }
            return true;
        }
    };

    //Java8
    Validator<Person> validator = ((Validator<Person>) person -> true)
            .and(person -> person!=null, "person")
            .and(person -> person.name != null,"name")
            .and(person -> person.age < 42, "age")
            .and(person -> person.address.city!= null, "city")
            .and(person -> person.address.street!= null, "street")
            .and(person -> person.address.zipCode!= null, "zipCode");

    public void doAction(Person person)throws ValidatorException{
        //TODO:Validate
        //TODO:Do whatever needs to do
    }

    public static void main(String[] args) {

    }
}