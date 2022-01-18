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

      User user1 = new User("Ivan","Ivanov","hendai@mail.ru");
      User user2 = new User("Ivan","Ivanov","Porsche@mail.ru");
      User user3 = new User("Ivan","Ivanov","Lamborghini@mail.ru");
      User user4 = new User("Ivan","Ivanov","Mazda@mail.ru");

      Car Hendai = new Car("Civic",777);
      Car Porsche = new Car("Panamera",777);
      Car Lamborghini = new Car("urus",777);
      Car Mazda = new Car("rx-7",777);

      userService.add(user1.setCar(Hendai).setUser(user1));
      userService.add(user2.setCar(Porsche).setUser(user2));
      userService.add(user3.setCar(Lamborghini).setUser(user3));
      userService.add(user4.setCar(Mazda).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Civic", 777));

      try {
         User notFoundUser = userService.getUserByCar("Panamera", 777);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
