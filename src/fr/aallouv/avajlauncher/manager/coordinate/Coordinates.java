package fr.aallouv.avajlauncher.manager.coordinate;

public class Coordinates {

	private final int longitude;
	private final int latitude;
	private final int height;

	public Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		if (height > 100)
			this.height = 100;
		else if (height < 0)
			this.height = 0;
		else
			this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public int getLatitude() {
		return latitude;
	}

	public int getLongitude() {
		return longitude;
	}
}
