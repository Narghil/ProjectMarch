package hu.gaborpernyei;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static hu.gaborpernyei.Apple.*;

public class SortingPersons {

    public static void main(String[] args) {
        List<Person> lstPerson = new LinkedList<>( Arrays.asList( new Person[]{ new Person("B"), new Person("A")  } )  );

        lstPerson.sort( (e,f) -> e.getFullName().compareTo(f.getFullName()));
        //lstPerson.forEach( e -> System.out.println(e.getFullName()) );
        //lstPerson.forEach( e -> System.out.println(e) );
        //lstPerson.stream().filter( e -> e.isA() ).forEach( System.out::println );
        //lstPerson.stream().filter( e -> e.isB(e) ).forEach( System.out::println );
        //lstPerson.stream().filter( e -> Person.isB(e) ).forEach( System.out::println );
        lstPerson.stream().filter( Person::isB ).forEach( System.out::println );
    }
}

class Person{
    String fullName;

    public Person(String fn){
        this.fullName = fn;
    }
    public String getFullName() {
        return fullName;
    }
    public String toString() {
        return fullName;
    }
    public Boolean isA(){ return fullName.equals("A"); }
    static public Boolean isB(Person p){ return p.getFullName().equals("B"); }
}
