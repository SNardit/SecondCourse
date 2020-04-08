package lesson3;

import java.util.*;

public class PhoneBook {


    String surname;
    String phone;

    public PhoneBook(String surname, String phone) {
        this.surname = surname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "surname='" + surname + '\'' +
                ", phone=" + phone +
                '}';
    }

    public static void main(String[] args) {

        Set<PhoneBook> people = new HashSet<>();
        people.add(new PhoneBook("Ivanov", "111"));
        people.add(new PhoneBook("Petrov", "222"));
        people.add(new PhoneBook("Sidorov", "333"));
        people.add(new PhoneBook("Kuznecov", "444"));
        people.add(new PhoneBook("Ivanov", "555"));
        people.add(new PhoneBook("Ivanov", "666"));

        for (PhoneBook person : people) {
            System.out.println(person);
        }

        Map<String, String > list = new HashMap<>();

        for (PhoneBook s : people) {
            if (!list.containsKey(s.surname)) {
                list.put(s.surname, s.phone);
            }
            else {list.put(s.surname, list.get(s.surname) + "\n" + s.phone);}
        }


        System.out.println("Номер телефона по заданной фамилии: \n" + list.get("Ivanov"));

    }
}
