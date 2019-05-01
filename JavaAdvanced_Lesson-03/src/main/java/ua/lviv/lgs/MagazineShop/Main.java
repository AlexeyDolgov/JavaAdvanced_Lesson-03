package ua.lviv.lgs.MagazineShop;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Logger logger = Logger.getLogger(Main.class);
		PropertyConfigurator.configure("log4j.config.properties");
		logger.info("Starting application...");
		
		UserDAO userDAO = new UserDAO(ConnectionUtils.openConnection());
		MagazineDAO magazineDAO = new MagazineDAO(ConnectionUtils.openConnection());
		SubscribesDAO subscribesDAO = new SubscribesDAO(ConnectionUtils.openConnection());		
		
		List<User> userList = new ArrayList<>();
		userList.add(new User("Иван", "Петренко", "petrenko@gmail.com", "123456", "USER"));
		userList.add(new User("Василий", "Иванов", "vas_ivanov@gmail.com", "123456", "USER"));

		userList.forEach(user -> {
			try {
				userDAO.insert(user);
			} catch (SQLException e) {
				logger.error("Error occured!", e);
				e.printStackTrace();
			}
		});

		System.out.println(userDAO.readByID(2));
		System.out.println(userDAO.readByEmail("petrenko@gmail.com"));
		userDAO.delete(1);
		userDAO.readAll().forEach(System.out::println);

		magazineDAO.insert(new Magazine("Playboy", "Алина Альвинская покоряет шоу-бизнес и продает экзотические острова!",
						LocalDate.parse("2019-04-01"), 60.25));
		magazineDAO.readAll().forEach(System.out::println);

		subscribesDAO.insert(new Subscribes(2, 1, true, LocalDate.parse("2019-04-26"), 12));
		subscribesDAO.readAll().forEach(System.out::println);
	}
}
