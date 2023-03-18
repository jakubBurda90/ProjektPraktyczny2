package sda.orange.grcy.person;

import java.util.Comparator;

public class PersonByAgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person first, Person second) {
        return first.getAge() - second.getAge();
    }
}

