package sda.orange.grcy.person;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonDemo {

    public static void main(String[] args) {

        var person1 = new Person("Jan", "Kowalski", 33, Gender.MALE, LocalDate.of(1991, 6,1), 4);
        var person2 = new Person("Janina", "Nowak", 18, Gender.FEMALE, LocalDate.of(1991, 4,1), 0);
        var person3 = new Person("Andrzej", "Zduń", 45, Gender.MALE, LocalDate.of(1991, 5,1), 2);

        var person4 = new Person("Jerzy", "Ząbal", 24, "m", LocalDate.of(1991, 11,1), 0);
        var person5 = new Person("Elżbieta", "Grysik", 15, "f", LocalDate.of(1991, 2,1), 0);
        var person6 = new Person("Wiesława", "Stalowa", 65, "f", LocalDate.of(1991, 4,1), 5);

        person3.addChild(new Person("Patryk", "Zduń", 7, "m", LocalDate.of(1991, 7,1), 0));
        person3.addChild(new Person("Weronika", "Zduń", 12, "f", LocalDate.of(1991, 8,1), 0));

        person1.addChild(new Person("Mikołaj", "Kowalski", 7, "m", LocalDate.of(1991, 12,1), 0));
        person1.addChild(new Person("Klaudia", "Kowalska", 5, "f", LocalDate.of(1991, 10,1), 0));
        person1.addChild(new Person("Zuzia", "Kowalska", 3, "f", LocalDate.of(1991, 6,1), 0));
        person1.addChild(new Person("Norbert", "Kowalski", 11, "m", LocalDate.of(1991, 4,1), 0));

        person6.addChild(new Person("Kasia", "Stalowa", 15, "f", LocalDate.of(1991, 6,3), 0));
        person6.addChild(new Person("Ola", "Stalowa", 13, "f", LocalDate.of(1991, 6,1), 0));
        person6.addChild(new Person("Kamil", "Stalowy", 2, "m", LocalDate.of(1991, 6,2), 0));
        person6.addChild(new Person("Kuba", "Stalowy", 3, "m", LocalDate.of(1991, 6,1), 0));
        person6.addChild(new Person("Nina", "Stalowa", 10, "f", LocalDate.of(1991, 6,7), 0));
        grubaKrecha();
        System.out.println("Wypisujemy listę osób:");
        List<Person> people = List.of(person1, person2, person3, person4, person5, person6);
        people.forEach(each -> System.out.println(each.personInfo(false)));
        grubaKrecha();
        System.out.println("Wypisujemy listę osób posortowanych po nazwisku");
        people.stream()
                .sorted()
                .forEach(each -> System.out.println(each.personInfo(true)));
        grubaKrecha();
        System.out.println("Wypisujemy listę osób posortowanych po wieku od najstarszego");
        people.stream()
                .sorted(new PersonByAgeComparator().reversed())
                .forEach(each -> System.out.println(each.personInfo(false)));
        grubaKrecha();
        System.out.println("Lista wszystkich dzieci");
        people.stream()
                //map czyli "przekształć element strumienia na "coś",
                //w tym przypadku przekształć Person na List<Person>
                // czyli na wejściu jest osoba a na wyjściu lista dzieci tej osoby
                //.map(each -> each.getChildren())
                .map(Person::getChildren)
                //flatMap czyli "spłaszcz i przekształć"
                //czyli wyciągamy "wnętrze" elementu strumienia
                //w naszym przypadku strumień list dzieci przekształcamy
                // na jeden wspólny strumień wszystkich dzieci
                .flatMap(Collection::stream)
                .forEach(each -> System.out.println(each.personInfo(false)));
        grubaKrecha();
        System.out.println("Suma lat wszystkich ludzi");
        int ageSum = people.stream()
                .map(Person::getAge)
                .reduce(0, (current, incoming) -> current + incoming);
        System.out.println("Obliczona suma lat to " + ageSum);
        grubaKrecha();
        System.out.println("Dzieci i dorośli do osobnej struktury");
        List<Person> allChildren = people.stream()
                .map(Person::getChildren)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Map<String, List<Person>> peopleGroupBySurname = Stream.of(people, allChildren)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(each -> each.getSurname(), Collectors.toList()));

        peopleGroupBySurname.entrySet()
                .stream()
                .forEach(each -> {
                    System.out.println("Klucz (nazwisko): " + each.getKey());
                    each.getValue().stream().forEach(val -> System.out.println("\t" + val.personInfo(false)));
                });
    }

    private static void grubaKrecha() {
        System.out.println("================================================");
        System.out.println("================================================");
    }
}

