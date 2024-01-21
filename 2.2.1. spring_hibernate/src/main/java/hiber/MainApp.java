package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User Denis = new User("Denis", "Vasiliev", "d.vasiliev@mail.ru");
      Car Chevrolet = new Car("Chevrolet", 2);
      Denis.setCar(Chevrolet);

      User Maria = new User("Maria", "Vasilieva", "m.vasilieva@mail.ru");
      Car BMW = new Car("BMW", 3);
      Maria.setCar(BMW);

      User Alex = new User("Alex", "Baranov", "a.baranov@mail.ru");
      Car Kia = new Car("Kia", 1);
      Alex.setCar(Kia);

      User Daria = new User("Daria", "Baranova", "d.baranova@mail.ru");
      Car Audi = new Car("Audi", 7);
      Daria.setCar(Audi);

      userService.add(Denis);
      userService.add(Maria);
      userService.add(Alex);
      userService.add(Daria);

      userService.searchCar("Audi", 7);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      User user = userService.searchCar("Audi", 7);
      System.out.println(user.toString());

      try {
         User notFoundUser = userService.searchCar("Lada", 9);
      } catch (NoResultException e) {
      }
      context.close();
   }
}