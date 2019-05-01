package ua.lviv.lgs.MagazineShop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SubscribesMapper {
	public static Subscribes map(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		int userID = result.getInt("user_id");
		int magazineID = result.getInt("magazine_id");
		boolean subscribeStatus = result.getBoolean("subscribe_status");
		LocalDate subscribeDate = result.getDate("subscribe_date").toLocalDate();
		int subscribePeriod = result.getInt("subscribe_period");

		return new Subscribes(id, userID, magazineID, subscribeStatus, subscribeDate, subscribePeriod);
	}
}
