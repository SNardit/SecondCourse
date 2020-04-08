package lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhonesBook {

        private String surname;
        private String phone;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

        public PhonesBook(String surname, String phone) {
            this.surname = surname;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "PhonesBook{" +
                    "surname='" + surname + '\'' +
                    ", phone=" + phone +
                    '}';
        }

        public static void main(String[] args) {

            Set<PhonesBook> people = new HashSet<>();
            people.add(new PhonesBook("Ivanov", "111"));
            people.add(new PhonesBook("Petrov", "222"));
            people.add(new PhonesBook("Sidorov", "333"));
            people.add(new PhonesBook("Kuznecov", "444"));
            people.add(new PhonesBook("Ivanov", "555"));
            people.add(new PhonesBook("Ivanov", "666"));

            for (PhonesBook person : people) {
                System.out.println(person);
            }

            Map<String, String > list = new HashMap<>();

            for (PhonesBook s : people) {
                if (!list.containsKey(s.surname)) {
                    list.put(s.surname, s.phone);
                }
                else {list.put(s.surname, list.get(s.surname) + "\n" + s.phone);}
            }


            System.out.println("Номер телефона по заданной фамилии: \n" + list.get("Ivanov"));

        }

}
