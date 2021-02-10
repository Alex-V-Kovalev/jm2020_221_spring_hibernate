package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что делаем?");
        System.out.println("===============================");
        System.out.println("1: добавляем 4 дефолтных юзера");
        System.out.println("2: выводим всех юзеров");
        System.out.println("3: посмотрим у кого таз");
        System.out.print("> ");
        String choice = scanner.nextLine();

        UserService userService = context.getBean(UserService.class);
        switch (choice) {
            case "1":
                Car car = new Car("Lambo", 111);
                User user = new User("User1", "Lastname1", "user1@mail.ru", car);
                userService.add(user);

                car = new Car("Mitsu", 222);
                user = new User("User2", "Lastname2", "user2@mail.ru", car);
                userService.add(user);

                car = new Car("VAZ", 99999);
                user = new User("User3", "Lastname3", "user3@mail.ru", car);
                userService.add(user);

                car = new Car("Mustang", 123);
                user = new User("User4", "Lastname4", "user4@mail.ru", car);
                userService.add(user);
                break;
            case "2":
                List<User> users = userService.listUsers();
                for (User usr : users) {
                    System.out.println("Id = " + usr.getId());
                    System.out.println("First Name = " + usr.getFirstName());
                    System.out.println("Last Name = " + usr.getLastName());
                    System.out.println("Email = " + usr.getEmail());
                    System.out.println("============================");
                    System.out.println("Car = " + usr.getCar());
                    System.out.println();
                }
                break;
            case "3":
                User oneUser = userService.getUserByCarModelAndSeries("VAZ", 99999);
                System.out.println("Id = " + oneUser.getId());
                System.out.println("First Name = " + oneUser.getFirstName());
                System.out.println("Last Name = " + oneUser.getLastName());
                System.out.println("Email = " + oneUser.getEmail());
                System.out.println("============================");
                System.out.println("Car = " + oneUser.getCar());
                System.out.println();
                break;
            default:
                System.out.println("Чет не то выбрал, перезапусти программу и убедись, что выбераешь 1, 2 или 3");
                System.out.println("bye-bye");
        }
        context.close();
    }
}
