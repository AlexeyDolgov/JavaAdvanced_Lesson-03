package ua.lviv.lgs.MagazineShop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MagazineMapper {
	public static Magazine map(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String title = result.getString("title");
		String description = result.getString("description");
		LocalDate publishDate = result.getDate("publish_date").toLocalDate();
		double subscriptionPrice = result.getDouble("subscription_price");

		return new Magazine(id, title, description, publishDate, subscriptionPrice);
	}
}
