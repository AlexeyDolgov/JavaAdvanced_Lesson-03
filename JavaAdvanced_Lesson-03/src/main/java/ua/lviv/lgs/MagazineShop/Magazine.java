package ua.lviv.lgs.MagazineShop;

import java.time.LocalDate;

public class Magazine {
	private int id;
	private String title;
	private String description;
	private LocalDate publishDate;
	private double subscriptionPrice;

	public Magazine(int id, String title, String description, LocalDate publishDate, double subscriptionPrice) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.publishDate = publishDate;
		this.subscriptionPrice = subscriptionPrice;
	}

	public Magazine(String title, String description, LocalDate publishDate, double subscriptionPrice) {
		this.title = title;
		this.description = description;
		this.publishDate = publishDate;
		this.subscriptionPrice = subscriptionPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public double getSubscriptionPrice() {
		return subscriptionPrice;
	}

	public void setSubscriptionPrice(double subscriptionPrice) {
		this.subscriptionPrice = subscriptionPrice;
	}

	@Override
	public String toString() {
		if (id == 0)
			return "Журнал \"" + title + "\", " + description + ", Дата выхода: " + publishDate + ", Цена подписки: "
					+ subscriptionPrice + " грн.";
		else
			return "Magazine ID#" + id + ": Журнал \"" + title + "\", " + description + ", Дата выхода: " + publishDate
					+ ", Цена подписки: " + subscriptionPrice + " грн.";
	}
}
