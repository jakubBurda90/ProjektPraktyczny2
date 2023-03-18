package sda.orange.grcy.lombokDemo;

public class LombokDemoApp {
    public static void main(String[] args) {
        PersonLomboked personLomboked = new PersonLomboked();
        personLomboked.setAge(22);
        personLomboked.setName("Ala");
        personLomboked.setSurname("Kalina");

        System.out.println(personLomboked.getSurname());

        PersonLomboked fullPerson = new PersonLomboked("Jan"
                , "Kowalski", 33);

        System.out.println("Pokaż fullPerson");
        System.out.println(fullPerson);
    }
}

