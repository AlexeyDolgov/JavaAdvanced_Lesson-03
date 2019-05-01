package ua.lviv.lgs.MagazineShop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class ConnectionUtils {
	private static Logger logger = Logger.getLogger(Main.class);
	private static String url = "jdbc:mysql://localhost/magazine_shop?serverTimezone=" + TimeZone.getDefault().getID();
	private static String userName = "root";
	private static String userPassword = "111111";

	public static Connection openConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, userName, userPassword);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			logger.error("Connection failed!", e);
			e.printStackTrace();
		}

		if (connection != null)
			logger.info("Connecting to database... Successfull!");

		return connection;
	}
}
