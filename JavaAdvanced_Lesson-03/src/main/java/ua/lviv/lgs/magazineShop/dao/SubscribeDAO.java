package ua.lviv.lgs.magazineShop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.magazineShop.domain.Subscribe;

public class SubscribeDAO {
	private Logger log = Logger.getLogger(SubscribeDAO.class);

	public Subscribe insert(int userID, int magazineID, boolean subscribeStatus, LocalDate subscribeDate, int subscribePeriod)
			throws DAOException {
		log.info("Creating new subscribe in database...");
		String sqlQuery = "insert into subscribe(`user_id`, `magazine_id`, `subscribe_status`, `subscribe_date`, `subscribe_period`) values (?, ?, ?, ?, ?)";

		Subscribe subscribe = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, userID);
			statement.setInt(2, magazineID);
			statement.setBoolean(3, subscribeStatus);
			statement.setDate(4, Date.valueOf(subscribeDate));
			statement.setInt(5, subscribePeriod);

			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) added!", rows));

			log.trace("Getting result set...");
			if (rows == 0) {
				log.error("Creating subscribe failed, no rows affected!");
				throw new DAOException("Creating subscribe failed, no rows affected!");
			} else {
				resultSet = statement.getGeneratedKeys();

				if (resultSet.next()) {
					log.trace("Creating Subscribe to return...");
					subscribe = new Subscribe(resultSet.getInt(1), userID, magazineID, subscribeStatus, subscribeDate,
							subscribePeriod);
				}
			}
		} catch (SQLException e) {
			log.error("Creating subscribe failed!");
			throw new DAOException("Creating subscribe failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning Subscribe...");
		log.info(subscribe + " is added to database!");
		return subscribe;
	}

	public List<Subscribe> readAll() throws DAOException {
		log.info("Getting all subscribes from database...");
		String sqlQuery = "select * from subscribe";

		List<Subscribe> subscribeList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);

			log.trace("Getting result set...");
			resultSet = statement.executeQuery();

			log.trace("Creating list of subscribes to return...");
			while (resultSet.next()) {
				subscribeList.add(new Subscribe(resultSet.getInt("id"), resultSet.getInt("user_id"),
						resultSet.getInt("magazine_id"), resultSet.getBoolean("subscribe_status"),
						resultSet.getDate("subscribe_date").toLocalDate(), resultSet.getInt("subscribe_period")));
			}
		} catch (SQLException e) {
			log.error("Getting list of subscribes failed!");
			throw new DAOException("Getting list of subscribes failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning list of subscribes...");
		return subscribeList;
	}

	public Subscribe readByID(int id) throws DAOException {
		log.info("Getting subscribe by id from database...");
		String sqlQuery = "select * from subscribe where id = ?";

		Subscribe subscribe = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, id);

			log.trace("Getting result set...");
			resultSet = statement.executeQuery();

			log.trace("Creating Subscribe to return...");
			while (resultSet.next()) {
				subscribe = new Subscribe(resultSet.getInt("id"), resultSet.getInt("user_id"),
						resultSet.getInt("magazine_id"), resultSet.getBoolean("subscribe_status"),
						resultSet.getDate("subscribe_date").toLocalDate(), resultSet.getInt("subscribe_period"));
			}
		} catch (SQLException e) {
			log.error("Getting subscribe by id failed!");
			throw new DAOException("Getting subscribe by id failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning Subscribe...");
		log.info(subscribe + " is getted from database!");
		return subscribe;
	}

	public boolean updateByID(int id, int userID, int magazineID, boolean subscribeStatus, LocalDate subscribeDate,
			int subscribePeriod) throws DAOException {
		log.info("Updating subscribe by id in database...");
		String sqlQuery = "update subscribe set user_id = ?, magazine_id = ?, subscribe_status = ?, subscribe_date = ?, subscribe_period = ? where id = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, userID);
			statement.setInt(2, magazineID);
			statement.setBoolean(3, subscribeStatus);
			statement.setDate(4, Date.valueOf(subscribeDate));
			statement.setInt(5, subscribePeriod);
			statement.setInt(6, id);

			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) updated!", rows));

			if (rows == 0) {
				log.error("Updating subscribe failed, no rows affected!");
				throw new DAOException("Updating subscribe failed, no rows affected!");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Updating subscribe failed!");
			throw new DAOException("Updating subscribe failed!", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		if (result == false) {
			log.info("Updating subscribe failed, no rows affected!");
		} else {
			log.trace("Returning result...");
			log.info("Subscribe with ID#" + id + " is updated in database!");
		}
		return result;
	}

	public boolean delete(int id) throws DAOException {
		log.info("Deleting subscribe by id from database...");
		String sqlQuery = "delete from subscribe where id = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, id);

			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) deleted!", rows));

			if (rows == 0) {
				log.error("Deleting subscribe failed, no rows affected!");
				throw new DAOException("Deleting subscribe failed, no rows affected!");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Deleting subscribe failed!");
			throw new DAOException("Deleting subscribe failed!", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		if (result == false) {
			log.info("Deleting subscribe failed, no rows affected!");
		} else {
			log.trace("Returning result...");
			log.info("Subscribe with ID#" + id + " is deleted from database!");
		}
		return result;
	}
}
