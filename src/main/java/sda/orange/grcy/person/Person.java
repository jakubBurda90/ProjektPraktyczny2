package sda.orange.grcy.person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {
    private String name;
    private String surname;
    private int age;
    private Gender gender;
    private LocalDate dateOfBirth;
    private int noOfChildren;
    private List<Person> children;

    public Person(String name, String surname, int age, Gender gender, LocalDate dateOfBirth, int noOfChildren) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.noOfChildren = noOfChildren;
        children = new ArrayList<>();
    }

    public Person(String name, String surname, int age, String genderAbbrev, LocalDate dateOfBirth, int noOfChildren) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = Gender.findByAbbrev(genderAbbrev);
        this.dateOfBirth = dateOfBirth;
        this.noOfChildren = noOfChildren;
        children = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public String personInfo(boolean showNoOfChildren) {
        StringBuilder childrenInfo = showNoOfChildren
                ? new StringBuilder(", dzieci=").append(noOfChildren)
                : new StringBuilder("");

        return new StringBuilder("Dane osoby: imię=").append(name)
                .append(", nazwisko=").append(surname)
                .append(", wiek=").append(age)
                .append(", data urodzenia=").append(dateOfBirth.toString())
                .append(childrenInfo)
                .toString();
    }

    @Override
    public int compareTo(Person other) {
        return this.surname.compareTo(other.surname);
    }
}
