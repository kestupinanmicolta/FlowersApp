package com.flowersapp.data.repository;

import com.flowersapp.data.model.City;
import com.flowersapp.data.model.Department;
import com.flowersapp.data.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static List<User> userList = new ArrayList<>();

    static {
        City city= new City();
        city.setId(1);
        city.setName("Leticia");
        Department deparment =new Department();
        deparment.setId(1);
        deparment.setName("Amazonas");
        userList.add(new User("Abby Flores", "abby@mail.com", "1234567", "pass123","Cr 63 #51-24",city,deparment));
        userList.add(new User("Juan Perez", "juan@mail.com", "9876543", "juan123","Cr 63 #51-24",city,deparment));
        userList.add(new User("Maria Gomez", "maria@mail.com", "5554433", "maria123","Cr 63 #51-24",city,deparment));
        userList.add(new User("Carlos Ruiz", "carlos@mail.com", "2223344", "carlos123","Cr 63 #51-24",city,deparment));
        userList.add(new User("Ana Lopez", "ana@mail.com", "1110099", "ana123","Cr 63 #51-24",city,deparment));
    }

    public static User login(String email, String password) {
        return userList.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    public static Boolean isRegistered(String email) {
        return userList.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }
    public static Boolean addUser(User userNew) {
        if (!isRegistered(userNew.getEmail())) {
            userList.add(userNew);
            return true;
        }
        return false;
    }
}