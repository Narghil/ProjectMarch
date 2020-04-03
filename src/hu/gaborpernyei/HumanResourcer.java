package hu.gaborpernyei;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HumanResourcer {

    List<Employee> employees = new LinkedList<Employee>();
    public void addEmployee( String name, Genders gender, Integer age, Integer salary){
        employees.add(new Employee(name, gender, age, salary));
    }

    //Hívás: searchEmployee( e -> e.getname().startWidth("K"));
    public List<Employee> searchEmployee(Predicate<Employee> pred) {
        return employees.stream().filter(pred).collect(Collectors.toList());
    }

    public Map<Boolean, List<Employee>> getPartitioned() {
        return employees.stream().collect(Collectors
                .partitioningBy(Employee::getGenderBool))
        ;
    }
    public static void main(String[] args) {
    }

}

enum Genders{ MALE, FEMALE }

class Employee{
    String name;
    Integer age;
    Integer salary;
    Genders gender;

    public Integer getAge() {
        return age;
    }

    public Genders getGender() {
        return gender;
    }

    public Boolean getGenderBool() {
        return getGender() == Genders.MALE;
    }

    public Employee(){}

    public Employee( String name, Genders gender, Integer age, Integer salary){
        this.name = name; this.age = age; this.salary = salary;
        this.gender = gender;
    }
}

