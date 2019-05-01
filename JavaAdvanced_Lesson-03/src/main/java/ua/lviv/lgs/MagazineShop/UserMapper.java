package ua.lviv.lgs.MagazineShop;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
	public static User map(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String firstName = result.getString("first_name");
		String lastName = result.getString("last_name");
		String email = result.getString("email");
		String password = result.getString("password");
		String accessLevel = result.getString("access_level");

		return new User(id, firstName, lastName, email, password, accessLevel);
	}
}
