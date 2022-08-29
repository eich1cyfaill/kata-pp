package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarServiceImp;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarServiceImp carServiceImp = context.getBean(CarServiceImp.class);

      Car car1 = new Car("AC151", 128759);
      Car car2 = new Car("AC152", 128700);
      Car car3 = new Car("AC153", 1287667);
      Car car4 = new Car("AC154", 84739);

      carServiceImp.add(car1);
      carServiceImp.add(car2);
      carServiceImp.add(car3);
      carServiceImp.add(car4);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      Optional<User> optionalUser = Optional.ofNullable(carServiceImp.getCarOwner(84739));
      Optional<User> optionalUser2 = Optional.ofNullable(carServiceImp.getCarOwner(128700));
      Optional<User> optionalUser3 = Optional.ofNullable(carServiceImp.getCarOwner(128759));
      Optional<User> optionalUser4 = Optional.ofNullable(carServiceImp.getCarOwner(1287459));
      optionalUser.ifPresent(System.out::println);
      optionalUser2.ifPresent(System.out::println);
      optionalUser3.ifPresent(System.out::println);
      optionalUser4.ifPresent(System.out::println);

      context.close();
   }
}
