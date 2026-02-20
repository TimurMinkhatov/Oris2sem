import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");

        UserService userService = context.getBean(UserService.class);

        userService.create(new User(1L, "Alice"));
        userService.create(new User(2L, "Buddy"));
        userService.create(new User(3L, "Tim"));

        System.out.println("все юзеры");
        userService.findAll().forEach(System.out::println);

        System.out.println("юзер с айди 1");
        System.out.println(userService.findById(1L));

        System.out.println("юзер с айди 2 после апдейта");
        userService.update(2L, new User(2L, "Tom"));
        System.out.println(userService.findById(2L));

        System.out.println("после удаления юзера с айди 3");
        userService.delete(3L);
        userService.findAll().forEach(System.out::println);
    }
}