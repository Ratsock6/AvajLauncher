package fr.aallouv.avajlauncher.manager.flyable;

import fr.aallouv.avajlauncher.manager.coordinate.Coordinates;
import fr.aallouv.avajlauncher.manager.weather.tower.WeatherTower;

public class Aircraft implements Flyable {

	private static long idCounter = 0;
	protected long id;
	protected String name;
	protected Coordinates coordinates;

	protected Aircraft(String name, Coordinates coordinates) {
		this.id = nextId();
		this.name = name;
		this.coordinates = coordinates;
	}

	private long nextId() {
		return ++idCounter;
	}

	public void addHeight(int height) {
		int newHeight = this.coordinates.getHeight() + height;
		if (newHeight > 100)
			newHeight = 100;
		else if (newHeight < 0)
			newHeight = 0;
		this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), newHeight);
	}

	public void removeHeight(int height) {
		int newHeight = this.coordinates.getHeight() - height;
		if (newHeight > 100)
			newHeight = 100;
		else if (newHeight < 0)
			newHeight = 0;
		this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), newHeight);
	}

	public void addLongitude(int longitude) {
		int newLongitude = this.coordinates.getLongitude() + longitude;
		this.coordinates = new Coordinates(newLongitude, this.coordinates.getLatitude(), this.coordinates.getHeight());
	}

	public void removeLongitude(int longitude) {
		int newLongitude = this.coordinates.getLongitude() - longitude;
		this.coordinates = new Coordinates(newLongitude, this.coordinates.getLatitude(), this.coordinates.getHeight());
	}

	public void addLatitude(int latitude) {
		int newLatitude = this.coordinates.getLatitude() + latitude;
		this.coordinates = new Coordinates(this.coordinates.getLongitude(), newLatitude, this.coordinates.getHeight());
	}

	public void removeLatitude(int latitude) {
		int newLatitude = this.coordinates.getLatitude() - latitude;
		this.coordinates = new Coordinates(this.coordinates.getLongitude(), newLatitude, this.coordinates.getHeight());
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	@Override
	public void updateConditions() {
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
	}
}
