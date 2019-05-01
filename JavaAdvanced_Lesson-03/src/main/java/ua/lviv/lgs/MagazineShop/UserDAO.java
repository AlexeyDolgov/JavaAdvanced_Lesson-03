package ua.lviv.lgs.MagazineShop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class UserDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	private Logger logger = Logger.getLogger(UserDAO.class);
	private String CREATE = "insert into user(`first_name`, `last_name`, `email`, `password`, `access_level`) values (?, ?, ?, ?, ?)";
	private String READ_ALL = "select * from user";
	private String READ_BY_ID = "select * from user where id = ?";
	private String READ_BY_EMAIL = "select * from user where email = ?";
	private String UPDATE_BY_ID = "update user set first_name = ?, last_name = ?, email = ?, password = ?, access_level = ? where id = ?";
	private String UPDATE_BY_EMAIL = "update user set first_name = ?, last_name = ?, password = ?, access_level = ? where email = ?";
	private String DELETE_BY_ID = "delete from user where id = ?";

	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	public void insert(User user) throws SQLException {
		preparedStatement = connection.prepareStatement(CREATE);
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPassword());
		preparedStatement.setString(5, user.getAccessLevel());

		int rows = preparedStatement.executeUpdate();
		String message = String.format("Adding " + user + "... %d row added!", rows);
		logger.info(message);
	}

	public List<User> readAll() throws SQLException {
		preparedStatement = connection.prepareStatement(READ_ALL);

		ResultSet result = preparedStatement.executeQuery();

		List<User> userList = new ArrayList<>();
		while (result.next()) {
			userList.add(UserMapper.map(result));
		}

		return userList;
	}

	public User readByID(int id) throws SQLException {
		preparedStatement = connection.prepareStatement(READ_BY_ID);
		preparedStatement.setInt(1, id);

		ResultSet result = preparedStatement.executeQuery();
		result.next();

		return UserMapper.map(result);
	}

	public User readByEmail(String email) throws SQLException {
		preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
		preparedStatement.setString(1, email);

		ResultSet result = preparedStatement.executeQuery();
		result.next();

		return UserMapper.map(result);
	}

	public void updateByID(User user) throws SQLException {
		preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPassword());
		preparedStatement.setString(5, user.getAccessLevel());
		preparedStatement.setInt(6, user.getId());

		int rows = preparedStatement.executeUpdate();
		String message = String.format("Updating " + user + "... %d row updated!", rows);
		logger.info(message);
	}

	public void updateByEmail(User user) throws SQLException {
		preparedStatement = connection.prepareStatement(UPDATE_BY_EMAIL);
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getPassword());
		preparedStatement.setString(4, user.getAccessLevel());
		preparedStatement.setString(5, user.getEmail());

		int rows = preparedStatement.executeUpdate();
		String message = String.format("Updating " + user + "... %d row updated!", rows);
		logger.info(message);
	}

	public void delete(int id) throws SQLException {
		preparedStatement = connection.prepareStatement(DELETE_BY_ID);
		preparedStatement.setInt(1, id);

		int rows = preparedStatement.executeUpdate();
		String message = String.format("Deleting user with ID#" + id + "... %d row deleted!", rows);
		logger.info(message);
	}
}
